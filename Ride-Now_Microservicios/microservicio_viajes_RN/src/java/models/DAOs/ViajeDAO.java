/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAOs;

import models.DAOs.DAO;
import accesodatos.Conexion;
import com.ridenow.models.Locacion;
import com.ridenow.models.PrestadorDeServicio;
import com.ridenow.models.Vehiculo;
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
 * @author xlancet
 */
public class ViajeDAO implements DAO<Viaje> {

    public ViajeDAO() {
    }

    @Override
    public Viaje create(Viaje g) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO viajes "
                + "(fecha, hora, tipo, precio, idvehiculo, idlocacionorigen, idlocaciondestino) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING idviaje";
        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, new Date(g.getFecha().getTime()));
        statement.setInt(2, g.getHora());
        statement.setString(3, g.getTipo());
        statement.setFloat(4, g.getPrecio());
        statement.setInt(5, g.getVehiculo().getIdVehiculo());
        statement.setInt(6, g.getOrigen().getId());
        statement.setInt(7, g.getDestino().getId());
        
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            g.setId(result.getInt("idviaje"));
            return g;
        } else {
            return null;
        }
    }

    @Override
    public Viaje get(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM viajes WHERE idviaje = ?;";
        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new Viaje(
                    result.getInt("idviaje"),
                    result.getInt("hora"),
                    result.getFloat("precio"),
                    result.getString("tipo"),
                    result.getDate("fecha"),
                    new Locacion(result.getInt("idlocacionorigen")),
                    new Locacion(result.getInt("idlocaciondestino")),
                    new Vehiculo(result.getInt("idvehiculo"))
            );
        }
        return null;
    }

    @Override
    public List<Viaje> getAll() throws SQLException, ClassNotFoundException {
        List<Viaje> viajes = new ArrayList<>();
        
        String sql = "SELECT * FROM viajes;";
        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            viajes.add(
                new Viaje(
                    result.getInt("idviaje"),
                    result.getInt("hora"),
                    result.getFloat("precio"),
                    result.getString("tipo"),
                    result.getDate("fecha"),
                    new Locacion(result.getInt("idlocacionorigen")),
                    new Locacion(result.getInt("idlocaciondestino")),
                    new Vehiculo(result.getInt("idvehiculo"))
                )
            );
        }
        return viajes;
    }

    @Override
    public boolean update(Viaje g) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM viajes WHERE idviaje = ?";
        Connection con = Conexion.getInstancia().getConexion();;
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, id);
        return statement.executeUpdate() > 0;
    }

    @Override
    public List<Viaje> filterByOwner(int id) throws SQLException, ClassNotFoundException {
        List<Viaje> viajes = new ArrayList<>();
        String sql = "SELECT * FROM prestadoresdeservicio "
                + "INNER JOIN vehiculos USING(idprestadordeservicio) "
                + "INNER JOIN viajes USING(idvehiculo) "
                + "WHERE idprestadordeservicio = ?";

        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Viaje v = new Viaje(
                rs.getInt("idviaje"),
                rs.getInt("hora"),
                rs.getFloat("precio"),
                rs.getString("tipo"),
                rs.getDate("fecha"),
                new Locacion(rs.getInt("idlocacionorigen")),
                new Locacion(rs.getInt("idlocaciondestino")),
                new Vehiculo(rs.getInt("idvehiculo"))
            );
            viajes.add(v);
        }
        return viajes;
    }
}
