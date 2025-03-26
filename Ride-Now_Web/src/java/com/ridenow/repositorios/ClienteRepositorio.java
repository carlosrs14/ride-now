/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.repositorios;

import com.ridenow.modelos.Cliente;
import com.ridenow.accesodatos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author crinc
 */
public class ClienteRepositorio {
    public ClienteRepositorio() {
    }
    
    public boolean saveCliente(Cliente cliente) {
        String consultaPersonaSQL = "INSERT INTO personas (nombre, apellido, email, fechanacimiento, telefono, password)" +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING idpersona;";
        try {
            Connection conexion = Conexion.getConexion();
            conexion.setAutoCommit(false);
            PreparedStatement statementPersona = conexion.prepareStatement(consultaPersonaSQL);
            
            statementPersona.setString(1, cliente.getNombre());
            statementPersona.setString(2, cliente.getApellido());
            statementPersona.setString(3, cliente.getCorreo());
            statementPersona.setDate(4, Date.valueOf(cliente.getFechaNacimiento().toString()));
            statementPersona.setString(5, cliente.getTelefono());
            statementPersona.setString(6, cliente.getPassword());
            
            ResultSet result = statementPersona.executeQuery();
            
            if (result.next()) {
                int idPersona = result.getInt("idpersona"); // tomar el id de la persona para ponerselo al cliente
                
                String consultaClienteSQL = "INSERT INTO clientes (idcliente) VALUES (?);";
                PreparedStatement statementCliente = conexion.prepareStatement(consultaClienteSQL);
                statementCliente.setInt(1, idPersona);
                
                statementCliente.executeUpdate();

                conexion.commit();
                conexion.close();
                return true;
            } else {
                // por si no se guardó bien
                conexion.rollback();
                conexion.close();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Cliente login(String email, String password) {
        Cliente cliente = null;

        if (!validarLogin(email, password)) {
            return cliente;
        } 
        
        String consultaSQL = "SELECT idpersona, nombre, apellido, email, fechanacimiento, telefono "
                + "FROM personas "
                + "WHERE email = ?;";
        try {
            Connection conexion = Conexion.getConexion();
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, email);
            
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int idPersona = result.getInt("idpersona");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                int diaNacimiento = 0;
                int mesNacimiento = 0;
                int anioNacimiento = 0;
                String telefono = result.getString("telefono");
                cliente = new Cliente(idPersona, diaNacimiento, mesNacimiento, anioNacimiento, nombre, apellido, telefono, email, "");
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e.toString());
        }
        return cliente;
    }
    
    public boolean validarLogin(String email, String password) {
        String consultaQL = "SELECT email, password FROM personas WHERE (email = ?) " +
                " AND (password = ?);";
        try {
            Connection conexion = Conexion.getConexion();
            
            PreparedStatement statement = conexion.prepareStatement(consultaQL);
            statement.setString(1, email);
            statement.setString(2, password);
            
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return false;
    }
    
    
}
