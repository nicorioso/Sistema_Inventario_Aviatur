package com.aviatur.sgia.dto.auth;

import java.util.List;

public record AuthUserResponse(
        Integer id,
        String nombre,
        String username,
        List<String> roles
) {
}
