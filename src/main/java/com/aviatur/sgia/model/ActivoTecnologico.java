package com.aviatur.sgia.model;

import com.aviatur.sgia.model.enums.EstadoActivo;
import com.aviatur.sgia.model.enums.EstadoActivoConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ActivoTecnologico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = EstadoActivoConverter.class)
    @Column(name = "estado", nullable = false, length = 20,
            columnDefinition = "ENUM('ACTIVO','DAÑADO','OBSOLETO','ACARGO')")
    private EstadoActivo estado = EstadoActivo.ACTIVO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoActivo getEstado() {
        return estado;
    }

    public void setEstado(EstadoActivo estado) {
        this.estado = estado;
    }
}
