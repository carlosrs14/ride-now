/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author crinc
 */
public class Conexion {
    
    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dotenv dotenv = Dotenv.load();
        String URL = dotenv.get("URL");
        String USER = dotenv.get("USER");
        String PASSWORD = dotenv.get("PASSWORD");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
