package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    List<Area> findBySedeIdOrderByNombreAsc(Long sedeId);

    boolean existsBySedeId(Long sedeId);

    boolean existsByNombreIgnoreCaseAndSedeId(String nombre, Long sedeId);
}
