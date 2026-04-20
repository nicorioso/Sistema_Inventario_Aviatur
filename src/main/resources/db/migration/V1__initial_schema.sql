CREATE TABLE sede (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(120) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_sede_nombre UNIQUE (nombre)
);

CREATE TABLE usuario_asignado (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    cedula VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_usuario_asignado_cedula UNIQUE (cedula)
);

CREATE TABLE sistema_operativo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    version VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_sistema_operativo_nombre_version UNIQUE (nombre, version)
);

CREATE TABLE software (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_software_nombre UNIQUE (nombre)
);

CREATE TABLE administrador (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    username VARCHAR(80) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_administrador_username UNIQUE (username)
);

CREATE TABLE area (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(120) NOT NULL,
    sede_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_area_nombre_sede UNIQUE (nombre, sede_id),
    CONSTRAINT fk_area_sede FOREIGN KEY (sede_id) REFERENCES sede (id)
);

CREATE TABLE equipo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    estado VARCHAR(20) NOT NULL,
    nombre_pc VARCHAR(120) NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    modelo VARCHAR(120) NOT NULL,
    serial VARCHAR(120) NOT NULL,
    inventario VARCHAR(120) NOT NULL,
    procesador VARCHAR(120) NOT NULL,
    ram VARCHAR(60) NOT NULL,
    almacenamiento VARCHAR(120) NOT NULL,
    usuario_asignado_id BIGINT NULL,
    area_id BIGINT NOT NULL,
    sistema_operativo_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_equipo_serial UNIQUE (serial),
    CONSTRAINT uk_equipo_inventario UNIQUE (inventario),
    CONSTRAINT fk_equipo_usuario_asignado FOREIGN KEY (usuario_asignado_id) REFERENCES usuario_asignado (id),
    CONSTRAINT fk_equipo_area FOREIGN KEY (area_id) REFERENCES area (id),
    CONSTRAINT fk_equipo_sistema_operativo FOREIGN KEY (sistema_operativo_id) REFERENCES sistema_operativo (id)
);

CREATE TABLE impresora (
    id BIGINT NOT NULL AUTO_INCREMENT,
    estado VARCHAR(20) NOT NULL,
    serial VARCHAR(120) NOT NULL,
    ip VARCHAR(45) NULL,
    direccion VARCHAR(255) NOT NULL,
    sede_id BIGINT NOT NULL,
    area_id BIGINT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_impresora_serial UNIQUE (serial),
    CONSTRAINT uk_impresora_ip UNIQUE (ip),
    CONSTRAINT fk_impresora_sede FOREIGN KEY (sede_id) REFERENCES sede (id),
    CONSTRAINT fk_impresora_area FOREIGN KEY (area_id) REFERENCES area (id)
);

CREATE TABLE telefono (
    id BIGINT NOT NULL AUTO_INCREMENT,
    estado VARCHAR(20) NOT NULL,
    mac VARCHAR(17) NOT NULL,
    serial VARCHAR(120) NOT NULL,
    modelo VARCHAR(120) NOT NULL,
    referencia VARCHAR(120) NOT NULL,
    usuario_asignado_id BIGINT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_telefono_mac UNIQUE (mac),
    CONSTRAINT uk_telefono_serial UNIQUE (serial),
    CONSTRAINT fk_telefono_usuario_asignado FOREIGN KEY (usuario_asignado_id) REFERENCES usuario_asignado (id)
);

CREATE TABLE monitor (
    id BIGINT NOT NULL AUTO_INCREMENT,
    inventario VARCHAR(120) NOT NULL,
    serial VARCHAR(120) NOT NULL,
    pulgadas DECIMAL(5,2) NOT NULL,
    equipo_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_monitor_inventario UNIQUE (inventario),
    CONSTRAINT uk_monitor_serial UNIQUE (serial),
    CONSTRAINT fk_monitor_equipo FOREIGN KEY (equipo_id) REFERENCES equipo (id)
);

CREATE TABLE prestamo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre_empleado VARCHAR(150) NOT NULL,
    cedula VARCHAR(20) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NULL,
    fecha_devolucion DATE NULL,
    equipo_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_prestamo_equipo FOREIGN KEY (equipo_id) REFERENCES equipo (id)
);

CREATE TABLE historial_traslado (
    id BIGINT NOT NULL AUTO_INCREMENT,
    tipo_activo VARCHAR(20) NOT NULL,
    activo_id BIGINT NOT NULL,
    fecha DATETIME NOT NULL,
    origen VARCHAR(255) NOT NULL,
    destino VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE equipo_software (
    equipo_id BIGINT NOT NULL,
    software_id BIGINT NOT NULL,
    PRIMARY KEY (equipo_id, software_id),
    CONSTRAINT fk_equipo_software_equipo FOREIGN KEY (equipo_id) REFERENCES equipo (id),
    CONSTRAINT fk_equipo_software_software FOREIGN KEY (software_id) REFERENCES software (id)
);
