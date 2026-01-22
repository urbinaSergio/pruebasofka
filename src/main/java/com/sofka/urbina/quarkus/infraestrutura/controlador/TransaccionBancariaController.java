package com.sofka.urbina.quarkus.infraestrutura.controlador;

import com.sofka.urbina.quarkus.aplicacion.port.in.CrearTransacionBancariaUseCase;
import com.sofka.urbina.quarkus.dominio.modelo.TransaccionBancaria;
import com.sofka.urbina.quarkus.infraestrutura.controlador.dto.TransaccionBancariaRequest;
import com.sofka.urbina.quarkus.infraestrutura.controlador.dto.TransaccionBancariaResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/transaccion")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class TransaccionBancariaController {

    private final CrearTransacionBancariaUseCase crearTransacionBancariaUseCase;

    @POST
    public TransaccionBancariaResponse crearTransaccionBancaria(
            TransaccionBancariaRequest request) {

        validarRequest(request);

        TransaccionBancaria transaccion = new TransaccionBancaria(
                null,
                request.estado(),
                request.monto(),
                request.nombre()
        );

        TransaccionBancaria creada =
                crearTransacionBancariaUseCase.crearTransacionBancaria(transaccion);

        return TransaccionBancariaResponse.fromDomain(creada);
    }

    private void validarRequest(TransaccionBancariaRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("La transacción es obligatoria");
        }
        if (request.nombre() == null || request.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
    }
}
