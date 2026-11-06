package com.mycompany.prog3_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "domicilio")
public class Domicilio extends EntityId {
    private String nombreCalle;
    private String numeroCalle;

    public String getNombreCalle() { return nombreCalle; }
    public void setNombreCalle(String nombreCalle) { this.nombreCalle = nombreCalle; }
    public String getNumeroCalle() { return numeroCalle; }
    public void setNumeroCalle(String numeroCalle) { this.numeroCalle = numeroCalle; }

    @Override
    public String toString() { return "Domicilio{id=" + getId() + ", nombreCalle='" + nombreCalle + "'}"; }
}