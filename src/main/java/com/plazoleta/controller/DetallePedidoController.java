package com.plazoleta.controller;

import com.plazoleta.dto.DetallePedidoDTO;
import com.plazoleta.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public List<DetallePedidoDTO> listarDetalles(){
        return detallePedidoService.listarDetalles();
    }

    @PostMapping
    public DetallePedidoDTO crearDetalle(@RequestBody DetallePedidoDTO detallePedidoDTO){
        return detallePedidoService.crearDetalle(detallePedidoDTO);
    }

    @PutMapping("/{id}")
    public DetallePedidoDTO actualizarDetalle(@PathVariable Long id, @RequestBody DetallePedidoDTO detallePedidoDTO){
        return detallePedidoService.actualizarDetalle(id, detallePedidoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetalle(@PathVariable Long id){
        detallePedidoService.eliminarDetalle(id);
    }
}
