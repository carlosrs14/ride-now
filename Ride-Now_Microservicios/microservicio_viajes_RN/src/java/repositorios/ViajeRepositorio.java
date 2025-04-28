/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import accesodatos.Conexion;
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
public class ViajeRepositorio {

    public ViajeRepositorio() {
    }
    
    public boolean saveViaje(Viaje viaje) throws SQLException, ClassNotFoundException {
        String consultaSQL = "INSERT INTO viajes(fecha, hora, precio, tipo, idvehiculo, idlocacionorigen, idlocaciondestino) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?);";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statementViaje = conexion.prepareStatement(consultaSQL);
        
        String anio = String.valueOf(viaje.getFecha().getYear());
        String mes = String.valueOf(viaje.getFecha().getMonth());
        String dia = String.valueOf(viaje.getFecha().getDay());
        String fecha = anio + "-" + mes + "-" + dia;
        
        statementViaje.setDate(1, Date.valueOf(fecha));
        statementViaje.setInt(2, viaje.getHora());
        statementViaje.setFloat(3, viaje.getPrecio());
        statementViaje.setString(4, viaje.getTipo());
        statementViaje.setInt(5, viaje.getVehiculo().getIdVehiculo());
        statementViaje.setInt(6, viaje.getOrigen().getId());
        statementViaje.setInt(7, viaje.getDestino().getId());
        
        ResultSet result = statementViaje.executeQuery();
        if (result.next()) {
            conexion.close();
            return true;
        } else {
            conexion.rollback();
            conexion.close();
            return false;
        }
    }
    
    public Viaje buscar(int idViaje) throws SQLException, ClassNotFoundException {
        String consultaSQL = "SELECT * FROM viajes WHERE idviaje = ?;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setInt(1, idViaje);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int hora = result.getInt("hora");
            float precio = result.getFloat("precio");
            String tipo = result.getString("tipo");
            int dia = 0;
            int mes = 0;
            int anio = 0;
            int idLocacionOrigen = result.getInt("idlocacionorigen");
            int idLocacionDestino = result.getInt("idlocaciondestino");
            int idvehiculo = result.getInt("idvehiculo");
            Date fecha = new Date(dia, mes, anio);
            Viaje viaje = new Viaje(idViaje, hora, precio,tipo,fecha , null, null, null);
            return viaje;
        } else {
            return null; 
        }
    }
    public List<Viaje> listar() throws SQLException, ClassNotFoundException {
        String consultaSQL = "SELECT * FROM viajes";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        List<Viaje> viajes = new ArrayList<>();
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            int idViaje = result.getInt("idviaje");
            int hora = result.getInt("hora");
            float precio = result.getFloat("precio");
            String tipo = result.getString("tipo");
            // fecha = result.getInt("fecha");
             int dia = 0;
            int mes = 0;
            int anio = 0;
            int idLocacionOrigen = result.getInt("idlocacionorigen");
            int idLocacionDestino = result.getInt("idlocaciondestino");
            int idvehiculo = result.getInt("idvehiculo");
            Date fecha = new Date(dia, mes, anio);
            Viaje viaje = new Viaje(idViaje, hora, precio,tipo,fecha , null, null, null);
            viajes.add(viaje);
        }
        return viajes;
    }

    public boolean eliminarViaje(int idViaje) throws SQLException, ClassNotFoundException {
        String consultaSQL = "DELETE FROM viajes WHERE idviaje = ?;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setInt(1, idViaje);

        int filasAfectadas = statement.executeUpdate();
        return filasAfectadas > 0;
    }
    
    
    public boolean updateViaje(Viaje viaje) throws SQLException, ClassNotFoundException {
        String consultaSQL = "UPDATE viajes SET fecha = ?, hora = ?, precio = ?,"
                + " idvehiculo = ?, idlocacionorigen = ?, idlocaciondestino = ? WHERE idviaje = ?;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statementViaje = conexion.prepareStatement(consultaSQL);

        String anio = String.valueOf(viaje.getFecha().getYear());
        String mes = String.valueOf(viaje.getFecha().getMonth());
        String dia = String.valueOf(viaje.getFecha().getDay());
        String fecha = anio + "-" + mes + "-" + dia;
        statementViaje.setDate(1, Date.valueOf(fecha));
        statementViaje.setInt(2, viaje.getHora());
        statementViaje.setFloat(3, viaje.getPrecio());
        statementViaje.setInt(4, viaje.getVehiculo().getIdVehiculo());
        statementViaje.setInt(5, viaje.getOrigen().getId());
        statementViaje.setInt(6, viaje.getDestino().getId());
        

        int filasAfectadas = statementViaje.executeUpdate();
        conexion.close();
        return filasAfectadas > 0;
}

    
    
    

}
