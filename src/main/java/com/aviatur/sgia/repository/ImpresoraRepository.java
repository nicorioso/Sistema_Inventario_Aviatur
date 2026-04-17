package com.aviatur.sgia.repository;

import com.aviatur.sgia.model.Impresora;
import com.aviatur.sgia.model.enums.EstadoActivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImpresoraRepository extends JpaRepository<Impresora, Long> {

    boolean existsBySerial(String serial);

    boolean existsByIp(String ip);

    Optional<Impresora> findBySerial(String serial);

    Optional<Impresora> findByIp(String ip);

    List<Impresora> findBySedeIdOrderBySerialAsc(Long sedeId);

    List<Impresora> findByAreaIdOrderBySerialAsc(Long areaId);

    boolean existsBySedeId(Long sedeId);

    List<Impresora> findByEstadoOrderBySerialAsc(EstadoActivo estado);
}
