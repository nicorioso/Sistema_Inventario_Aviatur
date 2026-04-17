package com.aviatur.sgia.model;

import com.aviatur.sgia.model.enums.TipoEquipo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "equipo")
public class Equipo extends ActivoTecnologico {

    @Column(name = "nombre_pc", nullable = false, length = 120)
    private String nombrePc;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoEquipo tipo;

    @Column(name = "modelo", nullable = false, length = 120)
    private String modelo;

    @Column(name = "serial", nullable = false, unique = true, length = 120)
    private String serial;

    @Column(name = "inventario", nullable = false, unique = true, length = 120)
    private String inventario;

    @Column(name = "procesador", nullable = false, length = 120)
    private String procesador;

    @Column(name = "ram", nullable = false, length = 60)
    private String ram;

    @Column(name = "almacenamiento", nullable = false, length = 120)
    private String almacenamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_asignado_id")
    private UsuarioAsignado usuarioAsignado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sistema_operativo_id", nullable = false)
    private SistemaOperativo sistemaOperativo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "equipo_software",
            joinColumns = @JoinColumn(name = "equipo_id"),
            inverseJoinColumns = @JoinColumn(name = "software_id")
    )
    private Set<Software> softwareInstalado = new LinkedHashSet<>();

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Monitor> monitores = new ArrayList<>();

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Prestamo> prestamos = new ArrayList<>();

    public String getNombrePc() {
        return nombrePc;
    }

    public void setNombrePc(String nombrePc) {
        this.nombrePc = nombrePc;
    }

    public TipoEquipo getTipo() {
        return tipo;
    }

    public void setTipo(TipoEquipo tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public UsuarioAsignado getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(UsuarioAsignado usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public SistemaOperativo getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public Set<Software> getSoftwareInstalado() {
        return softwareInstalado;
    }

    public void setSoftwareInstalado(Set<Software> softwareInstalado) {
        this.softwareInstalado = softwareInstalado;
    }

    public List<Monitor> getMonitores() {
        return monitores;
    }

    public void setMonitores(List<Monitor> monitores) {
        this.monitores = monitores;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
