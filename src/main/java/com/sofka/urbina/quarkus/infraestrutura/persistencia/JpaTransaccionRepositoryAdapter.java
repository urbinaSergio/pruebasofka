package com.sofka.urbina.quarkus.infraestrutura.persistencia;

import com.sofka.urbina.quarkus.aplicacion.port.out.TransaccionBancariaRepositoryPort;
import com.sofka.urbina.quarkus.dominio.modelo.TransaccionBancaria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class JpaTransaccionRepositoryAdapter
        implements TransaccionBancariaRepositoryPort {

    private final QuarkusDateTransaccionBancariaRepository repository;

    @Override
    public TransaccionBancaria save(TransaccionBancaria transaccionBancaria) {
        TransaccionBancariaEntity entity =
                new TransaccionBancariaEntity(
                        transaccionBancaria.getId(),
                        transaccionBancaria.getEstado(),
                        transaccionBancaria.getMonto(),
                        transaccionBancaria.getNombre()
                );

        repository.persist(entity);

        return new TransaccionBancaria(
                entity.getId(),
                entity.getEstado(),
                entity.getMonto(),
                entity.getNombre()
        );
    }

    @Override
    public TransaccionBancaria update(TransaccionBancaria transaccionBancaria) {
        TransaccionBancariaEntity entity =
                new TransaccionBancariaEntity(
                        transaccionBancaria.getId(),
                        transaccionBancaria.getEstado(),
                        transaccionBancaria.getMonto(),
                        transaccionBancaria.getNombre()
                );

        repository.getEntityManager().merge(entity);

        return new TransaccionBancaria(
                entity.getId(),
                entity.getEstado(),
                entity.getMonto(),
                entity.getNombre()
        );
    }
}
