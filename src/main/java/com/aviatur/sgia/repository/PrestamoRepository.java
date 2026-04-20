package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    boolean existsByEquipoIdAndFechaDevolucionIsNull(Integer equipoId);

    List<Prestamo> findByEquipoIdOrderByFechaInicioDesc(Integer equipoId);

    List<Prestamo> findByCedulaOrderByFechaInicioDesc(String cedula);
}
