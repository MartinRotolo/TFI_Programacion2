# Trabajo Final Integrador - Programación 2 (UTN)
## Sistema de Gestión de Vehículos y Seguros

Este proyecto implementa un sistema de gestión CRUD para **Vehículos** y sus **Seguros** asociados, modelando una relación **1 a 1 unidireccional** y garantizando la integridad referencial y transaccional mediante JDBC puro y MySQL.

### 📋 Descripción del Dominio
El dominio elegido es **Vehículo → SeguroVehicular**.
* **Relación:** 1 a 1 Unidireccional (El vehículo referencia al seguro).
* **Validaciones:** Un vehículo no puede tener más de un seguro (garantizado por restricción `UNIQUE` en BD).
* **Persistencia:** JDBC (sin ORM) con patrón DAO.

### 🛠️ Requisitos Técnicos
* **Java JDK:** 21 (o superior).
* **Base de Datos:** MySQL 8.0.
* **IDE recomendado:** NetBeans / IntelliJ / Eclipse.
* **Driver:** MySQL Connector/J (debe estar en la carpeta `lib/` o agregado al proyecto).

### 🚀 Instrucciones de Instalación y Ejecución

Siga estos pasos para levantar el proyecto desde cero:

#### 1. Base de Datos
1.  Abra su cliente de MySQL (Workbench, DBeaver, etc.).
2.  Ejecute el script **`01_schema_tfi_Seguros.sql`** ubicado en la raíz. Esto creará la base de datos y las tablas.
3.  Ejecute el script **`02_data_tfi_Seguros.sql`** para cargar datos de prueba iniciales.

#### 2. Configuración de Conexión
El proyecto lee las credenciales desde un archivo externo por seguridad.
1.  Vaya a la carpeta raíz del proyecto.
2.  Cree un archivo llamado **`db.properties`** (si no existe).
3.  Agregue el siguiente contenido reemplazando con sus credenciales:
    ```properties
    db.url=jdbc:mysql://localhost:3306/tfi_seguros_db?useSSL=false&serverTimezone=UTC
    db.user=SU_USUARIO_MYSQL
    db.password=SU_CONTRASEÑA_MYSQL
    ```

#### 3. Ejecución
1.  Abra el proyecto en NetBeans.
2.  Si tiene errores de librerías, asegúrese de agregar el `.jar` del driver MySQL (ubicado en `lib/`) a las "Libraries" del proyecto.
3.  Ejecute la clase principal: `main.MainApp`.
4.  Utilice el menú de consola para probar las operaciones.

### 📹 Video de Presentación
(https://youtu.be/8WCfGglJohk?si=HZSbEWpH32uJt0KD)

### 👥 Integrantes
* **Martin Rotolo**: Diseño, BD y Entidades.
* **Wolanink Melany**: DAO, Servicios y Transacciones, Menú y Testing.

---
*Trabajo realizado para la Tecnicatura Universitaria en Programación - UTN.*
