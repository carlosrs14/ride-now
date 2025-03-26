/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.repositorios;

import com.ridenow.accesodatos.Conexion;
import com.ridenow.modelos.Reserva;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author crinc
 */
public class ReservasRespositorio {

    public ReservasRespositorio() {
    }
    // TODO reservar
    // TODO ver reservas
    public boolean saveReserva(Reserva reserva) {
        String consultaReservaSQL = "INSERT INTO reservas (fechareserva, idcliente, idviaje)" +
                " VALUES (?, ?, ?);";
        try {
            Connection conexion = Conexion.getConexion();
            PreparedStatement statement = conexion.prepareStatement(consultaReservaSQL);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, reserva.getCliente().getId());
            statement.setInt(3, reserva.getViaje().getId());
            
            int result = statement.executeUpdate();
            if (result != 0) {
                return true;
            }
            conexion.close();
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.toString());
            Logger.getLogger(ReservasRespositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
