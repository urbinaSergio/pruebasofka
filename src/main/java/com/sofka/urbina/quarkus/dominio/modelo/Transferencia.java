package com.sofka.urbina.quarkus.dominio.modelo;

import java.math.BigDecimal;

public record Transferencia(
        String cuentaOrigen,
        String cuentaDestino,
        BigDecimal monto
) {}
