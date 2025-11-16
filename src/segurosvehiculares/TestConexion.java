/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segurosvehiculares;


import segurosvehiculares.config.DatabaseConnection;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        try {
            System.out.println("Intentando conectar a la BD...");
            Connection conn = DatabaseConnection.getConnection();

            if (conn != null && !conn.isClosed()) {
                System.out.println("¡CONEXIÓN EXITOSA!");
                System.out.println("Objeto Conexión: " + conn);
            } else {
                System.err.println("Error: La conexión es nula o está cerrada.");
            }

            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            System.err.println("FALLO LA CONEXIÓN:");
            e.printStackTrace();
        }
    }
}