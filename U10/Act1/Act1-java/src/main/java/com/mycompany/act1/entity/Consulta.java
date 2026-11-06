package com.mycompany.act1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "consulta")
public class Consulta extends BaseEntity {
    @Column(nullable = false)
    private LocalDate fecha;
    private String diagnostico;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Medico medico;

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    @Override
    public String toString() { return "Consulta{id=" + getId() + ", fecha=" + fecha + ", diag=" + diagnostico + "}"; }
}
