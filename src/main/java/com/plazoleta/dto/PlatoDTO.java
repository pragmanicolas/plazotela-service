package com.plazoleta.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private Long restauranteId;
}
