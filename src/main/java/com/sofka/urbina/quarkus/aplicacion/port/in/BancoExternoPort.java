package com.sofka.urbina.quarkus.aplicacion.port.in;

import com.sofka.urbina.quarkus.dominio.modelo.ResultadoTransferencia;
import com.sofka.urbina.quarkus.dominio.modelo.Transferencia;

public interface BancoExternoPort {

    ResultadoTransferencia transferir(Transferencia transferencia);


}
