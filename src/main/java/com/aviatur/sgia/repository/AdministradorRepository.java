package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    boolean existsByUsername(String username);

    Optional<Administrador> findByUsername(String username);
}
