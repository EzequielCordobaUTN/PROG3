package com.mycompany.prog3_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class AuditoriaApp extends EntityId {
    @Column(nullable = false)
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    @Column(nullable = false)
    private LocalDate fechaModificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioCarga;

    @ManyToOne
    private Usuario usuarioBaja;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioModificacion;

    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
    public LocalDate getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(LocalDate fechaBaja) { this.fechaBaja = fechaBaja; }
    public LocalDate getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(LocalDate fechaModificacion) { this.fechaModificacion = fechaModificacion; }
    public Usuario getUsuarioCarga() { return usuarioCarga; }
    public void setUsuarioCarga(Usuario usuarioCarga) { this.usuarioCarga = usuarioCarga; }
    public Usuario getUsuarioBaja() { return usuarioBaja; }
    public void setUsuarioBaja(Usuario usuarioBaja) { this.usuarioBaja = usuarioBaja; }
    public Usuario getUsuarioModificacion() { return usuarioModificacion; }
    public void setUsuarioModificacion(Usuario usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }
}
