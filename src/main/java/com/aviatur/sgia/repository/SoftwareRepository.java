package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Software;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoftwareRepository extends JpaRepository<Software, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

    Optional<Software> findByNombreIgnoreCase(String nombre);
}
