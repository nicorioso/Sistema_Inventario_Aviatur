# Preparacion inicial del proyecto SGIA

## Decision tecnica inicial

- Framework base: Spring Boot con Thymeleaf, Spring Security, JPA, Validation y Actuator.
- Base de datos: MySQL.
- Migraciones: Flyway desde el inicio para evitar depender de `ddl-auto=update`.
- Configuracion por perfiles: `local`, `dev` y `prod`.
- Arquitectura base prevista: `config`, `controller`, `service`, `repository`, `model`, `dto`, `mapper`, `exception`.

## Convenciones recomendadas

- Paquete raiz actual: `com.aviatur.sgia`.
- Entidades separadas de DTOs.
- Validacion en capa `service`, no en controladores.
- Ninguna logica de negocio en plantillas Thymeleaf.
- Todas las tablas deben crearse por migraciones versionadas en `src/main/resources/db/migration`.

## Estructura sugerida

```text
src/main/java/com/aviatur/sgia/
├── config/
├── controller/
├── dto/
├── exception/
├── mapper/
├── model/
├── repository/
└── service/
```

## Perfiles

- `local`: desarrollo local con MySQL local o Docker.
- `dev`: ambiente de pruebas integradas.
- `prod`: ambiente productivo con cache de Thymeleaf activa.

## Siguientes pasos recomendados

1. Definir la primera migracion Flyway con catalogos y tablas base.
2. Crear entidades JPA a partir del modelo conceptual.
3. Configurar seguridad inicial con login para administradores.
4. Definir convenciones de nombres para inventario, seriales y estados.
5. Modelar DTOs y reglas de validacion por modulo.
