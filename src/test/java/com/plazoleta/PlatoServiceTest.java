package com.plazoleta;

import com.plazoleta.dto.PlatoDTO;
import com.plazoleta.entity.Plato;
import com.plazoleta.entity.Restaurante;
import com.plazoleta.repository.PlatoRepository;
import com.plazoleta.repository.RestauranteRepository;
import com.plazoleta.service.PlatoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlatoServiceTest {

    @Autowired
    private PlatoServiceImpl platoService;

    @MockBean
    private PlatoRepository platoRepository;

    @MockBean
    private RestauranteRepository restauranteRepository;

    @Test
    public void testCrearPlato(){
        PlatoDTO dto = new PlatoDTO();
        dto.setNombre("Plato 1");
        dto.setPrecio(15.0);
        dto.setRestauranteId(1L);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);

        Plato plato = new Plato();
        plato.setNombre("Plato 1");
        plato.setPrecio(15.0);
        plato.setRestaurante(restaurante);

        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restaurante));
        when(platoRepository.save(any(Plato.class))).thenReturn(plato);

        Plato resultado = platoService.crearPlato(dto);

        assertEquals("Plato 1", resultado.getNombre());
        assertEquals(15.0, resultado.getPrecio());
        verify(platoRepository, times(1)).save(any(Plato.class));
    }

    @Test
    public void testListarPlatosPorRestaurante(){
        Long restauranteId = 1L;

        List<Plato> platos = new ArrayList<>();
        platos.add(new Plato());
        when(platoRepository.findByRestauranteId(restauranteId)).thenReturn(platos);
        List<Plato> resultado = platoService.listarPlatosPorRestaurante(restauranteId);
        assertFalse(resultado.isEmpty());
        verify(platoRepository, times(1)).findByRestauranteId(restauranteId);
    }

    @Test
    public void testActualizarPlato(){
        Long id = 1L;
        PlatoDTO dto = new PlatoDTO();
        dto.setNombre("Plato Actualizado");
        dto.setPrecio(20.0);

        Plato plato = new Plato();
        plato.setId(id);
        plato.setNombre("Plato Antiguo");
        plato.setPrecio(15.0);

        when(platoRepository.findById(id)).thenReturn(Optional.of(plato));
        when(platoRepository.save(any(Plato.class))).thenReturn(plato);

        Plato resultado = platoService.actualizarPlato(id, dto);

        assertEquals("Plato Actualizado", resultado.getNombre());
        assertEquals(20.0, resultado.getPrecio());
        verify(platoRepository,times(1)).save(any(Plato.class));

    }

    @Test
    public void testEliminarPlato(){
        Long id = 1L;
        doNothing().when(platoRepository).deleteById(id);

        platoService.eliminarPlato(id);
        verify(platoRepository, times(1)).deleteById(id);
    }
}
