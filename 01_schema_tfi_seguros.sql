-- 1. Borrar la base de datos si existe
DROP DATABASE IF EXISTS tfi_seguros_db;

-- 2. Crear base de datos
CREATE DATABASE IF NOT EXISTS tfi_seguros_db
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE tfi_seguros_db;

-- 3. Tabla A: Vehiculo
CREATE TABLE IF NOT EXISTS vehiculo (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  dominio VARCHAR(10) NOT NULL UNIQUE,
  marca VARCHAR(50) NOT NULL,
  modelo VARCHAR(50) NOT NULL,
  anio INT NOT NULL,
  nro_chasis VARCHAR(50) UNIQUE,
  eliminado BOOLEAN NOT NULL DEFAULT 0
);

-- 4. Tabla B: SeguroVehicular
CREATE TABLE IF NOT EXISTS seguro_vehicular (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  aseguradora VARCHAR(80) NOT NULL,
  nro_poliza VARCHAR(50) UNIQUE,
  cobertura ENUM('RC', 'TERCEROS', 'TODO_RIESGO') NOT NULL,
  vencimiento DATE NOT NULL,
  eliminado BOOLEAN NOT NULL DEFAULT 0,
  
  -- Relación 1-1
  vehiculo_id BIGINT NOT NULL UNIQUE, 
  
  CONSTRAINT fk_seguro_vehicular_vehiculo
    FOREIGN KEY (vehiculo_id)
    REFERENCES vehiculo(id)
    ON DELETE CASCADE
);

SELECT * FROM vehiculo;
SELECT * FROM seguro_vehicular;