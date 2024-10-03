package pe.edu.utp.CorefoSpringServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.CorefoSpringServer.DAO.TextResourceDAO;
import pe.edu.utp.CorefoSpringServer.models.TextResource;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("api/texts")  // Agregar prefijo común para todas las rutas del controlador
public class TextController {

    @Autowired
    private TextResourceDAO textDAO;

    // Obtener todos los textos o un texto específico por su ID
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TextResource>> getText(@RequestParam Optional<Long> id) {
        if (id.isEmpty()) {
            return ResponseEntity.ok(textDAO.findAll());
        }

        Optional<TextResource> t = textDAO.findById(id.get());
        return t.map(textResource -> ResponseEntity.ok(Collections.singletonList(textResource)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo recurso textual
    @PostMapping
    public ResponseEntity<TextResource> createText(@RequestBody TextResource textResource) {
        try {
            TextResource savedTextResource = textDAO.save(textResource);
            return new ResponseEntity<>(savedTextResource, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar un recurso textual existente
    @PutMapping("/{id}")
    public ResponseEntity<TextResource> updateText(@PathVariable Long id, @RequestBody TextResource updatedTextResource) {
        return textDAO.findById(id).map(existingTextResource -> {
            // Actualizar campos existentes con los datos del recurso actualizado
            existingTextResource.setTitle(updatedTextResource.getTitle());
            existingTextResource.setPublication_date(updatedTextResource.getPublication_date());
            existingTextResource.setStock(updatedTextResource.getStock());
            existingTextResource.setPages(updatedTextResource.getPages());
            existingTextResource.setEdition(updatedTextResource.getEdition());
            existingTextResource.setVolume(updatedTextResource.getVolume());
            existingTextResource.setType(updatedTextResource.getType());
            existingTextResource.setEditorial(updatedTextResource.getEditorial());
            existingTextResource.getWrittenBy().clear();
            existingTextResource.getWrittenBy().addAll(updatedTextResource.getWrittenBy());

            // Guardar los cambios en la base de datos
            TextResource savedResource = textDAO.save(existingTextResource);
            return new ResponseEntity<>(savedResource, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un recurso textual por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteText(@PathVariable Long id) {
        return textDAO.findById(id).map(existingTextResource -> {
            textDAO.delete(existingTextResource);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
