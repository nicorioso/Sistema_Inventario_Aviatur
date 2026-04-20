package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.UsuarioAsignado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioAsignadoRepository extends JpaRepository<UsuarioAsignado, Integer> {

    boolean existsByCedula(String cedula);

    Optional<UsuarioAsignado> findByCedula(String cedula);
}
