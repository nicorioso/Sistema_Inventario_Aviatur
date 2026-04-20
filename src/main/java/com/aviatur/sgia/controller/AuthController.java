package com.aviatur.sgia.controller;

import com.aviatur.sgia.config.security.AdministradorPrincipal;
import com.aviatur.sgia.config.security.SecurityProperties;
import com.aviatur.sgia.dto.auth.AdminRegisterRequest;
import com.aviatur.sgia.dto.auth.AuthUserResponse;
import com.aviatur.sgia.dto.auth.CsrfTokenResponse;
import com.aviatur.sgia.dto.auth.LoginRequest;
import com.aviatur.sgia.exception.InvalidOperationException;
import com.aviatur.sgia.model.Administrador;
import com.aviatur.sgia.service.admin.AdministradorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AdministradorService administradorService;
    private final SecurityProperties securityProperties;

    public AuthController(
            AuthenticationManager authenticationManager,
            AdministradorService administradorService,
            SecurityProperties securityProperties
    ) {
        this.authenticationManager = authenticationManager;
        this.administradorService = administradorService;
        this.securityProperties = securityProperties;
    }

    @GetMapping("/csrf")
    public ResponseEntity<CsrfTokenResponse> csrf(CsrfToken csrfToken) {
        return ResponseEntity.ok(new CsrfTokenResponse(
                csrfToken.getHeaderName(),
                csrfToken.getParameterName(),
                csrfToken.getToken()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthUserResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpServletRequest
    ) {
        Authentication authenticatedUser = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.username().trim(),
                        request.password()
                )
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticatedUser);
        SecurityContextHolder.setContext(context);
        httpServletRequest.getSession(true)
                .setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        return ResponseEntity.ok(toResponse(authenticatedUser));
    }

    @GetMapping("/me")
    public ResponseEntity<AuthUserResponse> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(toResponse(authentication));
    }

    @PostMapping("/register-temp")
    public ResponseEntity<AuthUserResponse> registerTemp(
            @Valid @RequestBody AdminRegisterRequest request,
            Authentication authentication
    ) {
        if (!securityProperties.getAdmin().isTempRegistrationEnabled()) {
            throw new InvalidOperationException("El registro temporal de administradores esta deshabilitado");
        }

        boolean hasAdmins = administradorService.existeAlgunAdministrador();
        boolean isAuthenticatedAdmin = authentication != null
                && authentication.isAuthenticated()
                && authentication.getAuthorities().stream().anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));

        if (hasAdmins && !isAuthenticatedAdmin) {
            throw new InvalidOperationException(
                    "Solo un administrador autenticado puede registrar nuevos administradores"
            );
        }

        Administrador administrador = administradorService.registrarAdministrador(
                request.nombre(),
                request.username(),
                request.password()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/administradores/" + administrador.getId())
                .body(new AuthUserResponse(
                        administrador.getId(),
                        administrador.getNombre(),
                        administrador.getUsername(),
                        List.of("ROLE_ADMIN")
                ));
    }

    private AuthUserResponse toResponse(Authentication authentication) {
        AdministradorPrincipal principal = (AdministradorPrincipal) authentication.getPrincipal();

        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new AuthUserResponse(
                principal.getId(),
                principal.getNombre(),
                principal.getUsername(),
                roles
        );
    }
}
