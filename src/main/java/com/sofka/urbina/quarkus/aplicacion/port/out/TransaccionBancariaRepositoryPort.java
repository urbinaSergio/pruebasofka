package com.sofka.urbina.quarkus.aplicacion.port.out;

import com.sofka.urbina.quarkus.dominio.modelo.TransaccionBancaria;
import jakarta.enterprise.context.ApplicationScoped;


public interface TransaccionBancariaRepositoryPort {
        TransaccionBancaria save(TransaccionBancaria transaccionBancaria);

        TransaccionBancaria update(TransaccionBancaria transaccionBancaria);
}
