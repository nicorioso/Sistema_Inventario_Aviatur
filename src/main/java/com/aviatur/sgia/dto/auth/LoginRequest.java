package com.aviatur.sgia.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "El username es obligatorio")
        @Size(max = 80, message = "El username no puede superar 80 caracteres")
        String username,

        @NotBlank(message = "La contrasena es obligatoria")
        @Size(max = 255, message = "La contrasena no puede superar 255 caracteres")
        String password
) {
}
