package com.aviatur.sgia.service.admin.impl;

import com.aviatur.sgia.exception.DuplicateResourceException;
import com.aviatur.sgia.exception.InvalidOperationException;
import com.aviatur.sgia.model.Administrador;
import com.aviatur.sgia.repository.AdministradorRepository;
import com.aviatur.sgia.service.admin.AdministradorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder;

    public AdministradorServiceImpl(
            AdministradorRepository administradorRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.administradorRepository = administradorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Administrador registrarAdministrador(String nombre, String username, String password) {
        String normalizedNombre = sanitize(nombre);
        String normalizedUsername = sanitize(username);

        if (normalizedNombre.isEmpty()) {
            throw new InvalidOperationException("El nombre del administrador es obligatorio");
        }

        if (!normalizedUsername.matches("^[A-Za-z0-9._-]{4,80}$")) {
            throw new InvalidOperationException(
                    "El username solo puede contener letras, numeros, punto, guion y guion bajo"
            );
        }

        validatePassword(password);

        if (administradorRepository.existsByUsername(normalizedUsername)) {
            throw new DuplicateResourceException("Ya existe un administrador con ese username");
        }

        Administrador administrador = new Administrador();
        administrador.setNombre(normalizedNombre);
        administrador.setUsername(normalizedUsername);
        administrador.setPassword(passwordEncoder.encode(password));

        return administradorRepository.save(administrador);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeAlgunAdministrador() {
        return administradorRepository.count() > 0;
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 12 || password.length() > 72) {
            throw new InvalidOperationException("La contrasena debe tener entre 12 y 72 caracteres");
        }

        boolean hasUppercase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLowercase = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));

        if (!hasUppercase || !hasLowercase || !hasDigit || !hasSpecial) {
            throw new InvalidOperationException(
                    "La contrasena debe incluir mayuscula, minuscula, numero y caracter especial"
            );
        }
    }

    private String sanitize(String value) {
        return value == null ? "" : value.trim();
    }
}
