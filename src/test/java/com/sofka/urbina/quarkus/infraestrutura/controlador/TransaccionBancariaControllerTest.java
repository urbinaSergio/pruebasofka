package com.sofka.urbina.quarkus.infraestrutura.controlador;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;



import javax.inject.Inject;


import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TransaccionBancariaControllerTest {

    @Inject
    Client client; // Inyectamos un cliente JAX-RS de Quarkus

    @Test
    void debeCrearTransaccion() {
        String json = """
                {
                  "estado": "PENDIENTE",
                  "monto": 1000,
                  "nombre": "Sergio"
                }
                """;

        Response response = client.target("http://localhost:8080/transaccion")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));

        assertEquals(200, response.getStatus(), "La transacción debería crearse correctamente");
        response.close();
    }
}
