package com.mycompany.act1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medico")
public class Medico extends BaseEntity {
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    private String especialidad;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas = new ArrayList<>();

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public List<Consulta> getConsultas() { return consultas; }

    @Override
    public String toString() { return "Medico{id=" + getId() + ", " + nombre + " " + apellido + "}"; }
}
