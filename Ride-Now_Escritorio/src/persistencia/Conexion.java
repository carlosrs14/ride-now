/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;
/**
 *
 * @author crinc
 */
public class Conexion {
    
    public static Connection getConexion() throws SQLException {
        Dotenv dotenv = Dotenv.load(); 
        String URL = dotenv.get("URL");
        String NAME = dotenv.get("NAME");
        String PASSWORD = dotenv.get("PASSWORD");
        return  DriverManager.getConnection(URL, NAME, PASSWORD);
    }
}
