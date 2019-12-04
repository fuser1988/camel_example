package com.somospnt.camel.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Date;

@JacksonXmlRootElement(namespace = "urn:stackify:jacksonxml", localName = "Feriado")
public class Feriado{
    private String descripcion;
    private String tipo;
    private Date fecha;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Feriado " + this.tipo + " - " + this.descripcion;
    }

}
