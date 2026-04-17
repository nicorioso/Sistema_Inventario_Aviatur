# Estructura frontend para SGIA con Thymeleaf

## Enfoque

El frontend vive dentro del mismo proyecto Spring Boot. No se maneja como un proyecto separado.

- `templates/`: vistas HTML renderizadas por Thymeleaf.
- `static/`: archivos estaticos del frontend.

## Estructura propuesta

```text
src/main/resources/
├── templates/
│   ├── layouts/
│   ├── fragments/
│   ├── pages/
│   │   ├── home/
│   │   ├── auth/
│   │   ├── equipos/
│   │   ├── sedes/
│   │   ├── areas/
│   │   ├── usuarios-asignados/
│   │   ├── software/
│   │   ├── sistemas-operativos/
│   │   ├── prestamos/
│   │   ├── administradores/
│   │   └── monitores/
│   └── error/
└── static/
    ├── css/
    │   ├── base/
    │   ├── components/
    │   └── pages/
    ├── js/
    │   ├── base/
    │   └── pages/
    ├── img/
    │   ├── branding/
    │   ├── icons/
    │   └── placeholders/
    └── fonts/
```

## Rol de cada carpeta

- `layouts/`: plantillas base como layout principal, layout de login o layout de errores.
- `fragments/`: piezas reutilizables como header, sidebar, navbar, footer, breadcrumbs, tablas y modales.
- `pages/`: vistas finales agrupadas por modulo del negocio.
- `error/`: paginas para errores como 403, 404 y 500.

- `css/base/`: variables, reset, tipografias, utilidades y tema global.
- `css/components/`: estilos reutilizables de botones, tablas, formularios, cards, badges y modales.
- `css/pages/`: estilos propios de cada modulo o pantalla.

- `js/base/`: scripts globales del sistema.
- `js/pages/`: scripts especificos de una vista o modulo.

## Convenciones recomendadas

- Organizar vistas por dominio, no por tipo de archivo.
- Evitar poner HTML suelto directamente en `templates/`.
- Reutilizar fragmentos para encabezado, navegacion, alertas y tablas.
- Mantener los nombres de carpetas alineados con el negocio.
- No mezclar logica de negocio en Thymeleaf.
- Si una pantalla necesita mucho JavaScript, mantenerlo en `static/js/pages`.

## Flujo mental de trabajo

1. `controller` recibe la peticion.
2. `service` prepara los datos.
3. el controlador retorna una vista de `templates/pages/...`.
4. la vista usa un layout de `templates/layouts` y fragmentos de `templates/fragments`.
5. la vista carga CSS y JS desde `static`.

## Ejemplo de crecimiento sano

Para el modulo de equipos, mas adelante podrias tener:

- `templates/pages/equipos/lista.html`
- `templates/pages/equipos/detalle.html`
- `templates/pages/equipos/formulario.html`
- `static/css/pages/equipos.css`
- `static/js/pages/equipos.js`

## Recomendacion practica

Empieza con estos fragmentos cuando llegue el momento de construir vistas:

- `fragments/head`
- `fragments/header`
- `fragments/sidebar`
- `fragments/footer`
- `fragments/alerts`
- `layouts/main`
- `layouts/auth`

Eso te da una base limpia para todo el sistema.
