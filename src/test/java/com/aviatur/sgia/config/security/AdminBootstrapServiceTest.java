package com.aviatur.sgia.config.security;

import com.aviatur.sgia.model.Administrador;
import com.aviatur.sgia.repository.AdministradorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminBootstrapServiceTest {

    @Mock
    private AdministradorRepository administradorRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AdminBootstrapService adminBootstrapService;

    @Test
    void shouldCreateAdminWhenItDoesNotExist() throws Exception {
        SecurityProperties properties = new SecurityProperties();
        properties.getAdmin().setNombre("Administrador Corporativo");
        properties.getAdmin().setUsername("admin");
        properties.getAdmin().setPassword("super-segura");

        adminBootstrapService = new AdminBootstrapService(administradorRepository, properties, passwordEncoder);

        when(administradorRepository.findByUsername("admin")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("super-segura")).thenReturn("$2a$12$nueva");
        when(administradorRepository.save(any(Administrador.class))).thenAnswer(invocation -> invocation.getArgument(0));

        adminBootstrapService.run(new DefaultApplicationArguments(new String[0]));

        ArgumentCaptor<Administrador> captor = ArgumentCaptor.forClass(Administrador.class);
        verify(administradorRepository).save(captor.capture());

        Administrador savedAdmin = captor.getValue();
        assertThat(savedAdmin.getNombre()).isEqualTo("Administrador Corporativo");
        assertThat(savedAdmin.getUsername()).isEqualTo("admin");
        assertThat(savedAdmin.getPassword()).isEqualTo("$2a$12$nueva");
    }

    @Test
    void shouldNotRewriteAdminWhenConfigurationMatchesStoredCredentials() throws Exception {
        SecurityProperties properties = new SecurityProperties();
        properties.getAdmin().setNombre("Administrador General");
        properties.getAdmin().setUsername("admin");
        properties.getAdmin().setPassword("super-segura");

        adminBootstrapService = new AdminBootstrapService(administradorRepository, properties, passwordEncoder);

        Administrador administrador = new Administrador();
        administrador.setId(1);
        administrador.setNombre("Administrador General");
        administrador.setUsername("admin");
        administrador.setPassword("$2a$12$existente");

        when(administradorRepository.findByUsername("admin")).thenReturn(Optional.of(administrador));
        when(passwordEncoder.matches("super-segura", "$2a$12$existente")).thenReturn(true);

        adminBootstrapService.run(new DefaultApplicationArguments(new String[0]));

        verify(administradorRepository, never()).save(any(Administrador.class));
    }
}
