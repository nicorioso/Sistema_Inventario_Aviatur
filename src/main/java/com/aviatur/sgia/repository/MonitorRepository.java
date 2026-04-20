package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MonitorRepository extends JpaRepository<Monitor, Integer> {

    boolean existsByInventario(String inventario);

    boolean existsBySerial(String serial);

    Optional<Monitor> findByInventario(String inventario);

    Optional<Monitor> findBySerial(String serial);

    List<Monitor> findByEquipoIdOrderByInventarioAsc(Integer equipoId);
}
