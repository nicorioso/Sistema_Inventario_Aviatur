package com.aviatur.sgia.service.sede;

import com.aviatur.sgia.model.Sede;

import java.util.List;

public interface SedeService {

    List<Sede> listarSedes();

    Sede obtenerSedePorId(Long id);

    Sede crearSede(Sede sede);

    Sede actualizarSede(Long id, Sede sedeActualizada);

    void eliminarSede(Long id);
}
