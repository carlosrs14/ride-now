/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import accessodatos.Conexion;
import com.ridenow.models.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xlancet
 */
public class UsuarioRepositorio {
    public UsuarioRepositorio() {
    }
    
    public boolean saveCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        String consultaUsuarioSQL = "INSERT INTO usuarios (nombre, apellido, email, fechanacimiento, telefono, password)" +
                                    " VALUES (?, ?, ?, ?, ?, ?) RETURNING idusuario;";
        Connection conexion = Conexion.getConexion();
        conexion.setAutoCommit(false);
        PreparedStatement statementUsuario = conexion.prepareStatement(consultaUsuarioSQL);

        statementUsuario.setString(1, cliente.getNombre());
        statementUsuario.setString(2, cliente.getApellido());
        statementUsuario.setString(3, cliente.getCorreo());
        String anio = String.valueOf(cliente.getFechaNacimiento().getYear());
        String mes = String.valueOf(cliente.getFechaNacimiento().getMonth());
        String dia = String.valueOf(cliente.getFechaNacimiento().getDay());
        String fecha = anio + "-" + mes + "-" + dia;
        statementUsuario.setDate(4, Date.valueOf(fecha));
        statementUsuario.setString(5, cliente.getTelefono());
        statementUsuario.setString(6, cliente.getPassword());

        ResultSet result = statementUsuario.executeQuery();

        if (result.next()) {
            int idUsuario = result.getInt("idusuario"); // tomar el id de la usuario para ponerselo al cliente

            String consultaClienteSQL = "INSERT INTO clientes (idcliente) VALUES (?);";
            PreparedStatement statementCliente = conexion.prepareStatement(consultaClienteSQL);
            statementCliente.setInt(1, idUsuario);

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
    }
    
    public Cliente login(String email, String password) throws SQLException, ClassNotFoundException {
        Cliente cliente = null;

        if (!validarLogin(email, password)) {
            return cliente;
        } 
        
        String consultaSQL = "SELECT idusuario, nombre, apellido, email, fechanacimiento, telefono "
                + "FROM usuarios "
                + "WHERE email = ?;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setString(1, email);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int idUsuario = result.getInt("idusuario");
            String nombre = result.getString("nombre");
            String apellido = result.getString("apellido");
            int diaNacimiento = 0;
            int mesNacimiento = 0;
            int anioNacimiento = 0;
            String telefono = result.getString("telefono");
            cliente = new Cliente(idUsuario, diaNacimiento, mesNacimiento, anioNacimiento, nombre, apellido, telefono, email, "");
        } 
        return cliente;
    }
    
    private boolean validarLogin(String email, String password) throws SQLException, ClassNotFoundException {
        String consultaQL = "SELECT email, password FROM usuarios WHERE (email = ?) " +
                " AND (password = ?);";
        Connection conexion = Conexion.getConexion();

        PreparedStatement statement = conexion.prepareStatement(consultaQL);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return true;   
        }
        return false;
    }
}
