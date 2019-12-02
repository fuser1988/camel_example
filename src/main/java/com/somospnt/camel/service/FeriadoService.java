package com.somospnt.camel.service;

import com.somospnt.camel.domain.Feriado;

public interface FeriadoService {

    boolean esInamovible(Feriado feriado);
    boolean esMesMayorA6(Feriado feriado);
    Feriado inamovibles(Feriado feriado);
    Feriado mesMayorA6(Feriado feriado);
    Feriado mesMenorA6(Feriado feriado);
    String devolverBody(String body);
}
