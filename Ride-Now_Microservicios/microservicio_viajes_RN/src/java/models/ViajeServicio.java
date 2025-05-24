/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.ridenow.models.Locacion;
import com.ridenow.models.Vehiculo;
import com.ridenow.models.Viaje;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.DAOs.ViajeDAO;
import models.DTOs.ViajeDTO;

/**
 *
 * @author xlancet
 */
public class ViajeServicio {
    private ViajeDAO repositorio;

    public ViajeServicio() {
        repositorio = new ViajeDAO();
    }
    
    public ViajeDTO save(ViajeDTO viajeDTO) throws SQLException, ClassNotFoundException{
        Viaje viaje = dtoToModel(viajeDTO);
        viaje = repositorio.create(viaje);
        return viaje != null? modelToDto(viaje): null;
    }
    
    public List<ViajeDTO> all() throws SQLException, ClassNotFoundException {
        List<Viaje> lista = repositorio.getAll();
        List<ViajeDTO> resultado = new ArrayList<>();
        for (Viaje viaje : lista) {
            resultado.add(modelToDto(viaje));
        }
        return resultado;
    }
    
    public ViajeDTO get(ViajeDTO viajeDTO) throws SQLException, ClassNotFoundException {
        Viaje viaje = repositorio.get(viajeDTO.getId());
        return viaje != null? modelToDto(viaje): null;
    }
    
    public ViajeDTO delete(ViajeDTO viajeDTO) throws SQLException, ClassNotFoundException {
        boolean eliminado = repositorio.delete(viajeDTO.getId());
        return eliminado? viajeDTO: null;
    }
    
    private Viaje dtoToModel(ViajeDTO dto) {
        return new Viaje(
            dto.getId(),
            dto.getHora(),
            dto.getPrecio(),
            dto.getTipo(),
            dto.getFecha(),
            new Locacion(dto.getIdLocacionOrigen()),
            new Locacion(dto.getIdLocacionOrigen()),
            new Vehiculo(dto.getIdVehiculo())
        );
    }

    private ViajeDTO modelToDto(Viaje viaje) {
        ViajeDTO dto = new ViajeDTO();
        dto.setId(viaje.getId());
        dto.setHora(viaje.getHora());
        dto.setPrecio(viaje.getPrecio());
        dto.setTipo(viaje.getTipo());
        dto.setIdLocacionOrigen(viaje.getOrigen().getId());
        dto.setIdLocacionDestino(viaje.getDestino().getId());
        dto.setIdVehiculo(viaje.getVehiculo().getIdVehiculo());
        return dto;
    }
}
