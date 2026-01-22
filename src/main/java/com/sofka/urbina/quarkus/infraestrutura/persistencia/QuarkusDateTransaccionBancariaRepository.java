package com.sofka.urbina.quarkus.infraestrutura.persistencia;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuarkusDateTransaccionBancariaRepository implements PanacheRepository<TransaccionBancariaEntity> {
}
