/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import models.Viaje;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Fecha;
import models.Locacion;


/**
 *
 * @author crinc
 */
public class ViajePersistenacia {
    public static List<Viaje> filtrarViajes(String origen, String destino) throws SQLException {
        List<Viaje> viajes = new ArrayList<>();
        Connection conexion = Conexion.getConexion();
        String consultaSQL = "SELECT viajes.idviaje, viajes.hora, viajes.precio, viajes.fecha, "
                + "origen.idlocacion AS id_origen, origen.nombre AS nombre_origen, origen.tipo AS tipo_origen, "
                + "destino.idlocacion AS id_destino, destino.nombre AS nombre_destino, destino.tipo AS tipo_destino "
                + "FROM viajes "
                + "INNER JOIN locaciones AS origen ON(viajes.idlocacionorigen = origen.idlocacion) "
                + "INNER JOIN locaciones AS destino ON (viajes.idlocaciondestino = destino.idlocacion) "
                + "INNER JOIN prestadoresdeservicio AS pds ON (viajes.idprestadordeservicio = pds.idprestadordeservicio) "
                + "WHERE LOWER(origen.nombre) LIKE ? AND LOWER(destino.nombre) LIKE ? "
                + "ORDER BY fecha "
                + "LIMIT 50;";
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setString(1, "%" + origen + "%");
        statement.setString(2, "%" + destino + "%");
        
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int id = result.getInt("idviaje");
            int hora = 1;//result.getInt("hora");
            float precio = result.getFloat("precio");
            Fecha fecha = new Fecha(result.getDate("fecha"));
            
            int idLocacionOrigen = result.getInt("id_origen");
            String nombreOrigen = result.getString("nombre_origen");
            String tipoOrigen = result.getString("tipo_origen");
            Locacion origenLocacion = new Locacion(idLocacionOrigen, nombreOrigen, tipoOrigen);

            int idLocacionDestino = result.getInt("id_destino");
            String nombreDestino = result.getString("nombre_destino");
            String tipoDestino = result.getString("tipo_destino");
            Locacion destinoLocacion = new Locacion(idLocacionDestino, nombreDestino, tipoDestino);

            
            Viaje viaje = new Viaje(id, hora, precio, fecha, origenLocacion, destinoLocacion);
            viajes.add(viaje);
        }
        return viajes;
    }
}
