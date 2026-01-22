package com.sofka.urbina.quarkus.infraestrutura.controlador.dto;

import com.sofka.urbina.quarkus.dominio.modelo.TransaccionBancaria;

import java.math.BigDecimal;

public record TransaccionBancariaResponse(
        Long id,
        String estado,
        BigDecimal monto,
        String nombre
) {
    public static TransaccionBancariaResponse fromDomain(TransaccionBancaria t) {
        return new TransaccionBancariaResponse(
                t.getId(),
                t.getEstado(),
                t.getMonto(),
                t.getNombre()
        );
    }
}
