package com.aviatur.sgia.config.security;

import com.aviatur.sgia.model.Administrador;
import com.aviatur.sgia.repository.AdministradorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdministradorUserDetailsServiceTest {

    @Mock
    private AdministradorRepository administradorRepository;

    @InjectMocks
    private AdministradorUserDetailsService userDetailsService;

    @Test
    void shouldLoadAdminFromDatabase() {
        Administrador administrador = new Administrador();
        administrador.setId(7);
        administrador.setNombre("Admin SGIA");
        administrador.setUsername("admin");
        administrador.setPassword("$2a$12$hash");

        when(administradorRepository.findByUsername("admin")).thenReturn(Optional.of(administrador));

        AdministradorPrincipal principal = (AdministradorPrincipal) userDetailsService.loadUserByUsername(" admin ");

        assertThat(principal.getId()).isEqualTo(7);
        assertThat(principal.getNombre()).isEqualTo("Admin SGIA");
        assertThat(principal.getUsername()).isEqualTo("admin");
        assertThat(principal.getAuthorities())
                .extracting("authority")
                .containsExactly("ROLE_ADMIN");
    }

    @Test
    void shouldFailWhenAdminDoesNotExist() {
        when(administradorRepository.findByUsername("admin")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userDetailsService.loadUserByUsername("admin"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("Administrador no encontrado");
    }
}
