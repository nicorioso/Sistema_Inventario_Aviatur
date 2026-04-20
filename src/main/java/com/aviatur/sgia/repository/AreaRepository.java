package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Integer> {

    List<Area> findBySedeIdOrderByNombreAsc(Integer sedeId);

    boolean existsBySedeId(Integer sedeId);

    boolean existsByNombreIgnoreCaseAndSedeId(String nombre, Integer sedeId);
}
