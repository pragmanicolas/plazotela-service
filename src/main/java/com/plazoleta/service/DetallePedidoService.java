package com.plazoleta.service;

import com.plazoleta.dto.DetallePedidoDTO;

import java.util.List;

public interface DetallePedidoService {

    List<DetallePedidoDTO> listarDetalles();
    DetallePedidoDTO crearDetalle(DetallePedidoDTO detallePedidoDTO);
    DetallePedidoDTO actualizarDetalle(Long id, DetallePedidoDTO detallePedidoDTO);
    void eliminarDetalle(Long id);
}
