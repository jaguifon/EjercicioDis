package com.example.JAF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControladoresApi {
    @Autowired
    private ObjetoService objetoService;

    @GetMapping("/api/objeto")
    public List<Objeto> getAllObjetos() {
        return objetoService.getAllObjetos();
    }
    @PostMapping(value = "/api/objeto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> addShip(@RequestBody Objeto objeto) {
        ObjetoService.getPDF(objeto);
        return ResponseEntity.ok("Object request added to the log.");

    }
}
