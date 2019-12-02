package com.somospnt.camel.service;

import com.somospnt.camel.domain.Feriado;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class FeriadoServiceImpl implements FeriadoService {

    @Override
    public boolean esInamovible(Feriado feriado) {
        return feriado.getTipo().equals("INAMOVIBLE");
    }

    @Override
    public boolean esMesMayorA6(Feriado feriado) {
        return feriado.getFecha().toInstant().isAfter(Instant.now());
    }

    @Override
    public Feriado inamovibles(Feriado feriado) {
        feriado.setEquipo("Es Inamovible");
        return feriado;
    }

    @Override
    public Feriado mesMayorA6(Feriado feriado) {
        feriado.setEquipo("Es mayor a 6");
        return feriado;
    }

    @Override
    public Feriado mesMenorA6(Feriado feriado) {
        feriado.setEquipo("Es menor a 6");
        return feriado;
    }

    @Override
    public String devolverBody(String body) {
        return body;
    }

}
