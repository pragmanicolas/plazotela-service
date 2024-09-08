package com.plazoleta.service;

import com.plazoleta.dto.PedidoDTO;
import com.plazoleta.entity.Pedido;

import java.util.List;

public interface PedidoService {

    Pedido crearPedido(PedidoDTO pedidoDTO);
    List<Pedido> listarPedidosPorRestaurante(Long restauranteId);
    Pedido actualizarPedido(Long id, PedidoDTO pedidoDTO);
    void eliminarPedido(Long id);
}
