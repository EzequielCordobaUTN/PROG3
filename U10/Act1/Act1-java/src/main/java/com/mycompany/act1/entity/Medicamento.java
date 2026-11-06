package com.mycompany.act1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicamento")
public class Medicamento extends BaseEntity {
    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Paciente paciente;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    @Override
    public String toString() { return "Medicamento{id=" + getId() + ", " + nombre + "}"; }
}
