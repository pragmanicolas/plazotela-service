package com.plazoleta.service;

import com.plazoleta.dto.PlatoDTO;
import com.plazoleta.entity.Plato;

import java.util.List;

public interface PlatoService {

    Plato crearPlato(PlatoDTO platoDTO);
    List<Plato> listarPlatosPorRestaurante(Long restauranteId);
    Plato actualizarPlato(Long id, PlatoDTO platoDTO);
    void eliminarPlato(Long id);
}
