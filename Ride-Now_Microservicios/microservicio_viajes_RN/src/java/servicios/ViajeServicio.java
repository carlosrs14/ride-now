/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import com.ridenow.models.Viaje;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import repositorios.ViajeRepositorio;

/**
 *
 * @author xlancet
 */
public class ViajeServicio {
    private ViajeRepositorio repositorio;

    public ViajeServicio() {
        repositorio = new ViajeRepositorio();
    }
    
    public Map<String, Object> saveViaje(Viaje viaje){
        Map<String, Object> resultado = new HashMap<>();
        try {
            boolean rta = repositorio.saveViaje(viaje);
            String mensaje = rta? "viaje guardado": "viaje NO guardado";
            resultado.put("mensaje", mensaje);
            resultado.put("resultado", "hecho");
        } catch (SQLException | ClassNotFoundException ex) {
            resultado.put("mensaje", ex.toString());
            
        }
        return resultado;
    }
    
    public Map<String, Object> listar() {
        Map<String, Object> resultado = new HashMap<>();
        List<Viaje> viajes;
        try {
            viajes = repositorio.listar();
            resultado.put("resultado", "hecho");
            resultado.put("viajes", viajes);
        } catch (SQLException | ClassNotFoundException ex) {
            resultado.put("mensaje", ex.toString());
        }
        return resultado;
    }
    
    public Map<String, Object> buscar(String idViajeStr) {
        Map<String, Object> resultado = new HashMap<>();
        if (idViajeStr == null) {
            resultado.put("mensaje", "debe escrbir un id");
            return resultado;
        }
        try {
            int idViaje = Integer.parseInt(idViajeStr);
            Viaje viaje = repositorio.buscar(idViaje);
            resultado.put("viaje", viaje);
            resultado.put("resultado", "hecho");
        } catch (SQLException | NumberFormatException  | ClassNotFoundException ex) {
            resultado.put("mensaje", ex.toString());
        }
        return resultado;
    }
    
    public Map<String, Object> eliminarViaje(String idViajeStr) {
        Map<String, Object> resultado = new HashMap<>();
        if (idViajeStr == null) {
            resultado.put("mensaje", "debe escrbir un id");
            return resultado;
        }
        try {
            int idViaje = Integer.parseInt(idViajeStr);
            boolean rta = repositorio.eliminarViaje(idViaje);
            String mensaje = rta? "viaje elimindado": "no se encontro el viaje";
            resultado.put("mensaje", mensaje);
            resultado.put("resultado", "hecho");
        } catch (SQLException | NumberFormatException  | ClassNotFoundException ex) {
            resultado.put("mensaje", ex.toString());
        }
        return resultado;
    }
}
