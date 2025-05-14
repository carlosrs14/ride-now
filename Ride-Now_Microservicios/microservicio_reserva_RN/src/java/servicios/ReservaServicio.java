/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import com.ridenow.models.Reserva;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
    
     public Map<String, Object> saveReserva(Reserva reserva){
        Map<String, Object> resultado = new HashMap<>();
        try {
            boolean rta = repositorio.saveReserva(reserva);
            String mensaje = rta? "reserva guardada": "reserva NO guardada";
            resultado.put("mensaje", mensaje);
            resultado.put("resultado", "hecho");
        } catch (SQLException | ClassNotFoundException ex) {
            resultado.put("mensaje", ex.toString());
            
        }
        return resultado;
    }
    
      public Map<String, Object> eliminar(String idReservaStr) {
        Map<String, Object> resultado = new HashMap<>();
        if (idReservaStr == null) {
            resultado.put("mensaje", "debe escrbir un id");
            return resultado;
        }
        try {
            int idReserva = Integer.parseInt(idReservaStr);
            boolean rta = repositorio.eliminar(idReserva);
            String mensaje = rta? "reserva eliminada": "no se encontro la reserva";
            resultado.put("mensaje", mensaje);
            resultado.put("resultado", "hecho");
        } catch (SQLException | NumberFormatException  | ClassNotFoundException ex) {
            resultado.put("mensaje", ex.toString());
        }
        return resultado;
    }

    public Map<String, Object> buscar(String idReservaStr) {
      Map<String, Object> resultado = new HashMap<>();
      if (idReservaStr == null) {
          resultado.put("mensaje", "debe escrbir un id");
          return resultado;
      }
      try {
          int idReserva = Integer.parseInt(idReservaStr);
          Reserva reserva = repositorio.buscar(idReserva);
          resultado.put("reserva", reserva);
          resultado.put("resultado", "hecho");
      } catch (SQLException | NumberFormatException  | ClassNotFoundException ex) {
          resultado.put("mensaje", ex.toString());
      }
      return resultado;
    }
    
}
