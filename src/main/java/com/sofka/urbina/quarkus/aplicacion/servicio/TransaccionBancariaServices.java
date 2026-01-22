package com.sofka.urbina.quarkus.aplicacion.servicio;

import com.sofka.urbina.quarkus.aplicacion.port.in.BancoExternoPort;
import com.sofka.urbina.quarkus.aplicacion.port.in.CrearTransacionBancariaUseCase;
import com.sofka.urbina.quarkus.aplicacion.port.out.TransaccionBancariaRepositoryPort;
import com.sofka.urbina.quarkus.dominio.modelo.EstadoTransaccion;
import com.sofka.urbina.quarkus.dominio.modelo.ResultadoTransferencia;
import com.sofka.urbina.quarkus.dominio.modelo.TransaccionBancaria;
import com.sofka.urbina.quarkus.dominio.modelo.Transferencia;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import java.util.concurrent.*;

@ApplicationScoped
@RequiredArgsConstructor
public class TransaccionBancariaServices implements CrearTransacionBancariaUseCase {

    private final TransaccionBancariaRepositoryPort repository;
    private final BancoExternoPort bancoExternoPort;

    @ConfigProperty(name = "banco.externo.timeout-ms", defaultValue = "2000")
    long timeoutMs;

    @ConfigProperty(name = "banco.externo.reintentos", defaultValue = "5")
    int maxReintentos;

    @Override
    public TransaccionBancaria crearTransacionBancaria(TransaccionBancaria transaccion) {

        // 1️⃣ Registrar estado inicial
        transaccion.setEstado(EstadoTransaccion.PENDIENTE.toString());
        TransaccionBancaria guardada = guardar(transaccion);

        ResultadoTransferencia resultado = ejecutarConReintentos(guardada);

        // 3️⃣ Actualizar estado final
        if ("00".equals(resultado.codigo())) {
            guardada.setEstado(EstadoTransaccion.EXITOSA.toString());
        } else {
            guardada.setEstado(EstadoTransaccion.FALLIDA.toString());
        }

        return actualizar(guardada);
    }

    @Transactional
    protected TransaccionBancaria guardar(TransaccionBancaria transaccion) {
        return repository.save(transaccion);
    }

    @Transactional
    protected TransaccionBancaria actualizar(TransaccionBancaria transaccion) {
        return repository.update(transaccion);
    }

    private ResultadoTransferencia ejecutarConReintentos(TransaccionBancaria transaccion) {
        int intento = 0;

        while (intento < maxReintentos) {
            try {
                intento++;

                return ejecutarConTimeout(transaccion);

            } catch (Exception e) {
                if (intento >= maxReintentos) {
                    return new ResultadoTransferencia("99", "Timeout o error tras reintentos");
                }
            }
        }

        return new ResultadoTransferencia("99", "Error desconocido");
    }

    private ResultadoTransferencia ejecutarConTimeout(TransaccionBancaria transaccion) throws Exception {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<ResultadoTransferencia> future = executor.submit(() ->
                    bancoExternoPort.transferir(
                            new Transferencia("123", "456", transaccion.getMonto())
                    )
            );

            return future.get(timeoutMs, TimeUnit.MILLISECONDS);

        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout banco externo");

        } finally {
            executor.shutdown();
        }
    }
}
