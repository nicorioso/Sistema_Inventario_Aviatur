package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.SistemaOperativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SistemaOperativoRepository extends JpaRepository<SistemaOperativo, Long> {

    boolean existsByNombreIgnoreCaseAndVersionIgnoreCase(String nombre, String version);

    Optional<SistemaOperativo> findByNombreIgnoreCaseAndVersionIgnoreCase(String nombre, String version);
}
