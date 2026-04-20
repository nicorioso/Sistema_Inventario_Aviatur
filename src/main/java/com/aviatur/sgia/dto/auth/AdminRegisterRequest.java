package com.aviatur.sgia.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminRegisterRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
        String nombre,

        @NotBlank(message = "El username es obligatorio")
        @Size(min = 4, max = 80, message = "El username debe tener entre 4 y 80 caracteres")
        String username,

        @NotBlank(message = "La contrasena es obligatoria")
        @Size(min = 12, max = 72, message = "La contrasena debe tener entre 12 y 72 caracteres")
        String password
) {
}
