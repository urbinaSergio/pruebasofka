package com.sofka.urbina.quarkus.infraestrutura.controlador.dto;

import java.math.BigDecimal;

public record TransaccionBancariaRequest(
        String estado,
        BigDecimal monto,
        String nombre
) {
}
