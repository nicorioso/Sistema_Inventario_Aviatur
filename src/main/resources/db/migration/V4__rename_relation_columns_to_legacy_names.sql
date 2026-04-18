SET @sql = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'area'
              AND COLUMN_NAME = 'sede_id'
        ),
        'ALTER TABLE area RENAME COLUMN sede_id TO id_sede',
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
              AND COLUMN_NAME = 'usuario_asignado_id'
        ),
        'ALTER TABLE equipo RENAME COLUMN usuario_asignado_id TO id_usuario_asignado',
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
              AND COLUMN_NAME = 'area_id'
        ),
        'ALTER TABLE equipo RENAME COLUMN area_id TO id_area',
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
              AND COLUMN_NAME = 'sistema_operativo_id'
        ),
        'ALTER TABLE equipo RENAME COLUMN sistema_operativo_id TO id_sistema_operativo',
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
              AND COLUMN_NAME = 'sede_id'
        ),
        'ALTER TABLE impresora RENAME COLUMN sede_id TO id_sede',
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
              AND COLUMN_NAME = 'area_id'
        ),
        'ALTER TABLE impresora RENAME COLUMN area_id TO id_area',
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
              AND COLUMN_NAME = 'usuario_asignado_id'
        ),
        'ALTER TABLE telefono RENAME COLUMN usuario_asignado_id TO id_usuario_asignado',
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
              AND COLUMN_NAME = 'equipo_id'
        ),
        'ALTER TABLE monitor RENAME COLUMN equipo_id TO id_equipo',
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
              AND COLUMN_NAME = 'equipo_id'
        ),
        'ALTER TABLE prestamo RENAME COLUMN equipo_id TO id_equipo',
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
              AND COLUMN_NAME = 'equipo_id'
        ),
        'ALTER TABLE equipo_software RENAME COLUMN equipo_id TO id_equipo',
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
              AND COLUMN_NAME = 'software_id'
        ),
        'ALTER TABLE equipo_software RENAME COLUMN software_id TO id_software',
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
              AND TABLE_NAME = 'historial_traslado'
              AND COLUMN_NAME = 'activo_id'
        ),
        'ALTER TABLE historial_traslado RENAME COLUMN activo_id TO id_activo',
        'SELECT 1'
    )
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
