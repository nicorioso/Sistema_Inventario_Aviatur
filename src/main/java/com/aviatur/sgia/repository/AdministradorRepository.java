package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    boolean existsByUsername(String username);

    Optional<Administrador> findByUsername(String username);
}
