package com.example.JAF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ControladoresApi {
    @Autowired
    ObjetoService objetoService;

    @GetMapping("/api/objeto")
    public ArrayList<Objeto> getAllObjetos() {
        return objetoService.getAllObjetos();
    }

    @PostMapping(value = "/api/objeto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> addShip(@RequestBody Objeto objeto) {
        ObjetoService.getPDF(objeto);
        return ResponseEntity.ok("Object request added to the log.");

    }
    @PutMapping(value = "/api/{ObjetoName}")
    public ResponseEntity<Objeto> updateShipByName(@PathVariable("ObjetoName") String Name, @RequestBody  Objeto updatedObjeto) {

        // Obtener el barco con el nombre especificado
        Objeto existingShip = ObjetoService.getObjetoByName(Name);

        // Verificar si el barco existe
        if (existingShip == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar los atributos del barco existente con los del barco actualizado
        existingShip.setModel(updatedObjeto.getModel());
        existingShip.setCostInCredits(updatedObjeto.getCostInCredits());
        existingShip.setCrew(updatedObjeto.getCrew());
        existingShip.setCargoCapacity(updatedObjeto.getCargoCapacity());
        existingShip.setConsumables(updatedObjeto.getConsumables());
        existingShip.setHyperdriveRating(updatedObjeto.getHyperdriveRating());
        existingShip.setStarshipClass(updatedObjeto.getStarshipClass());
        existingShip.setPilots(updatedObjeto.getPilots());
        existingShip.setFilms(updatedObjeto.getFilms());

        // Guardar el barco actualizado en la base de datos o realizar cualquier otra operación necesaria
        ObjetoService.updateObjeto(existingShip);

        // Devolver una respuesta con el barco actualizado
        return ResponseEntity.ok(existingShip);
    }

}
