package com.sofka.urbina.quarkus.aplicacion.port.in;

import com.sofka.urbina.quarkus.dominio.modelo.TransaccionBancaria;
import jakarta.enterprise.context.ApplicationScoped;


public interface CrearTransacionBancariaUseCase {

    TransaccionBancaria crearTransacionBancaria(TransaccionBancaria transacionBancaria);
}
