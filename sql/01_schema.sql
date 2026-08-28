DROP DATABASE IF EXISTS epicentro_gourmet;

CREATE DATABASE epicentro_gourmet
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE epicentro_gourmet;


-- ================================================================
-- FESTIVAL
-- ================================================================

CREATE TABLE festival (
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    nombre        VARCHAR(80)  NOT NULL,
    temporada     VARCHAR(40),
    fecha_inicio  DATE,
    fecha_fin     DATE,

    PRIMARY KEY (id)
) ENGINE=InnoDB;



CREATE TABLE staff (
    id                BIGINT       NOT NULL AUTO_INCREMENT,
    tipo_staff        VARCHAR(20)  NOT NULL,
    nombre            VARCHAR(60)  NOT NULL,
    apellido          VARCHAR(60)  NOT NULL,
    dni               VARCHAR(15)  NOT NULL,
    fecha_nacimiento  DATE,
    fecha_ingreso     DATE,
    sueldo_base       DOUBLE,
    especialidad      VARCHAR(60),
    turno             VARCHAR(20),

    festival_id       BIGINT,
    unidad_venta_id   BIGINT,

    PRIMARY KEY (id),

    CONSTRAINT uk_staff_dni
        UNIQUE (dni)
) ENGINE=InnoDB;


CREATE TABLE unidad_venta (
    id                     BIGINT       NOT NULL AUTO_INCREMENT,
    tipo_unidad             VARCHAR(20)  NOT NULL,
    codigo                  VARCHAR(20)  NOT NULL,
    nombre_comercial        VARCHAR(80)  NOT NULL,
    superficie_m2           DOUBLE,
    patente                 VARCHAR(15),
    requiere_electricidad   BOOLEAN,
    cantidad_carpas         INT,
    tiempo_montaje_min      INT,

    responsable_id          BIGINT,
    festival_id             BIGINT,

    PRIMARY KEY (id),

    CONSTRAINT uk_unidad_codigo
        UNIQUE (codigo)
) ENGINE=InnoDB;


-- ================================================================
-- PLATO
-- ================================================================

CREATE TABLE plato (
    id                BIGINT       NOT NULL AUTO_INCREMENT,
    nombre            VARCHAR(80)  NOT NULL,
    precio_venta      DOUBLE,
    costo_produccion  DOUBLE,
    unidad_venta_id   BIGINT,

    PRIMARY KEY (id)
) ENGINE=InnoDB;


-- ================================================================
-- PEDIDO
-- ================================================================

CREATE TABLE pedido (
    id               BIGINT  NOT NULL AUTO_INCREMENT,
    fecha            DATE    NOT NULL,
    festival_id      BIGINT  NOT NULL,
    unidad_venta_id  BIGINT  NOT NULL,

    PRIMARY KEY (id)
) ENGINE=InnoDB;


-- ================================================================
-- ITEM_PEDIDO
-- ================================================================

CREATE TABLE item_pedido (
    id             BIGINT  NOT NULL AUTO_INCREMENT,
    cantidad       BIGINT  NOT NULL,
    precio_unidad  DOUBLE  NOT NULL,
    plato_id       BIGINT  NOT NULL,
    pedido_id      BIGINT  NOT NULL,

    PRIMARY KEY (id)
) ENGINE=InnoDB;


-- STAFF
ALTER TABLE staff
    ADD CONSTRAINT fk_staff_festival
        FOREIGN KEY (festival_id)
        REFERENCES festival(id),

    ADD CONSTRAINT fk_staff_unidad
        FOREIGN KEY (unidad_venta_id)
        REFERENCES unidad_venta(id);


-- UNIDAD DE VENTA
ALTER TABLE unidad_venta
    ADD CONSTRAINT fk_unidad_responsable
        FOREIGN KEY (responsable_id)
        REFERENCES staff(id),

    ADD CONSTRAINT fk_unidad_festival
        FOREIGN KEY (festival_id)
        REFERENCES festival(id);


-- PLATO
ALTER TABLE plato
    ADD CONSTRAINT fk_plato_unidad
        FOREIGN KEY (unidad_venta_id)
        REFERENCES unidad_venta(id);


-- PEDIDO
ALTER TABLE pedido
    ADD CONSTRAINT fk_pedido_festival
        FOREIGN KEY (festival_id)
        REFERENCES festival(id),

    ADD CONSTRAINT fk_pedido_unidad
        FOREIGN KEY (unidad_venta_id)
        REFERENCES unidad_venta(id);


-- ITEM PEDIDO
ALTER TABLE item_pedido
    ADD CONSTRAINT fk_item_plato
        FOREIGN KEY (plato_id)
        REFERENCES plato(id),

    ADD CONSTRAINT fk_item_pedido
        FOREIGN KEY (pedido_id)
        REFERENCES pedido(id)
        ON DELETE CASCADE;


-- ================================================================
-- ÍNDICES
-- ================================================================

CREATE INDEX idx_pedido_fecha
    ON pedido(fecha);

CREATE INDEX idx_item_plato
    ON item_pedido(plato_id);

CREATE INDEX idx_staff_tipo
    ON staff(tipo_staff);

CREATE INDEX idx_unidad_tipo
    ON unidad_venta(tipo_unidad);