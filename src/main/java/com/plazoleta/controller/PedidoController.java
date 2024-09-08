package com.plazoleta.controller;

import com.plazoleta.dto.PedidoDTO;
import com.plazoleta.entity.Pedido;
import com.plazoleta.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/restaurante/{restauranteId}")
    public List<Pedido> listarPedidosPorRestaurante(@PathVariable Long restauranteId){
        return pedidoService.listarPedidosPorRestaurante(restauranteId);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody PedidoDTO pedidoDTO){
        return pedidoService.crearPedido(pedidoDTO);
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO){
        return pedidoService.actualizarPedido(id, pedidoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Long id){
        pedidoService.eliminarPedido(id);
    }
}
