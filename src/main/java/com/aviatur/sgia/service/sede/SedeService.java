package com.aviatur.sgia.service.sede;

import com.aviatur.sgia.model.Sede;

import java.util.List;

public interface SedeService {

    List<Sede> listarSedes();

    Sede obtenerSedePorId(Integer id);

    Sede crearSede(Sede sede);

    Sede actualizarSede(Integer id, Sede sedeActualizada);

    void eliminarSede(Integer id);
}
