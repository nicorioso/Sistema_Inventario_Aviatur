package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Equipo;
import com.aviatur.sgia.model.enums.EstadoActivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    boolean existsBySerial(String serial);

    boolean existsByInventario(String inventario);

    Optional<Equipo> findBySerial(String serial);

    Optional<Equipo> findByInventario(String inventario);

    List<Equipo> findByAreaIdOrderByNombrePcAsc(Integer areaId);

    List<Equipo> findByUsuarioAsignadoIdOrderByNombrePcAsc(Integer usuarioAsignadoId);

    List<Equipo> findByEstadoOrderByNombrePcAsc(EstadoActivo estado);
}
