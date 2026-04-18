SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'area'
              AND COLUMN_NAME = 'id_sede'
        ),
        'ALTER TABLE area RENAME COLUMN id_sede TO sede_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo'
              AND COLUMN_NAME = 'id_usuario_asignado'
        ),
        'ALTER TABLE equipo RENAME COLUMN id_usuario_asignado TO usuario_asignado_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo'
              AND COLUMN_NAME = 'id_area'
        ),
        'ALTER TABLE equipo RENAME COLUMN id_area TO area_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo'
              AND COLUMN_NAME = 'id_sistema_operativo'
        ),
        'ALTER TABLE equipo RENAME COLUMN id_sistema_operativo TO sistema_operativo_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'impresora'
              AND COLUMN_NAME = 'id_sede'
        ),
        'ALTER TABLE impresora RENAME COLUMN id_sede TO sede_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'impresora'
              AND COLUMN_NAME = 'id_area'
        ),
        'ALTER TABLE impresora RENAME COLUMN id_area TO area_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'telefono'
              AND COLUMN_NAME = 'id_usuario_asignado'
        ),
        'ALTER TABLE telefono RENAME COLUMN id_usuario_asignado TO usuario_asignado_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'monitor'
              AND COLUMN_NAME = 'id_equipo'
        ),
        'ALTER TABLE monitor RENAME COLUMN id_equipo TO equipo_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'prestamo'
              AND COLUMN_NAME = 'id_equipo'
        ),
        'ALTER TABLE prestamo RENAME COLUMN id_equipo TO equipo_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo_software'
              AND COLUMN_NAME = 'id_equipo'
        ),
        'ALTER TABLE equipo_software RENAME COLUMN id_equipo TO equipo_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo_software'
              AND COLUMN_NAME = 'id_software'
        ),
        'ALTER TABLE equipo_software RENAME COLUMN id_software TO software_id',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo_software'
              AND CONSTRAINT_NAME = 'fk_equipo_software_equipo'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE equipo_software DROP FOREIGN KEY fk_equipo_software_equipo',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo_software'
              AND CONSTRAINT_NAME = 'fk_equipo_software_software'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE equipo_software DROP FOREIGN KEY fk_equipo_software_software',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'area'
              AND CONSTRAINT_NAME = 'fk_area_sede'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE area DROP FOREIGN KEY fk_area_sede',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo'
              AND CONSTRAINT_NAME = 'fk_equipo_usuario_asignado'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE equipo DROP FOREIGN KEY fk_equipo_usuario_asignado',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo'
              AND CONSTRAINT_NAME = 'fk_equipo_area'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE equipo DROP FOREIGN KEY fk_equipo_area',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'equipo'
              AND CONSTRAINT_NAME = 'fk_equipo_sistema_operativo'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE equipo DROP FOREIGN KEY fk_equipo_sistema_operativo',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'impresora'
              AND CONSTRAINT_NAME = 'fk_impresora_sede'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE impresora DROP FOREIGN KEY fk_impresora_sede',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'impresora'
              AND CONSTRAINT_NAME = 'fk_impresora_area'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE impresora DROP FOREIGN KEY fk_impresora_area',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'telefono'
              AND CONSTRAINT_NAME = 'fk_telefono_usuario_asignado'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE telefono DROP FOREIGN KEY fk_telefono_usuario_asignado',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'monitor'
              AND CONSTRAINT_NAME = 'fk_monitor_equipo'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE monitor DROP FOREIGN KEY fk_monitor_equipo',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'prestamo'
              AND CONSTRAINT_NAME = 'fk_prestamo_equipo'
              AND CONSTRAINT_TYPE = 'FOREIGN KEY'
        ),
        'ALTER TABLE prestamo DROP FOREIGN KEY fk_prestamo_equipo',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

ALTER TABLE sede
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT;

ALTER TABLE usuario_asignado
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT;

ALTER TABLE sistema_operativo
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT;

ALTER TABLE software
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT;

ALTER TABLE administrador
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT;

ALTER TABLE area
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN sede_id INT NOT NULL;

ALTER TABLE equipo
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN usuario_asignado_id INT NULL,
    MODIFY COLUMN area_id INT NOT NULL,
    MODIFY COLUMN sistema_operativo_id INT NOT NULL;

ALTER TABLE impresora
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN sede_id INT NOT NULL,
    MODIFY COLUMN area_id INT NULL;

ALTER TABLE telefono
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN usuario_asignado_id INT NULL;

ALTER TABLE monitor
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN equipo_id INT NOT NULL;

ALTER TABLE prestamo
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN equipo_id INT NOT NULL;

ALTER TABLE historial_traslado
    MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN activo_id INT NOT NULL;

ALTER TABLE equipo_software
    MODIFY COLUMN equipo_id INT NOT NULL,
    MODIFY COLUMN software_id INT NOT NULL;

ALTER TABLE area
    ADD CONSTRAINT fk_area_sede FOREIGN KEY (sede_id) REFERENCES sede (id);

ALTER TABLE equipo
    ADD CONSTRAINT fk_equipo_usuario_asignado FOREIGN KEY (usuario_asignado_id) REFERENCES usuario_asignado (id),
    ADD CONSTRAINT fk_equipo_area FOREIGN KEY (area_id) REFERENCES area (id),
    ADD CONSTRAINT fk_equipo_sistema_operativo FOREIGN KEY (sistema_operativo_id) REFERENCES sistema_operativo (id);

ALTER TABLE impresora
    ADD CONSTRAINT fk_impresora_sede FOREIGN KEY (sede_id) REFERENCES sede (id),
    ADD CONSTRAINT fk_impresora_area FOREIGN KEY (area_id) REFERENCES area (id);

ALTER TABLE telefono
    ADD CONSTRAINT fk_telefono_usuario_asignado FOREIGN KEY (usuario_asignado_id) REFERENCES usuario_asignado (id);

ALTER TABLE monitor
    ADD CONSTRAINT fk_monitor_equipo FOREIGN KEY (equipo_id) REFERENCES equipo (id);

ALTER TABLE prestamo
    ADD CONSTRAINT fk_prestamo_equipo FOREIGN KEY (equipo_id) REFERENCES equipo (id);

ALTER TABLE equipo_software
    ADD CONSTRAINT fk_equipo_software_equipo FOREIGN KEY (equipo_id) REFERENCES equipo (id),
    ADD CONSTRAINT fk_equipo_software_software FOREIGN KEY (software_id) REFERENCES software (id);
