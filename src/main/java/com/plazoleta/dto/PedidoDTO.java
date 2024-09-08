package com.plazoleta.dto;

import com.plazoleta.entity.DetallePedido;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {

    private Long id;
    private LocalDateTime fecha;
    private String estado;
    private Long restauranteId;
    private List<DetallePedido> detalles;
}
