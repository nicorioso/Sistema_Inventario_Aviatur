package com.aviatur.sgia.dto.auth;

public record CsrfTokenResponse(
        String headerName,
        String parameterName,
        String token
) {
}
