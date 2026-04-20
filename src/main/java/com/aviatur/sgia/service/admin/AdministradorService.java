package com.aviatur.sgia.service.admin;

import com.aviatur.sgia.model.Administrador;

public interface AdministradorService {

    Administrador registrarAdministrador(String nombre, String username, String password);

    boolean existeAlgunAdministrador();
}
