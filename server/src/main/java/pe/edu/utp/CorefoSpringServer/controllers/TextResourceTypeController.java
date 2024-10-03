package pe.edu.utp.CorefoSpringServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.CorefoSpringServer.DAO.TextResourceTypeDAO;
import pe.edu.utp.CorefoSpringServer.models.TextResourceType;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/text-resource-types")
public class TextResourceTypeController {

	@Autowired
	private TextResourceTypeDAO textResourceTypeDAO;

	// GET: Obtener todos los tipos de recursos textuales o uno específico por ID
	@GetMapping
	public ResponseEntity<List<TextResourceType>> getAllTextResourceTypes(@RequestParam Optional<Long> id) {
		if (id.isPresent()) {
			Optional<TextResourceType> textResourceType = textResourceTypeDAO.findById(id.get());
			return textResourceType.map(value -> new ResponseEntity<>(List.of(value), HttpStatus.OK))
					.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
		return new ResponseEntity<>(textResourceTypeDAO.findAll(), HttpStatus.OK);
	}

	// POST: Crear un nuevo tipo de recurso textual
	@PostMapping
	public ResponseEntity<TextResourceType> createTextResourceType(@RequestBody TextResourceType textResourceType) {
		try {
			TextResourceType newTextResourceType = textResourceTypeDAO.save(textResourceType);
			return new ResponseEntity<>(newTextResourceType, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// PUT: Actualizar un tipo de recurso textual existente
	@PutMapping("/{id}")
	public ResponseEntity<TextResourceType> updateTextResourceType(
			@PathVariable("id") Long id, @RequestBody TextResourceType updatedTextResourceType) {
		Optional<TextResourceType> optionalTextResourceType = textResourceTypeDAO.findById(id);

		if (optionalTextResourceType.isPresent()) {
			TextResourceType existingTextResourceType = optionalTextResourceType.get();
			existingTextResourceType.setText_type(updatedTextResourceType.getText_type());
			return new ResponseEntity<>(textResourceTypeDAO.save(existingTextResourceType), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// DELETE: Eliminar un tipo de recurso textual por ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTextResourceType(@PathVariable("id") Long id) {
		try {
			Optional<TextResourceType> optionalTextResourceType = textResourceTypeDAO.findById(id);
			if (optionalTextResourceType.isPresent()) {
				textResourceTypeDAO.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
