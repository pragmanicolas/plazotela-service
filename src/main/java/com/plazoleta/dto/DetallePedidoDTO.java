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
public class DetallePedidoDTO {

    private Long id;
    private Long platoId;
    private int cantidad;
}
