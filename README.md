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
* **Driver:** MySQL Connector/J (incluido en la carpeta `lib/` del proyecto)).

### 🚀 Instrucciones de Instalación y Ejecución

Siga estos pasos para levantar el proyecto desde cero:

#### 1. Base de Datos
1.  Abra su cliente de MySQL (Workbench, DBeaver, etc.).
2.  Ejecute el script **`01_schema_tfi_Seguros.sql`** ubicado en la raíz. Esto creará la base de datos y las tablas.
3.  Ejecute el script **`02_data_tfi_Seguros.sql`** para cargar datos de prueba iniciales.

#### 2. Configuración de Conexión
El proyecto incluye un archivo de configuración llamado **`db.properties`** en la raíz.
1.  Abra el archivo **`db.properties`** con un editor de texto o desde el IDE.
2.  Verifique o modifique las credenciales según su instalación local de MySQL:

    ```properties
    db.url=jdbc:mysql://localhost:3306/tfi_seguros_db?useSSL=false&serverTimezone=UTC
    db.user=root
    # Si usa XAMPP, deje la contraseña vacía (sin espacios):
    db.password=
    # Si usa Workbench con contraseña, escríbala a continuación:
    # db.password=SuContraseñaAqui
    ```

#### 3. Ejecución
1.  Abra el proyecto en NetBeans.
2.  El proyecto ya incluye el driver necesario en la carpeta **`lib/`**.
    * *Nota:* Si al abrirlo el IDE indica "Reference Problems" (librería no encontrada), vaya a **Properties > Libraries** y vuelva a seleccionar el archivo `.jar` que se encuentra dentro de la carpeta `lib/` del proyecto.
3.  Ejecute la clase principal: `main.MainApp`.
4.  Utilice el menú de consola para probar las operaciones CRUD.

### 📹 Video de Presentación
https://youtu.be/8WCfGglJohk?si=HZSbEWpH32uJt0KD

### 👥 Integrantes
* **Martin Rotolo**: Diseño, BD y Entidades.
* **Wolanink Melany**: Implementación de Servicios, Transacciones y Lógica de Negocio.

---
*Trabajo realizado para la Tecnicatura Universitaria en Programación - UTN.*