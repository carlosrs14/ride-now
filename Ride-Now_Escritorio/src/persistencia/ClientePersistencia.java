/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

/**
 *
 * @author crinc
 */
public class ClientePersistencia {
    
    public static boolean guardarCliente(Cliente cliente) {
        Connection conexion;
        String consultaPersonaSQL = "INSERT INTO personas (nombre, apellido, email, fechanacimiento, telefono, password)" +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING idpersona;";
        try {
            conexion = Conexion.getConexion();
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
                conexion.rollback();
                
                conexion.close();
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
