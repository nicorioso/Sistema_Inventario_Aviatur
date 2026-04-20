package com.aviatur.sgia.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class EstadoActivoConverter implements AttributeConverter<EstadoActivo, String> {

    @Override
    public String convertToDatabaseColumn(EstadoActivo attribute) {
        if (attribute == null) {
            return null;
        }
        return switch (attribute) {
            case DANADO -> "DAÑADO";
            default -> attribute.name();
        };
    }

    @Override
    public EstadoActivo convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        if ("DAÑADO".equals(dbData)) {
            return EstadoActivo.DANADO;
        }
        return EstadoActivo.valueOf(dbData);
    }
}
