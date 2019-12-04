package com.somospnt.camel.domain;

import java.io.Serializable;
import java.util.Date;

public class Feriado implements Serializable {

    private Long id;
//    @JacksonXmlRootElemen(isAttribute=true)
    private String descripcion;
    private String tipo;
    private Date fecha;
    private String equipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Feriado " + this.tipo + " - " + this.descripcion;
    }

}
