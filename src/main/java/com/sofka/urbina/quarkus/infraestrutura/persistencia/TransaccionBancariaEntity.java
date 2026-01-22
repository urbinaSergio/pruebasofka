package com.sofka.urbina.quarkus.infraestrutura.persistencia;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transacionbancaria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionBancariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private String nombre;

}
