package com.aviatur.sgia.config.security;

import com.aviatur.sgia.model.Administrador;
import com.aviatur.sgia.repository.AdministradorRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminBootstrapService implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminBootstrapService.class);

    private final AdministradorRepository administradorRepository;
    private final SecurityProperties securityProperties;
    private final PasswordEncoder passwordEncoder;

    public AdminBootstrapService(
            AdministradorRepository administradorRepository,
            SecurityProperties securityProperties,
            PasswordEncoder passwordEncoder
    ) {
        this.administradorRepository = administradorRepository;
        this.securityProperties = securityProperties;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        String nombre = sanitize(securityProperties.getAdmin().getNombre());
        String username = sanitize(securityProperties.getAdmin().getUsername());
        String configuredPassword = sanitize(securityProperties.getAdmin().getPassword());

        if (username.isEmpty() && configuredPassword.isEmpty()) {
            boolean hasAdmins = administradorRepository.count() > 0;
            boolean tempRegistrationEnabled = securityProperties.getAdmin().isTempRegistrationEnabled();

            if (hasAdmins) {
                log.info("No se definio administrador bootstrap por configuracion. Se usaran los administradores existentes.");
                return;
            }

            if (tempRegistrationEnabled) {
                log.warn("No existe administrador bootstrap configurado. El sistema inicia apoyandose en el registro temporal habilitado.");
                return;
            }

            throw new IllegalStateException(
                    "Debe definir app.security.admin.username y app.security.admin.password, "
                            + "o habilitar temporalmente app.security.admin.temp-registration-enabled=true"
            );
        }

        if (username.isEmpty() || configuredPassword.isEmpty()) {
            throw new IllegalStateException(
                    "La configuracion del administrador bootstrap esta incompleta: debe definir "
                            + "app.security.admin.username y app.security.admin.password"
            );
        }

        Administrador administrador = administradorRepository.findByUsername(username)
                .orElseGet(Administrador::new);

        boolean isNew = administrador.getId() == null;
        boolean updated = false;
        String normalizedNombre = nombre.isEmpty() ? "Administrador General" : nombre;

        if (!normalizedNombre.equals(administrador.getNombre())) {
            administrador.setNombre(normalizedNombre);
            updated = true;
        }

        if (!username.equals(administrador.getUsername())) {
            administrador.setUsername(username);
            updated = true;
        }

        if (shouldUpdatePassword(administrador, configuredPassword)) {
            administrador.setPassword(resolvePasswordHash(configuredPassword));
            updated = true;
        }

        if (isNew || updated) {
            administradorRepository.save(administrador);
        }

        if (isNew) {
            log.info("Administrador '{}' creado o inicializado en base de datos.", username);
            return;
        }

        if (!updated) {
            log.info("Administrador '{}' verificado sin cambios pendientes.", username);
            return;
        }

        log.info("Administrador '{}' sincronizado con la configuración segura de inicio.", username);
    }

    private boolean shouldUpdatePassword(Administrador administrador, String configuredPassword) {
        if (securityProperties.getAdmin().isPasswordEncoded()) {
            return !configuredPassword.equals(administrador.getPassword());
        }

        String storedPassword = administrador.getPassword();
        return storedPassword == null || !passwordEncoder.matches(configuredPassword, storedPassword);
    }

    private String resolvePasswordHash(String configuredPassword) {
        if (securityProperties.getAdmin().isPasswordEncoded()) {
            return configuredPassword;
        }

        return passwordEncoder.encode(configuredPassword);
    }

    private String sanitize(String value) {
        return value == null ? "" : value.trim();
    }
}
