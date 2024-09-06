package com.plazoleta.controller;

import com.plazoleta.dto.RestauranteDTO;
import com.plazoleta.entity.Restaurante;
import com.plazoleta.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listarRestaurantes(){
        return new ResponseEntity<>(restauranteService.listarRestaurantes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurante> crearRestaurante(@RequestBody RestauranteDTO restauranteDTO){
        Restaurante restaurante = restauranteService.crearRestaurante(restauranteDTO);
        return new ResponseEntity<>(restaurante,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> actualizarRestaurante(@PathVariable Long id, @RequestBody RestauranteDTO restauranteDTO){
        Restaurante restaurante = restauranteService.actualizarRestaurante(id,restauranteDTO);
        return new ResponseEntity<>(restaurante, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRestaurante(@PathVariable Long id){
        restauranteService.eliminarRestaurante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
