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

    static {
        try (InputStream input = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            pass = prop.getProperty("db.password");

            // --- CODIGO DE DEPURACIÓN (BORRAR AL FINAL) ---
            System.out.println("------------------------------------------------");
            System.out.println("DEBUG: Leyendo archivo db.properties");
            System.out.println("Usuario leído: " + user);
            System.out.println("Contraseña leída: '" + pass + "'"); // Las comillas simples nos dejarán ver si hay espacios
            System.out.println("------------------------------------------------");
            // ------------------------------------------------

        } catch (IOException e) {
            System.err.println("ERROR: No se pudo leer db.properties");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}