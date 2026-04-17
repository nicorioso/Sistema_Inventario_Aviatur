package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    boolean existsByEquipoIdAndFechaDevolucionIsNull(Long equipoId);

    List<Prestamo> findByEquipoIdOrderByFechaInicioDesc(Long equipoId);

    List<Prestamo> findByCedulaOrderByFechaInicioDesc(String cedula);
}
