package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.HistorialTraslado;
import com.aviatur.sgia.model.enums.TipoActivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialTrasladoRepository extends JpaRepository<HistorialTraslado, Integer> {

    List<HistorialTraslado> findByTipoActivoAndActivoIdOrderByFechaDesc(TipoActivo tipoActivo, Integer activoId);
}
