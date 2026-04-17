package com.aviatur.sgia.controller;

import com.aviatur.sgia.dto.sede.SedeRequest;
import com.aviatur.sgia.dto.sede.SedeResponse;
import com.aviatur.sgia.model.Sede;
import com.aviatur.sgia.service.sede.SedeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sedes")
public class SedeController {

    private final SedeService sedeService;

    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping
    public ResponseEntity<List<SedeResponse>> listarSedes() {
        List<SedeResponse> response = sedeService.listarSedes()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeResponse> obtenerSedePorId(@PathVariable Long id) {
        Sede sede = sedeService.obtenerSedePorId(id);
        return ResponseEntity.ok(toResponse(sede));
    }

    @PostMapping
    public ResponseEntity<SedeResponse> crearSede(@Valid @RequestBody SedeRequest request) {
        Sede sedeCreada = sedeService.crearSede(toModel(request));
        return ResponseEntity
                .created(URI.create("/api/sedes/" + sedeCreada.getId()))
                .body(toResponse(sedeCreada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SedeResponse> actualizarSede(
            @PathVariable Long id,
            @Valid @RequestBody SedeRequest request
    ) {
        Sede sedeActualizada = sedeService.actualizarSede(id, toModel(request));
        return ResponseEntity.ok(toResponse(sedeActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSede(@PathVariable Long id) {
        sedeService.eliminarSede(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private Sede toModel(SedeRequest request) {
        Sede sede = new Sede();
        sede.setNombre(request.getNombre());
        sede.setDireccion(request.getDireccion());
        return sede;
    }

    private SedeResponse toResponse(Sede sede) {
        return new SedeResponse(
                sede.getId(),
                sede.getNombre(),
                sede.getDireccion()
        );
    }
}
