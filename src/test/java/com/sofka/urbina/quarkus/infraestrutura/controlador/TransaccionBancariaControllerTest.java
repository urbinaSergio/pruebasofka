package com.sofka.urbina.quarkus.infraestrutura.controlador;

import io.quarkus.test.junit.QuarkusTest;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class TransaccionBancariaControllerTest {

    @Test
    void debeCrearTransaccion() {

        Client client = ClientBuilder.newClient();

        String json = """
            {
              "estado": "PENDIENTE",
              "monto": 1000,
              "nombre": "Sergio"
            }
            """;

        Response response = client
                .target("http://localhost:8080/transaccion")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));

        assertEquals(200, response.getStatus());

        response.close();
        client.close(); // 🔴 MUY IMPORTANTE
    }
}
