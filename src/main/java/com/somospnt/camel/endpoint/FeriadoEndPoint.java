package com.somospnt.camel.endpoint;

import com.somospnt.camel.domain.Feriado;
import java.util.List;

public interface FeriadoEndPoint {

    void crearEndPoint(List<Feriado> feriados);
}
