package com.plazoleta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestauranteDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String propietario;
}
