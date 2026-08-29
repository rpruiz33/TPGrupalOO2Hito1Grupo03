-- =====================================================================
--  Epicentro Gourmet - Consultas SQL
--  Cada consulta tiene su equivalente HQL en test/Consultas.java
-- =====================================================================
USE epicentro_gourmet;

-- 1) Unidades de venta con su responsable y el festival que las habilita
--    HQL equivalente: FROM UnidadVenta u ORDER BY u.codigo
SELECT u.codigo,
       u.tipo_unidad,
       u.nombre_comercial,
       CONCAT(s.apellido, ', ', s.nombre) AS responsable,
       f.nombre AS festival
FROM unidad_venta u
LEFT JOIN staff s ON s.id = u.responsable_id
LEFT JOIN festival f ON f.id = u.festival_id
ORDER BY u.codigo;

-- 2) Recaudacion total por festival
SELECT f.nombre,
       f.temporada,
       SUM(i.cantidad * i.precio_unidad) AS recaudacion
FROM pedido p
JOIN festival f ON f.id = p.festival_id
JOIN item_pedido i ON i.pedido_id = p.id
GROUP BY f.id, f.nombre, f.temporada
ORDER BY recaudacion DESC;

-- 3) Total vendido por unidad de venta
SELECT u.codigo,
       u.nombre_comercial,
       COUNT(DISTINCT p.id) AS cant_pedidos,
       SUM(i.cantidad * i.precio_unidad) AS total_vendido
FROM pedido p
JOIN unidad_venta u ON u.id = p.unidad_venta_id
JOIN item_pedido i ON i.pedido_id = p.id
GROUP BY u.id, u.codigo, u.nombre_comercial
ORDER BY total_vendido DESC;

-- 4) Top 5 platos mas vendidos
SELECT pl.nombre,
       SUM(i.cantidad) AS unidades_vendidas,
       SUM(i.cantidad * i.precio_unidad) AS facturado
FROM item_pedido i
JOIN plato pl ON pl.id = i.plato_id
GROUP BY pl.id, pl.nombre
ORDER BY unidades_vendidas DESC
LIMIT 5;

-- 5) Ticket promedio por unidad de venta
SELECT u.nombre_comercial,
       COUNT(DISTINCT p.id) AS pedidos,
       ROUND(
           SUM(i.cantidad * i.precio_unidad) / COUNT(DISTINCT p.id),
           2
       ) AS ticket_promedio
FROM pedido p
JOIN unidad_venta u ON u.id = p.unidad_venta_id
JOIN item_pedido i ON i.pedido_id = p.id
GROUP BY u.id, u.nombre_comercial
ORDER BY ticket_promedio DESC;

-- 6) Cocineros por especialidad (subclase de la jerarquia)
SELECT s.especialidad,
       CONCAT(s.apellido, ', ', s.nombre) AS cocinero,
       s.sueldo_base,
       TIMESTAMPDIFF(YEAR, s.fecha_ingreso, CURDATE()) AS antiguedad
FROM staff s
WHERE s.tipo_staff = 'COCINERO'
ORDER BY s.especialidad, s.apellido;

-- 7) Staff con ingreso anterior a 2020, ordenado por antiguedad
SELECT s.tipo_staff,
       CONCAT(s.apellido, ', ', s.nombre) AS empleado,
       s.fecha_ingreso,
       TIMESTAMPDIFF(YEAR, s.fecha_ingreso, CURDATE()) AS antiguedad,
       TIMESTAMPDIFF(YEAR, s.fecha_nacimiento, CURDATE()) AS edad
FROM staff s
WHERE s.fecha_ingreso < '2020-01-01'
ORDER BY s.fecha_ingreso;

-- 8) Pedidos del festival de verano entre dos fechas
SELECT p.id,
       p.fecha,
       u.nombre_comercial,
       SUM(i.cantidad * i.precio_unidad) AS total_pedido
FROM pedido p
JOIN festival f ON f.id = p.festival_id
JOIN unidad_venta u ON u.id = p.unidad_venta_id
JOIN item_pedido i ON i.pedido_id = p.id
WHERE f.temporada = 'VERANO'
  AND p.fecha BETWEEN '2026-01-10' AND '2026-01-13'
GROUP BY p.id, p.fecha, u.nombre_comercial
ORDER BY p.fecha;

-- 9) Margen unitario por plato
SELECT pl.nombre,
       pl.precio_venta,
       pl.costo_produccion,
       pl.precio_venta - pl.costo_produccion AS margen,
       ROUND(
           (pl.precio_venta - pl.costo_produccion) / pl.precio_venta * 100,
           1
       ) AS margen_pct
FROM plato pl
ORDER BY margen DESC;

-- 10) Foodtracks que requieren electricidad
SELECT u.codigo,
       u.nombre_comercial,
       u.patente,
       u.superficie_m2
FROM unidad_venta u
WHERE u.tipo_unidad = 'FOODTRACK'
  AND u.requiere_electricidad = TRUE;

-- 11) Cantidad de unidades y de staff por festival
SELECT f.nombre,
       COUNT(DISTINCT u.id) AS unidades_habilitadas,
       COUNT(DISTINCT s.id) AS staff_asignado
FROM festival f
LEFT JOIN unidad_venta u ON u.festival_id = f.id
LEFT JOIN staff s ON s.festival_id = f.id
GROUP BY f.id, f.nombre;

-- 12) Ganancia real por unidad (facturado - costo de produccion)
SELECT u.nombre_comercial,
       SUM(i.cantidad * i.precio_unidad) AS facturado,
       SUM(i.cantidad * pl.costo_produccion) AS costo,
       SUM(i.cantidad * (i.precio_unidad - pl.costo_produccion)) AS ganancia
FROM pedido p
JOIN unidad_venta u ON u.id = p.unidad_venta_id
JOIN item_pedido i ON i.pedido_id = p.id
JOIN plato pl ON pl.id = i.plato_id
GROUP BY u.id, u.nombre_comercial
ORDER BY ganancia DESC;

-- 13) Platos que nunca se vendieron
SELECT pl.nombre,
       u.nombre_comercial
FROM plato pl
LEFT JOIN item_pedido i ON i.plato_id = pl.id
LEFT JOIN unidad_venta u ON u.id = pl.unidad_venta_id
WHERE i.id IS NULL;

-- 14) Sueldo final estimado por tipo de staff
--     Regla del modelo: base + 2% por anio;
--     +15% cocinero, +20% cajero noche
SELECT s.tipo_staff,
       COUNT(*) AS cantidad,
       ROUND(
           AVG(
               s.sueldo_base
               * (1 + 0.02 * TIMESTAMPDIFF(YEAR, s.fecha_ingreso, CURDATE()))
               * CASE
                   WHEN s.tipo_staff = 'COCINERO' THEN 1.15
                   WHEN s.tipo_staff = 'CAJERO' AND s.turno = 'NOCHE' THEN 1.20
                   ELSE 1
                 END
           ),
           2
       ) AS sueldo_final_promedio
FROM staff s
GROUP BY s.tipo_staff;
