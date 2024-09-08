package com.plazoleta.service;

import com.plazoleta.dto.PedidoDTO;
import com.plazoleta.entity.Pedido;
import com.plazoleta.entity.Restaurante;
import com.plazoleta.exception.ResourceNotFoundException;
import com.plazoleta.repository.PedidoRepository;
import com.plazoleta.repository.RestauranteRepository;
import com.plazoleta.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public Pedido crearPedido(PedidoDTO pedidoDTO) {
        Restaurante restaurante = restauranteRepository.findById(pedidoDTO.getRestauranteId())
                .orElseThrow(()-> new ResourceNotFoundException(Constants.RESTAURANTE_NO_ENCONTRADO));
        Pedido pedido = Pedido.builder()
                .fecha(pedidoDTO.getFecha())
                .estado(pedidoDTO.getEstado())
                .restaurante(restaurante)
                .build();

        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarPedidosPorRestaurante(Long restauranteId) {
        return pedidoRepository.findByRestauranteId(restauranteId);
    }

    @Override
    public Pedido actualizarPedido(Long id, PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Constants.PEDIDO_NO_ENCONTRADO));

        pedido.setFecha(pedidoDTO.getFecha());
        pedido.setEstado(pedidoDTO.getEstado());

        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
