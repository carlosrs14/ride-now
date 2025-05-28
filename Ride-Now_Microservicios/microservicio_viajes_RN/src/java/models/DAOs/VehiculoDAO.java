/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAOs;

import models.DAOs.DAO;
import accesodatos.Conexion;
import com.ridenow.models.PrestadorDeServicio;
import com.ridenow.models.Vehiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xlancet
 */
public class VehiculoDAO implements DAO<Vehiculo> {

    public VehiculoDAO() {
    }

    @Override
    public Vehiculo create(Vehiculo g) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO vehiculos "
                + "(marca, modelo, placa, tieneaire, color, capacidad, idPrestadordeservicio) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING idvehiculo";
        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, g.getMarca());
        statement.setInt(2, g.getModelo());
        statement.setString(3, g.getPlaca());
        statement.setBoolean(4, g.isTieneAire());
        statement.setString(5, g.getColor());
        statement.setInt(6, g.getCapacidad());
        statement.setInt(7, g.getPrestadorDeServicio().getId());
        
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            g.setIdVehiculo(result.getInt("idvehiculo"));
            return g;
        } else {
            return null;
        }
    }

    @Override
    public Vehiculo get(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM vehiculos WHERE idvehiculo = ?;";
        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new Vehiculo(
                result.getInt("idvehiculo"),
                result.getString("marca"),
                result.getInt("modelo"),
                result.getString("placa"),
                result.getBoolean("tieneaire"),
                result.getString("color"),
                result.getInt("capacidad"),
                new PrestadorDeServicio(result.getInt("IDPrestadorDeServicio"))
            );
        }
        return null;
    }

    @Override
    public List<Vehiculo> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM vehiculos;";
        List<Vehiculo> vehiculos = new ArrayList<>();
        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            vehiculos.add(new Vehiculo(
                result.getInt("idvehiculo"),
                result.getString("marca"),
                result.getInt("modelo"),
                result.getString("placa"),
                result.getBoolean("tieneaire"),
                result.getString("color"),
                result.getInt("capacidad"),
                new PrestadorDeServicio(result.getInt("idprestadordeservicio"))
            ));
        }
        return vehiculos;
    }

    @Override
    public boolean update(Vehiculo g) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE vehiculos "
                + "SET marca = ?, modelo = ?, placa = ?, tieneaire = ?, "
                + "color = ?, capacidad = ?, idprestadordeservicio = ? "
                + "WHERE idvehiculo = ?;";
        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, g.getMarca());
        statement.setInt(2, g.getModelo());
        statement.setString(3, g.getPlaca());
        statement.setBoolean(4, g.isTieneAire());
        statement.setString(5, g.getColor());
        statement.setInt(6, g.getCapacidad());
        statement.setInt(7, g.getPrestadorDeServicio().getId());
        statement.setInt(8, g.getIdVehiculo());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM vehiculos WHERE idvehiculo = ?";
        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, id);
        return statement.executeUpdate() > 0;
    }

    @Override
    public List<Vehiculo> filterByOwner(int id) throws SQLException, ClassNotFoundException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos WHERE idprestadordeservicio = ?";

        Connection con = Conexion.getInstancia().getConexion();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Vehiculo v = new Vehiculo(
                rs.getInt("idvehiculo"),
                rs.getString("marca"),
                rs.getInt("modelo"),
                rs.getString("placa"),
                rs.getBoolean("tieneaire"),
                rs.getString("color"),
                rs.getInt("capacidad"),
                new PrestadorDeServicio(rs.getInt("idprestadorDeServicio"))
            );
            vehiculos.add(v);
        }
        return vehiculos;
    }
}
