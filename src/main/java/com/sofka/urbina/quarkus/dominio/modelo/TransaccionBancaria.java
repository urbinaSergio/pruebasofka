package com.sofka.urbina.quarkus.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class TransaccionBancaria {
    private Long id;
    private String estado;
    private BigDecimal monto;
    private String nombre;

}
