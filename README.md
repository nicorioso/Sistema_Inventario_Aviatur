# SGIA Backend

Backend del Sistema de Gestion de Equipos de Computo para Aviatur Medellin.

## Stack base

- Java 21
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Security
- Spring Data JPA
- Flyway
- MySQL

## Perfiles configurados

- `local`
- `dev`
- `prod`

Por defecto el proyecto arranca con `local`.

## Archivos importantes

- `src/main/resources/application.properties`
- `src/main/resources/application-local.properties`
- `src/main/resources/application-dev.properties`
- `src/main/resources/application-prod.properties`
- `.env.example`
- `compose.yaml`
- `docs/backend-structure.md`
- `docs/frontend-structure.md`

## Base de datos local

1. Copiar `.env.example` a `.env`.
2. Levantar MySQL con `docker compose up -d`.
3. Usar las mismas credenciales del `.env` para la aplicacion.

## Seguridad

- El acceso a `/api/**` requiere autenticacion con rol `ADMIN`.
- Usuario admin configurable por variables:
  - `ADMIN_USERNAME`
  - `ADMIN_PASSWORD`
  - `ADMIN_PASSWORD_ENCODED` (`true` si `ADMIN_PASSWORD` ya viene en hash BCrypt)
- CORS configurable con `CORS_ALLOWED_ORIGINS` (lista separada por comas).
- CSRF habilitado con cookie `XSRF-TOKEN`; las peticiones mutables (`POST`, `PUT`, `DELETE`, etc.) deben enviar el header `X-XSRF-TOKEN`.
- En `prod` se fuerza HTTPS (`SECURITY_REQUIRE_HTTPS=true`) y cookie de sesion segura.

## Notas de preparacion

- Se dejo Flyway listo desde el inicio para manejar el esquema de base de datos con migraciones.
- La estructura de paquetes ya esta separada por capas bajo `com.aviatur.sgia`.
- El frontend Thymeleaf ya tiene una estructura base por `layouts`, `fragments` y `pages`.
- No se agrego logica de negocio ni entidades del dominio en esta preparacion.

## Proximo paso

Crear la primera migracion y luego modelar entidades JPA segun el ERS.
