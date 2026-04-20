package com.aviatur.sgia.config.security;

import com.aviatur.sgia.model.Administrador;
import com.aviatur.sgia.repository.AdministradorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministradorUserDetailsService implements UserDetailsService {

    private final AdministradorRepository administradorRepository;

    public AdministradorUserDetailsService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String normalizedUsername = username == null ? "" : username.trim();

        Administrador administrador = administradorRepository.findByUsername(normalizedUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Administrador no encontrado"));

        return new AdministradorPrincipal(administrador);
    }
}
