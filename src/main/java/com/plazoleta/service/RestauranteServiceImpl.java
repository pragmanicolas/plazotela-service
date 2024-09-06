package com.plazoleta.service;

import com.plazoleta.dto.RestauranteDTO;
import com.plazoleta.entity.Restaurante;
import com.plazoleta.exception.ResourceNotFoundException;
import com.plazoleta.repository.RestauranteRepository;
import com.plazoleta.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService{

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    @Override
    public Restaurante crearRestaurante(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(restauranteDTO.getNombre());
        restaurante.setDireccion(restauranteDTO.getDireccion());
        restaurante.setTelefono(restauranteDTO.getTelefono());
        restaurante.setPropietario(restauranteDTO.getPropietario());
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Restaurante actualizarRestaurante(Long id, RestauranteDTO restauranteDTO) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Constants.RESTAURANTE_NO_ENCONTRADO));
        restaurante.setNombre(restauranteDTO.getNombre());
        restaurante.setDireccion(restauranteDTO.getDireccion());
        restaurante.setTelefono(restauranteDTO.getTelefono());
        restaurante.setPropietario(restauranteDTO.getPropietario());
        return restauranteRepository.save(restaurante);
    }

    @Override
    public void eliminarRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }
}
