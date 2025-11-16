/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segurosvehiculares.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String url;
    private static String user;
    private static String pass;

    // Carga estática de propiedades
    static {
        try (InputStream input = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            
            // Cargar el driver de MySQL (opcional en JDBC 4.0+)
            // Class.forName("com.mysql.cj.jdbc.Driver"); 

            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            pass = prop.getProperty("db.password");

        } catch (IOException e) {
            System.err.println("Error al leer 'db.properties': " + e.getMessage());
            throw new RuntimeException("No se pueden cargar las propiedades de la BD.", e);
        }
    }

    /**
     * Retorna una conexión a la base de datos[cite: 62].
     * Quien llama a este método es responsable de cerrar la conexión.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}