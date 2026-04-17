package com.aviatur.sgia.dto.sede;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SedeRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar los 120 caracteres")
    private String nombre;

    @NotBlank(message = "La direccion es obligatoria")
    @Size(max = 255, message = "La direccion no puede superar los 255 caracteres")
    private String direccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
