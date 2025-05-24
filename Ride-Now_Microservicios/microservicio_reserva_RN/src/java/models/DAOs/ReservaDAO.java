/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAOs;

import accesodatos.Conexion;
import com.ridenow.models.Cliente;
import com.ridenow.models.Reserva;
import com.ridenow.models.Viaje;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rossimar
 */
public class ReservaDAO implements DAO<Reserva> {

    public ReservaDAO() {
    }

    @Override
    public Reserva create(Reserva g) throws SQLException, ClassNotFoundException {
        String consultaSQL = "INSERT INTO reservas (fechareserva, idcliente, idviaje) " + 
                             "VALUES(?, ?, ?);";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        
        statement.setDate(1, Date.valueOf(g.getFecha()));
        statement.setInt(2, g.getCliente().getId());
        statement.setInt(4, g.getViaje().getId());
        
        ResultSet result = statement.executeQuery();
        if(result.next()) {
            g.setId(result.getInt("idreserva"));
            return g;
        } else {
            return null;
        }  
        
    }

    @Override
    public Reserva get(int id) throws SQLException, ClassNotFoundException {
       String consultaSQL = "SELECT * FROM reservas WHERE idreserva = ? ";
        
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setInt(1, id);
        
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return new Reserva(
                result.getInt("idreserva"),
                0,
                new Cliente(result.getInt("idcliente")),
                result.getString("fechareserva"),
                0,
                "",
                new Viaje(result.getInt("idviaje"))   
            );
        } 
        return null;
    }

    @Override
    public List<Reserva> getAll() throws SQLException, ClassNotFoundException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            reservas.add(
                    new Reserva(
                        result.getInt("idreserva"),
                        0,
                        new Cliente(result.getInt("idcliente")),
                        result.getString("fechareserva"),
                        0,
                        "",
                        new Viaje(result.getInt("idviaje"))
                    )
            );
        }
        return reservas;
    }

    @Override
    public boolean update(Reserva g) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE reservas SET fechareserva = ?, idcliente = ?, idviaje = ? WHERE idreserva = ?";
        Connection conexion = Conexion.getConexion();
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setString(1, g.getFecha());
        stmt.setInt(2, g.getCliente().getId());
        stmt.setInt(3, g.getViaje().getId());
        
        int filas = stmt.executeUpdate();
        conexion.close();
        return filas > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM reservas WHERE idreserva = ?";
        Connection conn = Conexion.getConexion();
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setInt(1, id);
        return statement.executeUpdate() > 0;
    }

    @Override
    public List<Reserva> filterByOwner(int id) throws SQLException, ClassNotFoundException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas WHERE idcliente = ?";
        Connection conexion = Conexion.getConexion();
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        while(result.next()) {
            reservas.add(
                new Reserva(
                    result.getInt("idreserva"),
                    0,
                    new Cliente(result.getInt("idcliente")),
                    result.getString("fechareserva"),
                    0,
                    "",
                    new Viaje(result.getInt("idviaje"))
                )
            );
        }
        return reservas;
    }  
}
