# Estructura backend para SGIA con Spring Boot

## Enfoque

El backend se organiza por capas dentro del paquete raiz `com.aviatur.sgia`.

- `controller`: entrada web del sistema.
- `service`: logica de negocio.
- `repository`: acceso a datos.
- `model`: entidades del dominio y persistencia.
- `dto`: objetos para entrada y salida de datos.
- `mapper`: conversion entre entidades y DTOs.
- `config`: configuraciones tecnicas.
- `exception`: manejo centralizado de errores.

## Estructura propuesta

```text
src/main/java/com/aviatur/sgia/
├── config/
├── controller/
├── dto/
├── exception/
├── mapper/
├── model/
├── repository/
├── service/
└── SgiaBackendApplication.java
```

## Rol de cada carpeta

- `config/`: seguridad, configuracion MVC, beans compartidos, propiedades y configuraciones tecnicas del framework.
- `controller/`: recibe peticiones HTTP, llama a servicios y retorna vistas Thymeleaf o redirecciones.
- `dto/`: define datos de entrada y salida sin exponer directamente las entidades JPA.
- `exception/`: excepciones de negocio y manejo global de errores.
- `mapper/`: transforma `dto` a `model` y viceversa.
- `model/`: entidades JPA, enums del dominio y clases ligadas al modelo de negocio persistente.
- `repository/`: interfaces Spring Data JPA para consultar y persistir datos.
- `service/`: contiene validaciones, reglas de negocio, coordinacion entre repositorios y preparacion de datos para controladores.

## Convenciones recomendadas

- Mantener `controller` delgado: solo recibir datos, delegar y responder.
- Centralizar reglas del negocio en `service`.
- No exponer entidades JPA directamente en formularios o vistas si el modulo empieza a crecer.
- Usar `dto` para formularios, filtros, listados y detalles.
- Mantener `repository` enfocado solo en persistencia.
- Reservar `mapper` para conversiones repetidas o relevantes.
- Nombrar clases por responsabilidad real del negocio.

## Flujo mental de trabajo

1. el usuario hace una peticion HTTP.
2. `controller` recibe la peticion y valida lo basico.
3. `service` aplica reglas de negocio.
4. `repository` consulta o guarda datos.
5. `mapper` convierte entidades y DTOs cuando haga falta.
6. `controller` retorna una vista Thymeleaf o una redireccion.

## Ejemplo de crecimiento sano

Para el modulo de equipos, mas adelante podrias tener:

- `controller/EquipoController.java`
- `service/EquipoService.java`
- `repository/EquipoRepository.java`
- `model/Equipo.java`
- `dto/equipo/EquipoRequest.java`
- `dto/equipo/EquipoResponse.java`
- `mapper/EquipoMapper.java`

## Organización interna recomendada cuando el proyecto crezca

Si un modulo empieza a crecer mucho, puedes subdividir por dominio sin romper la arquitectura.

Ejemplo:

```text
dto/
└── equipo/
    ├── EquipoRequest.java
    ├── EquipoResponse.java
    └── EquipoFiltroDto.java
```

Lo mismo puede aplicarse en `service`, `controller` y `mapper`.

## Relacion con la base de datos

- Las tablas deben definirse por migraciones en `src/main/resources/db/migration`.
- `model` representa la estructura persistente.
- `repository` opera sobre ese modelo.
- El backend no debe depender de `ddl-auto=update` para construir esquema.

## Recomendacion practica

Empieza construyendo en este orden:

1. migraciones Flyway
2. `model`
3. `repository`
4. `service`
5. `dto`
6. `mapper`
7. `controller`

Ese orden evita que la capa web termine guiando mal el dominio.
