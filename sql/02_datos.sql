-- =====================================================================
--  Epicentro Gourmet - Carga de datos de prueba
--  Se ejecuta despues de 01_schema.sql
-- =====================================================================
USE epicentro_gourmet;

SET FOREIGN_KEY_CHECKS = 0;

-- --------------------------------------------------------------- FESTIVAL
INSERT INTO festival (id, nombre, temporada, fecha_inicio, fecha_fin) VALUES
(1, 'Epicentro Gourmet Verano',   'VERANO',   '2026-01-09', '2026-01-18'),
(2, 'Epicentro Gourmet Invierno', 'INVIERNO', '2026-07-10', '2026-07-19');

-- ------------------------------------------------------------------ STAFF
INSERT INTO staff (id, tipo_staff, nombre, apellido, dni, fecha_nacimiento, fecha_ingreso, sueldo_base, especialidad, turno, festival_id, unidad_venta_id) VALUES
(1, 'COCINERO', 'Martin', 'Aguirre',   '30111222', '1983-04-12', '2015-03-01', 850000, 'Parrilla',        NULL, 1, 1),
(2, 'COCINERO', 'Yuki',   'Tanaka',    '94222333', '1991-09-03', '2019-07-15', 920000, 'Cocina japonesa', NULL, 1, 2),
(3, 'COCINERO', 'Lucia',  'Fernandez', '35333444', '1990-01-25', '2021-02-01', 780000, 'Cocina mexicana', NULL, 1, 3),
(4, 'COCINERO', 'Ana',    'Rossi',     '41444555', '1999-11-08', '2023-09-01', 640000, 'Pasteleria',      NULL, 2, 4),
(5, 'CAJERO',   'Diego',  'Perez',     '33555666', '1986-06-30', '2017-05-10', 610000, NULL, 'MANIANA', 1, 1),
(6, 'CAJERO',   'Sofia',  'Gomez',     '42666777', '2000-03-17', '2022-11-01', 580000, NULL, 'NOCHE',   1, 2),
(7, 'CAJERO',   'Nahuel', 'Quiroga',   '45777888', '2007-08-20', '2025-01-20', 520000, NULL, 'TARDE',   1, 3);

-- ----------------------------------------------------------- UNIDAD_VENTA
INSERT INTO unidad_venta (id, tipo_unidad, codigo, nombre_comercial, superficie_m2, patente, requiere_electricidad, cantidad_carpas, tiempo_montaje_min, responsable_id, festival_id) VALUES
(1, 'FOODTRACK', 'FT-001', 'La Parrilla Rodante', 14.5, 'AB123CD', TRUE,  NULL, NULL, 1, 1),
(2, 'FOODTRACK', 'FT-002', 'Tokyo Street Food',   12.0, 'AC456EF', TRUE,  NULL, NULL, 2, 1),
(3, 'PUESTO',    'PD-001', 'Cantina Azteca',      20.0, NULL,      NULL,  2,    90,   3, 1),
(4, 'PUESTO',    'PD-002', 'Dulce Invierno',       8.5, NULL,      NULL,  1,    45,   4, 2);

-- ------------------------------------------------------------------ PLATO
INSERT INTO plato (id, nombre, precio_venta, costo_produccion, unidad_venta_id) VALUES
(1, 'Empanadas de carne (x6)', 7200,  3100, 1),
(2, 'Choripan completo',       5500,  2000, 1),
(3, 'Milanesa napolitana',    11800,  5400, 1),
(4, 'Ramen de cerdo',         13500,  6200, 2),
(5, 'Gyozas (x8)',             8900,  3800, 2),
(6, 'Tacos al pastor (x3)',    9600,  4100, 3),
(7, 'Burrito vegetariano',     8200,  3300, 3),
(8, 'Helado artesanal',        4500,  1500, 4),
(9, 'Limonada de menta',       3200,   900, 4),
(10,'Flan con dulce de leche', 5100,  1800, 4);

-- ----------------------------------------------------------------- PEDIDO
INSERT INTO pedido (id, fecha, festival_id, unidad_venta_id) VALUES
(1, '2026-01-10', 1, 1),
(2, '2026-01-12', 1, 1),
(3, '2026-01-11', 1, 2),
(4, '2026-01-15', 1, 2),
(5, '2026-01-13', 1, 3),
(6, '2026-07-11', 2, 4);

-- ------------------------------------------------------------ ITEM_PEDIDO
INSERT INTO item_pedido (id, cantidad, precio_unidad, plato_id, pedido_id) VALUES
(1,  2,  7200, 1, 1),
(2,  3,  5500, 2, 1),
(3,  1, 11800, 3, 2),
(4,  1,  7200, 1, 2),
(5,  4, 13500, 4, 3),
(6,  2,  8900, 5, 3),
(7,  2, 13500, 4, 4),
(8,  5,  9600, 6, 5),
(9,  2,  8200, 7, 5),
(10, 6, 4500, 8, 6),
(11, 4, 3200, 9, 6);

SET FOREIGN_KEY_CHECKS = 1;

-- Control rapido: Verano = 213100, Invierno = 39800
SELECT f.nombre, SUM(i.cantidad * i.precio_unidad) AS recaudado
FROM pedido p
JOIN festival f    ON f.id = p.festival_id
JOIN item_pedido i ON i.pedido_id = p.id
GROUP BY f.id, f.nombre;
