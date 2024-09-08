package com.plazoleta.service;

import com.plazoleta.dto.PlatoDTO;
import com.plazoleta.entity.Plato;
import com.plazoleta.entity.Restaurante;
import com.plazoleta.exception.ResourceNotFoundException;
import com.plazoleta.repository.PlatoRepository;
import com.plazoleta.repository.RestauranteRepository;
import com.plazoleta.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatoServiceImpl implements PlatoService{

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;


    @Override
    public Plato crearPlato(PlatoDTO platoDTO) {
        Restaurante restaurante = restauranteRepository.findById(platoDTO.getRestauranteId())
                .orElseThrow(() -> new ResourceNotFoundException(Constants.RESTAURANTE_NO_ENCONTRADO));
        Plato plato = Plato.builder()
                .nombre(platoDTO.getNombre())
                .precio(platoDTO.getPrecio())
                .restaurante(restaurante)
                .build();
        return platoRepository.save(plato);
    }

    @Override
    public List<Plato> listarPlatosPorRestaurante(Long restauranteId) {
        return platoRepository.findByRestauranteId(restauranteId);
    }

    @Override
    public Plato actualizarPlato(Long id, PlatoDTO platoDTO) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.PLATO_NO_ENCONTRADO));

        plato.setNombre(platoDTO.getNombre());
        plato.setPrecio(platoDTO.getPrecio());
        return platoRepository.save(plato);
    }

    @Override
    public void eliminarPlato(Long id) {
        platoRepository.deleteById(id);
    }
}
