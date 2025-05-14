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
        String consultaSQL = "INSERT INTO reservas (fechareserva, idcliente, idpago, idviaje) " + 
                             "VALUES(?, ?, ?, ?);";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        
        statement.setDate(1, Date.valueOf(reserva.getFecha()));
        statement.setInt(2, reserva.getCliente().getId());
        statement.setNull(3, Types.INTEGER);
        statement.setInt(4, reserva.getViaje().getId());
        
        int rows = statement.executeUpdate();
        if(rows > 0) {
            conexion.close();
            return true; 
        } else {
            conexion.close();
            return false;
        }   
    }
    
    public Reserva buscar(int idReserva) throws SQLException, ClassNotFoundException {
        String consultaSQL = "SELECT * FROM reservas WHERE idreserva = ? ";
        
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setInt(1, idReserva);
        
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String fecha = String.valueOf(result.getDate("fechareserva"));
            int idCliente = result.getInt("idcliente");
            int idPago = result.getInt("idpago");
            int idViaje = result.getInt("idviaje");
            // int numeroDePuestos = result.getInt("numerodepuestos");
            // float monto = result.getFloat("monto");
            // String tipoPago = result.getString("tipopago");
            Reserva reserva = new Reserva(idReserva, 0, null, fecha, 0, "efectivo", null);
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
