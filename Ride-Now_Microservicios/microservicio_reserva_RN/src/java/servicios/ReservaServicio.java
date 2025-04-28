/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import com.ridenow.models.Reserva;
import java.sql.SQLException;
import repositorios.ReservaRepositorio;

/**
 *
 * @author xlancet
 */
public class ReservaServicio {
    private ReservaRepositorio repositorio;
    
    public ReservaServicio() {
        repositorio = new ReservaRepositorio();
    }
    
    public boolean saveReserva(Reserva reserva) throws ClassNotFoundException, SQLException {
        return repositorio.saveReserva(reserva);
    }
    
    public boolean eliminar(int idReserva) throws SQLException, ClassNotFoundException {
        return repositorio.eliminar(idReserva);
    }
    
    public Reserva buscar(int idReserva) throws SQLException, ClassNotFoundException {
        return repositorio.buscar(idReserva);
    }
    
}
