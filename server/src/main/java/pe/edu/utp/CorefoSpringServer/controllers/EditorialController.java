package pe.edu.utp.CorefoSpringServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.CorefoSpringServer.DAO.EditorialDAO;
import pe.edu.utp.CorefoSpringServer.models.Editorial;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("api/editorials")  // Agregar prefijo común para todas las rutas del controlador
public class EditorialController {

	@Autowired
	private EditorialDAO editorialDAO;

	// Obtener todos los textos o un texto específico por su ID
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Editorial>> getEditorials(@RequestParam Optional<Long> id) {
		if (id.isEmpty()) {
			return ResponseEntity.ok(editorialDAO.findAll());
		}

		Optional<Editorial> t = editorialDAO.findById(id.get());
		return t.map(editorial -> ResponseEntity.ok(Collections.singletonList(editorial)))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Crear un nuevo recurso textual
	@PostMapping
	public ResponseEntity<Editorial> createEditorial(@RequestBody Editorial editorial) {
		try {
			Editorial savedEditorial = editorialDAO.save(editorial);
			return new ResponseEntity<>(savedEditorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Actualizar un recurso textual existente
	@PutMapping("/{id}")
	public ResponseEntity<Editorial> updateEditorial(@PathVariable Long id, @RequestBody Editorial updatedEditorial) {
		return editorialDAO.findById(id).map(existingEditorial -> {
			// Actualizar campos existentes con los datos del recurso actualizado
			existingEditorial.setEditorial(updatedEditorial.getEditorial());

			// Guardar los cambios en la base de datos
			Editorial savedEditorial = editorialDAO.save(existingEditorial);
			return new ResponseEntity<>(savedEditorial, HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Eliminar un recurso textual por su ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEditorial(@PathVariable Long id) {
		return editorialDAO.findById(id).map(existingEditorial -> {
			editorialDAO.delete(existingEditorial);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}