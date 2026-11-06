package com.mycompany.act1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica extends BaseEntity {
    @Column(nullable = false)
    private String descripcion;

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private Paciente paciente;

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    @Override
    public String toString() { return "HistoriaClinica{id=" + getId() + "}"; }
}
