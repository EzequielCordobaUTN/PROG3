package com.mycompany.prog3_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "marca")
public class Marca extends AuditoriaApp {
    @Column(nullable = false)
    private String denominacion;
    @Column(nullable = false)
    private Integer codigo;

    public String getDenominacion() { return denominacion; }
    public void setDenominacion(String denominacion) { this.denominacion = denominacion; }
    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }

    @Override
    public String toString() { return "Marca{id=" + getId() + ", denominacion='" + denominacion + "'}"; }
}