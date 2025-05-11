/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import accessodatos.Conexion;
import com.ridenow.models.Administrador;
import com.ridenow.models.Cliente;
import com.ridenow.models.PrestadorDeServicio;
import com.ridenow.models.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xlancet
 */
public class UsuarioDAO implements DAO<Usuario>{
    public UsuarioDAO() {
    }

    @Override
    public Usuario create(Usuario usuario) throws SQLException, ClassNotFoundException {
        String consultaUsuarioSQL = "INSERT INTO usuarios (nombre, apellido, email, fechanacimiento, telefono, password, tipo)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING idusuario;";
        Connection conexion = Conexion.getConexion();
        conexion.setAutoCommit(false);
        PreparedStatement statementUsuario = conexion.prepareStatement(consultaUsuarioSQL);

        statementUsuario.setString(1, usuario.getNombre());
        statementUsuario.setString(2,  usuario.getApellido());
        statementUsuario.setString(3, usuario.getCorreo());
        statementUsuario.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
        statementUsuario.setString(5, usuario.getTelefono());
        statementUsuario.setString(6, usuario.getPassword());
        statementUsuario.setString(7, usuario.getTipo());

        ResultSet result = statementUsuario.executeQuery();

        if (!result.next()) {
            conexion.rollback();
            conexion.close();
            return null;
        }
        int idUsuario = result.getInt("idusuario"); // tomar el id de la usuario para ponerselo al cliente
        String tabla;
        String columna;
        switch (usuario.getTipo()) {
            case "cliente" -> {
                tabla = "clientes";
                columna = "idcliente";
            }
            case "prestadordeservicio" -> {
                tabla = "prestadoresdeservicio";
                columna = "idprestadordeservicio";
            }
            case "admin" -> {
                tabla = "administradores";
                columna = "idadministrador";
            }
            default -> throw new AssertionError();
        }
        String consultaClienteSQL = "INSERT INTO " + tabla + " (" + columna + ") VALUES (?);";
        PreparedStatement statementCliente = conexion.prepareStatement(consultaClienteSQL);
        statementCliente.setInt(1, idUsuario);

        statementCliente.executeUpdate();
        usuario.setId(idUsuario);
        conexion.commit();
        conexion.close();

        return usuario;
    }

    @Override
    public Usuario get(int id) throws SQLException, ClassNotFoundException{
        Usuario usuario = null;
        Connection conexion = Conexion.getConexion();
        String consulta = "SELECT * FROM usuarios WHERE idusuario = ?";
        PreparedStatement stmt = conexion.prepareStatement(consulta);
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            String tipo = result.getString("tipo");
            String fecha = String.valueOf(result.getDate("fechanacimiento"));
            String nombre = result.getString("nombre");
            String apellido = result.getString("apellido");
            String correo = result.getString("email");
            String telefono = result.getString("telefono");
            String password = result.getString("password");

            switch (tipo) {
                case "cliente" -> usuario = new Cliente(id, fecha, nombre, apellido, tipo, telefono, correo, password);
                case "prestadordeservicio" -> usuario = new PrestadorDeServicio(id, fecha, nombre, apellido, tipo, telefono, correo, password, 0);
                case "admin" -> usuario = new Administrador(id, fecha, nombre, apellido, tipo, telefono, correo, password);
            }
        }
        conexion.close();
        return usuario;
    }

    @Override
    public List<Usuario> getAll() throws SQLException, ClassNotFoundException {
        List<Usuario> usuarios = new ArrayList<>();

        Connection conexion = Conexion.getConexion();
        String consulta = "SELECT * FROM usuarios";
        PreparedStatement stmt = conexion.prepareStatement(consulta);
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            int id = result.getInt("idusuario");
            String tipo = result.getString("tipo");
            String fecha = String.valueOf(result.getDate("fechanacimiento"));
            String nombre = result.getString("nombre");
            String apellido = result.getString("apellido");
            String correo = result.getString("email");
            String telefono = result.getString("telefono");
            String password = result.getString("password");

            Usuario usuario;
            switch (tipo) {
                case "cliente" -> usuario = new Cliente(id, fecha, nombre, apellido, tipo, telefono, correo, password);
                case "prestadordeservicio" -> usuario = new PrestadorDeServicio(id, fecha, nombre, apellido, tipo, telefono, correo, password, 0);
                case "admin" -> usuario = new Administrador(id, fecha, nombre, apellido, tipo, telefono, correo, password);
                default -> throw new AssertionError();
            }
            usuarios.add(usuario);
        }
        conexion.close();
        return usuarios;
    }

    @Override
    public boolean update(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection conexion = Conexion.getConexion();
        String consulta = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, fechanacimiento = ?, telefono = ?, password = ? "
                + "WHERE idusuario = ?";
        PreparedStatement stmt = conexion.prepareStatement(consulta);

        stmt.setString(1, usuario.getNombre());
        stmt.setString(2, usuario.getApellido());
        stmt.setString(3, usuario.getCorreo());
        stmt.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
        stmt.setString(5, usuario.getTelefono());
        stmt.setString(6, usuario.getPassword());
        stmt.setInt(7, usuario.getId());

        int filas = stmt.executeUpdate();
        conexion.close();
        return filas > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        Connection conexion = Conexion.getConexion();

        String eliminarUsuario = "DELETE FROM usuarios WHERE idusuario = ?";
        PreparedStatement stmt = conexion.prepareStatement(eliminarUsuario);
        stmt.setInt(1, id);

        int filas = stmt.executeUpdate();
        conexion.close();
        return filas > 0;

    }
    
    public Usuario login(String email, String password) throws SQLException, ClassNotFoundException {
        Usuario usuario = null;

        if (!validarLogin(email, password)) {
            return usuario;
        } 
        
        String consultaSQL = "SELECT idusuario, nombre, apellido, fechanacimiento, telefono, tipo "
                + "FROM usuarios "
                + "WHERE email = ?;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setString(1, email);

        ResultSet result = statement.executeQuery();
        
        result.next();
        int idUsuario = result.getInt("idusuario");
        String nombre = result.getString("nombre");
        String apellido = result.getString("apellido");
        String fecha = String.valueOf(result.getDate("fechanacimiento"));
        String telefono = result.getString("telefono");
        String tipo = result.getString("tipo");
        switch (tipo) {
            case "cliente" -> usuario = new Cliente(idUsuario, fecha, nombre, apellido, tipo, telefono, email, password);
            case "prestadordeservicio" -> usuario = new PrestadorDeServicio(idUsuario, fecha, nombre, apellido, tipo, telefono, email, password, 0);
            case "admin" -> usuario = new Administrador(idUsuario, fecha, nombre, apellido, tipo, telefono, email, password);
            default -> {
                return usuario;
            }
        }
        return usuario;
    }
        private boolean validarLogin(String email, String password) throws SQLException, ClassNotFoundException {
        String consultaQL = "SELECT email, password FROM usuarios WHERE (email = ?) " +
                " AND (password = ?);";
        Connection conexion = Conexion.getConexion();

        PreparedStatement statement = conexion.prepareStatement(consultaQL);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();
        return result.next();
    }
}
