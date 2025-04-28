/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author xlancet
 */
public class Conexion {
    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Class.forName("io.github.cdimascio.dotenv.Dotenv");
        
        Dotenv dotEnv = Dotenv.load();
        String user = dotEnv.get("USER");
        String host  = dotEnv.get("URL");
        String password  = dotEnv.get("PASSWORD");
        return DriverManager.getConnection(host, user, password);
    }
    
}
