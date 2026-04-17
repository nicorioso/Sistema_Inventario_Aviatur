package com.aviatur.sgia.service.sede.impl;

import com.aviatur.sgia.exception.DuplicateResourceException;
import com.aviatur.sgia.exception.InvalidOperationException;
import com.aviatur.sgia.exception.ResourceNotFoundException;
import com.aviatur.sgia.model.Sede;
import com.aviatur.sgia.repository.AreaRepository;
import com.aviatur.sgia.repository.ImpresoraRepository;
import com.aviatur.sgia.repository.SedeRepository;
import com.aviatur.sgia.service.sede.SedeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SedeServiceImpl implements SedeService {

    private final SedeRepository sedeRepository;
    private final AreaRepository areaRepository;
    private final ImpresoraRepository impresoraRepository;

    public SedeServiceImpl(
            SedeRepository sedeRepository,
            AreaRepository areaRepository,
            ImpresoraRepository impresoraRepository
    ) {
        this.sedeRepository = sedeRepository;
        this.areaRepository = areaRepository;
        this.impresoraRepository = impresoraRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sede> listarSedes() {
        return sedeRepository.findAllByOrderByNombreAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Sede obtenerSedePorId(Long id) {
        validateId(id);
        return findSedeOrThrow(id);
    }

    @Override
    public Sede crearSede(Sede sede) {
        validatePayload(sede);

        String nombreNormalizado = normalizeRequired(sede.getNombre(), "El nombre de la sede es obligatorio");
        String direccionNormalizada = normalizeRequired(sede.getDireccion(), "La direccion de la sede es obligatoria");

        if (sedeRepository.existsByNombreIgnoreCase(nombreNormalizado)) {
            throw new DuplicateResourceException("Ya existe una sede con el nombre '" + nombreNormalizado + "'");
        }

        Sede nuevaSede = new Sede();
        nuevaSede.setNombre(nombreNormalizado);
        nuevaSede.setDireccion(direccionNormalizada);

        return sedeRepository.save(nuevaSede);
    }

    @Override
    public Sede actualizarSede(Long id, Sede sedeActualizada) {
        validateId(id);
        validatePayload(sedeActualizada);

        Sede sedeExistente = findSedeOrThrow(id);

        String nombreNormalizado = normalizeRequired(
                sedeActualizada.getNombre(),
                "El nombre de la sede es obligatorio"
        );
        String direccionNormalizada = normalizeRequired(
                sedeActualizada.getDireccion(),
                "La direccion de la sede es obligatoria"
        );

        if (sedeRepository.existsByNombreIgnoreCaseAndIdNot(nombreNormalizado, id)) {
            throw new DuplicateResourceException("Ya existe otra sede con el nombre '" + nombreNormalizado + "'");
        }

        sedeExistente.setNombre(nombreNormalizado);
        sedeExistente.setDireccion(direccionNormalizada);

        return sedeRepository.save(sedeExistente);
    }

    @Override
    public void eliminarSede(Long id) {
        validateId(id);
        Sede sede = findSedeOrThrow(id);

        if (areaRepository.existsBySedeId(id)) {
            throw new InvalidOperationException(
                    "No se puede eliminar la sede '" + sede.getNombre() + "' porque tiene areas asociadas"
            );
        }

        if (impresoraRepository.existsBySedeId(id)) {
            throw new InvalidOperationException(
                    "No se puede eliminar la sede '" + sede.getNombre() + "' porque tiene impresoras asociadas"
            );
        }

        sedeRepository.delete(sede);
    }

    private Sede findSedeOrThrow(Long id) {
        return sedeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe una sede con id " + id));
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la sede debe ser un valor positivo");
        }
    }

    private void validatePayload(Sede sede) {
        if (sede == null) {
            throw new IllegalArgumentException("La informacion de la sede es obligatoria");
        }
    }

    private String normalizeRequired(String value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }

        String normalized = value.trim().replaceAll("\\s{2,}", " ");
        if (normalized.isBlank()) {
            throw new IllegalArgumentException(message);
        }

        return normalized;
    }
}
