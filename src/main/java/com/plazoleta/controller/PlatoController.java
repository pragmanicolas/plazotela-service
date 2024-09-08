package com.plazoleta.controller;

import com.plazoleta.dto.PlatoDTO;
import com.plazoleta.entity.Plato;
import com.plazoleta.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platos")
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    @GetMapping("/restaurante/{restauranteId}")
    public List<Plato> listarPlatosPorRestaurante(@PathVariable Long restauranteId){
        return platoService.listarPlatosPorRestaurante(restauranteId);
    }

    @PostMapping
    public Plato crearPlato(@RequestBody PlatoDTO platoDTO){
        return platoService.crearPlato(platoDTO);
    }

    @PutMapping("/{id}")
    public Plato actualizarPlato(@PathVariable Long id, @RequestBody PlatoDTO platoDTO){
        return platoService.actualizarPlato(id, platoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarPlato(@PathVariable Long id){
        platoService.eliminarPlato(id);
    }
}
