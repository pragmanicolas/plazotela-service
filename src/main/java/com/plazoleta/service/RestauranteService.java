package com.plazoleta.service;

import com.plazoleta.dto.RestauranteDTO;
import com.plazoleta.entity.Restaurante;

import java.util.List;

public interface RestauranteService {

    List<Restaurante> listarRestaurantes();
    Restaurante crearRestaurante(RestauranteDTO restauranteDTO);
    Restaurante actualizarRestaurante(Long id, RestauranteDTO restauranteDTO);
    void eliminarRestaurante(Long id);
}
