package com.sofka.urbina.quarkus.infraestrutura.bancoexterno;

import com.sofka.urbina.quarkus.aplicacion.port.in.BancoExternoPort;
import com.sofka.urbina.quarkus.dominio.modelo.ResultadoTransferencia;
import com.sofka.urbina.quarkus.dominio.modelo.Transferencia;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;

@ApplicationScoped
public class BancoExternoMockAdapter  implements BancoExternoPort {
    @Override
    public ResultadoTransferencia transferir(Transferencia transferencia) {
        // Simulación simple
        if (transferencia.monto().compareTo(BigDecimal.ZERO) <= 0) {
            return new ResultadoTransferencia("99", "Monto inválido");
        }

        return new ResultadoTransferencia("00", "Transferencia aprobada (MOCK)");
    }
}

