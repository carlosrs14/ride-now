/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import com.ridenow.models.Viaje;
import java.sql.SQLException;
import java.util.List;
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
    
    public boolean saveViaje(Viaje viaje) throws SQLException, ClassNotFoundException {
        return repositorio.saveViaje(viaje);
    }
    
    public List<Viaje> listar() throws SQLException, ClassNotFoundException {
        return repositorio.listar();
    }
    
    public Viaje buscar(int idViaje) throws SQLException, ClassNotFoundException {
        return repositorio.buscar(idViaje);
    }
    
    public boolean eliminarViaje(int idViaje) throws SQLException, ClassNotFoundException {
        return repositorio.eliminarViaje(idViaje);
    }
}
