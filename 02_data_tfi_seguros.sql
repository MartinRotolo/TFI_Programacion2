USE tfi_seguros_db;

-- Deshabilitar safe updates para limpiar tablas
SET SQL_SAFE_UPDATES = 0;
DELETE FROM seguro_vehicular;
DELETE FROM vehiculo;
SET SQL_SAFE_UPDATES = 1;

-- Resetear contadores
ALTER TABLE vehiculo AUTO_INCREMENT = 1;
ALTER TABLE seguro_vehicular AUTO_INCREMENT = 1;

-- Caso 1: Vehiculo CON Seguro (ID 1)
INSERT INTO vehiculo (dominio, marca, modelo, anio, nro_chasis, eliminado) 
VALUES ('AA123BC', 'Ford', 'Focus', 2019, 'CH123456', 0);

INSERT INTO seguro_vehicular (aseguradora, nro_poliza, cobertura, vencimiento, eliminado, vehiculo_id)
VALUES ('La Caja', 'POL987654', 'TODO_RIESGO', '2026-05-10', 0, 1);

-- Caso 2: Vehiculo SIN Seguro (ID 2)
INSERT INTO vehiculo (dominio, marca, modelo, anio, nro_chasis, eliminado) 
VALUES ('AB456CD', 'Volkswagen', 'Golf', 2021, 'CH654321', 0);

-- Caso 3: Vehiculo (eliminado) CON Seguro (ID 3)
INSERT INTO vehiculo (dominio, marca, modelo, anio, nro_chasis, eliminado) 
VALUES ('AD789EF', 'Toyota', 'Corolla', 2022, 'CH789012', 1);

INSERT INTO seguro_vehicular (aseguradora, nro_poliza, cobertura, vencimiento, eliminado, vehiculo_id)
VALUES ('Sancor Seguros', 'POL123456', 'TERCEROS', '2025-11-20', 1, 3);

SELECT * FROM vehiculo;
SELECT * FROM seguro_vehicular;