package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Telefono;
import com.aviatur.sgia.model.enums.EstadoActivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelefonoRepository extends JpaRepository<Telefono, Integer> {

    boolean existsByMac(String mac);

    boolean existsBySerial(String serial);

    Optional<Telefono> findByMac(String mac);

    Optional<Telefono> findBySerial(String serial);

    List<Telefono> findByUsuarioAsignadoIdOrderByModeloAsc(Integer usuarioAsignadoId);

    List<Telefono> findByEstadoOrderByModeloAsc(EstadoActivo estado);
}
