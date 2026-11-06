package com.mycompany.prog3_jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factura_venta")
public class FacturaVenta extends AuditoriaApp {
    private Long numero;

    @Column(nullable = false)
    private LocalDate fechaEmision;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CondicionIva condicionIva;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TipoMoneda tipoMoneda;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PuntoVenta puntoVenta;

    private double importeCobrado;
    private double importeSaldo;

    @Column(nullable = false)
    private double importeTotal;

    private String cae;
    private LocalDate caeFechaVencimiento;
    private String resultadoAfip;
    private String motivoRechazo;

    @Column(nullable = false)
    private String estado;

    private LocalDate fechaAnulacion;
    private String observaciones;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<FacturaVentaDetalle> detalles = new ArrayList<>();

    public Long getNumero() { return numero; }
    public void setNumero(Long numero) { this.numero = numero; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public CondicionIva getCondicionIva() { return condicionIva; }
    public void setCondicionIva(CondicionIva condicionIva) { this.condicionIva = condicionIva; }
    public TipoMoneda getTipoMoneda() { return tipoMoneda; }
    public void setTipoMoneda(TipoMoneda tipoMoneda) { this.tipoMoneda = tipoMoneda; }
    public PuntoVenta getPuntoVenta() { return puntoVenta; }
    public void setPuntoVenta(PuntoVenta puntoVenta) { this.puntoVenta = puntoVenta; }
    public double getImporteCobrado() { return importeCobrado; }
    public void setImporteCobrado(double importeCobrado) { this.importeCobrado = importeCobrado; }
    public double getImporteSaldo() { return importeSaldo; }
    public void setImporteSaldo(double importeSaldo) { this.importeSaldo = importeSaldo; }
    public double getImporteTotal() { return importeTotal; }
    public void setImporteTotal(double importeTotal) { this.importeTotal = importeTotal; }
    public String getCae() { return cae; }
    public void setCae(String cae) { this.cae = cae; }
    public LocalDate getCaeFechaVencimiento() { return caeFechaVencimiento; }
    public void setCaeFechaVencimiento(LocalDate caeFechaVencimiento) { this.caeFechaVencimiento = caeFechaVencimiento; }
    public String getResultadoAfip() { return resultadoAfip; }
    public void setResultadoAfip(String resultadoAfip) { this.resultadoAfip = resultadoAfip; }
    public String getMotivoRechazo() { return motivoRechazo; }
    public void setMotivoRechazo(String motivoRechazo) { this.motivoRechazo = motivoRechazo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getFechaAnulacion() { return fechaAnulacion; }
    public void setFechaAnulacion(LocalDate fechaAnulacion) { this.fechaAnulacion = fechaAnulacion; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public List<FacturaVentaDetalle> getDetalles() { return detalles; }
    public void setDetalles(List<FacturaVentaDetalle> detalles) { this.detalles = detalles; }
    public void addDetalle(FacturaVentaDetalle d) {
        d.setFactura(this);
        this.detalles.add(d);
    }

    @Override
    public String toString() { return "FacturaVenta{id=" + getId() + ", estado='" + estado + "'}"; }
}