/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import accesodatos.Conexion;
import com.ridenow.models.Reserva;
import java.sql.*;

/**
 *
 * @author xlancet
 */
public class ReservaRepositorio {
    public ReservaRepositorio() {
    }
    public boolean saveReserva(Reserva reserva) throws SQLException, ClassNotFoundException {
        String consultaSQL = "INSERT INTO reservas(fechareserva, idcliente, idpago, idviaje) " + 
                                    "VALUES(?, ?, ?, ?);";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        String anio = String.valueOf(reserva.getFecha().getYear());
        String mes = String.valueOf(reserva.getFecha().getMonth());
        String dia = String.valueOf(reserva.getFecha().getDay());
        String fecha = anio + "-" + mes + "-" + dia;
        
        statement.setDate(1, Date.valueOf(fecha));
        statement.setInt(2, reserva.getCliente().getId());
        statement.setInt(3, 0);
        statement.setInt(4, reserva.getViaje().getId());
        
        ResultSet result = statement.executeQuery();
        if(result.next()) {
            conexion.close();
            return true; 
        } else {
            conexion.rollback();
            conexion.close();
            return false;
        }   
    }
    
    public Reserva buscar(int idReserva) throws SQLException, ClassNotFoundException {
        String consultaSQL = "SELECT * FROM reservas WHERE idreserva = ?;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setInt(1, idReserva);
        
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int dia = 0;
            int mes = 0;
            int anio = 0;
            Date fecha = new Date(dia, mes, anio);
            int idCliente = result.getInt("idcliente");
            int idPago = result.getInt("idpago");
            int idViaje = result.getInt("idviaje");
            int numeroDePuestos = result.getInt("nnumerodepuestos");
            float monto = result.getFloat("monto");
            String tipoPago = result.getString("tipopago");
            Reserva reserva = new Reserva(idReserva, numeroDePuestos, null, dia, mes, anio, monto, tipoPago, null);
            return reserva;
        } else {
            return null;
        }
    }
    
    public boolean eliminar(int idReserva) throws SQLException, ClassNotFoundException {
        String consultaSQL = "DELETE FROM reservas WHERE idreserva = ?;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setInt(1, idReserva);
        
        int filasAfectadas = statement.executeUpdate();
        return filasAfectadas > 0;
    }
    
    
}
