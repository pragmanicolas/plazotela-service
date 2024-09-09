package com.plazoleta.service;

import com.plazoleta.dto.DetallePedidoDTO;
import com.plazoleta.entity.DetallePedido;
import com.plazoleta.entity.Pedido;
import com.plazoleta.entity.Plato;
import com.plazoleta.exception.ResourceNotFoundException;
import com.plazoleta.repository.DetallePedidoRepository;
import com.plazoleta.repository.PedidoRepository;
import com.plazoleta.repository.PlatoRepository;
import com.plazoleta.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService{

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<DetallePedidoDTO> listarDetalles() {
        return detallePedidoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DetallePedidoDTO crearDetalle(DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detalle = mapToEntity(detallePedidoDTO);
        detallePedidoRepository.save(detalle);
        return mapToDTO(detalle);
    }

    @Override
    public DetallePedidoDTO actualizarDetalle(Long id, DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detalleExistente = detallePedidoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Detalle no encontrado"));
        detalleExistente.setCantidad(detallePedidoDTO.getCantidad());

        detalleExistente.setPlato(platoRepository.findById(detallePedidoDTO.getPlatoId())
                        .orElseThrow(() -> new ResourceNotFoundException(Constants.PLATO_NO_ENCONTRADO)));
        detalleExistente.setPedido(pedidoRepository.findById(detallePedidoDTO.getPedidoId())
                .orElseThrow(()-> new ResourceNotFoundException(Constants.PEDIDO_NO_ENCONTRADO)));


        detallePedidoRepository.save(detalleExistente);
        return mapToDTO(detalleExistente);
    }

    @Override
    public void eliminarDetalle(Long id) {
        detallePedidoRepository.deleteById(id);
    }

    // Métodos de mapeo entre entidad y DTO
    private DetallePedido mapToEntity(DetallePedidoDTO dto){
        DetallePedido detalle = new DetallePedido();
        detalle.setCantidad(dto.getCantidad());
        detalle.setPlato(platoRepository.findById(dto.getPlatoId())
                .orElseThrow(()-> new ResourceNotFoundException(Constants.PLATO_NO_ENCONTRADO)));
        detalle.setPedido(pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(()-> new ResourceNotFoundException(Constants.PEDIDO_NO_ENCONTRADO)));

        return detalle;
    }

    private DetallePedidoDTO mapToDTO(DetallePedido detalle){
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(detalle.getId());
        dto.setCantidad(detalle.getCantidad());
        dto.setPlatoId(detalle.getPlato().getId());
        dto.setPedidoId(detalle.getPedido().getId());
        return dto;
    }
}
