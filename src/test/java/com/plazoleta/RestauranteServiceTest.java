package com.plazoleta;

import com.plazoleta.dto.RestauranteDTO;
import com.plazoleta.entity.Restaurante;
import com.plazoleta.exception.ResourceNotFoundException;
import com.plazoleta.repository.RestauranteRepository;
import com.plazoleta.service.RestauranteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RestauranteServiceTest {

    @MockBean
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Test
    public void testCrearRestaurante(){
        RestauranteDTO dto = new RestauranteDTO();
        dto.setNombre("Nuevo Restaurante");
        dto.setDireccion("Calle 1");

        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(dto.getNombre());
        restaurante.setDireccion(dto.getDireccion());

        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restaurante);
        Restaurante resultado = restauranteService.crearRestaurante(dto);
        assertEquals("Nuevo Restaurante", restaurante.getNombre());
        assertEquals("Calle 1", resultado.getDireccion());
        verify(restauranteRepository, times(1)).save(any(Restaurante.class));
    }

    @Test
    public void testActualizarRestaurante(){
        Long id = 1L;
        RestauranteDTO dto = new RestauranteDTO();
        dto.setNombre("Restaurante Actualizado");
        dto.setDireccion("Dirección Actualizada");

        Restaurante existente = new Restaurante();
        existente.setId(id);
        existente.setNombre("Restaurante Antiguo");
        existente.setDireccion("Dirección Antigua");

        when(restauranteRepository.findById(id)).thenReturn(Optional.of(existente));
        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(existente);

        Restaurante actualizado = restauranteService.actualizarRestaurante(id,dto);

        assertEquals("Restaurante Actualizado", actualizado.getNombre());
        assertEquals("Dirección Actualizada", actualizado.getDireccion());
        verify(restauranteRepository, times(1)).findById(id);
        verify(restauranteRepository, times(1)).save(existente);
    }

    @Test
    public void testEliminarRestaurante(){
        Long id = 1L;
        doNothing().when(restauranteRepository).deleteById(id);
        restauranteService.eliminarRestaurante(id);
        verify(restauranteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testRestauranteNoEncontrado(){
        Long id = 1L;
        when(restauranteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restauranteService.actualizarRestaurante(id, new RestauranteDTO()));
    }
}
