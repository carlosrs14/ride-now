/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import models.DAOs.VehiculoDAO;
import models.DTOs.VehiculoDTO;
import com.ridenow.models.PrestadorDeServicio;
import com.ridenow.models.Vehiculo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.DAOs.DAOFactory;

/**
 *
 * @author xlancet
 */
public class VehiculoServicio {
    private VehiculoDAO repositorio;

    public VehiculoServicio() {
        repositorio = DAOFactory.getVehiculoDAO();
    }
    
    public VehiculoDTO save(VehiculoDTO vehiculoDTO) throws SQLException, ClassNotFoundException {
        Vehiculo vehiculo = dtoToModel(vehiculoDTO);
        vehiculo = repositorio.create(vehiculo);
        return vehiculo != null? modelToDto(vehiculo): null;
    }
    
    public VehiculoDTO get(VehiculoDTO vehiculoDTO) throws SQLException, ClassNotFoundException {
        Vehiculo vehiculo = repositorio.get(vehiculoDTO.getId());
        return vehiculo != null? modelToDto(vehiculo): null;
    }
    
    public List<VehiculoDTO> all() throws SQLException, ClassNotFoundException {
        List<Vehiculo> lista = repositorio.getAll();
        List<VehiculoDTO> resultado = new ArrayList<>();
        for (Vehiculo v : lista) {
            resultado.add(modelToDto(v));
        }
        return resultado;
    }
    
    public VehiculoDTO update(VehiculoDTO vehiculoDTO) throws SQLException, ClassNotFoundException {
        Vehiculo vehiculo = dtoToModel(vehiculoDTO);
        boolean actualizado = repositorio.update(vehiculo);
        return actualizado? vehiculoDTO: null;
    }
    
    public VehiculoDTO delete(VehiculoDTO vehiculoDTO) throws SQLException, ClassNotFoundException {
        boolean eliminado = repositorio.delete(vehiculoDTO.getId());
        return eliminado? vehiculoDTO: null;
    }
    
    public List<VehiculoDTO> filtrarPorPrestador(int idPrestador) throws SQLException, ClassNotFoundException {
        List<Vehiculo> lista = repositorio.filterByOwner(idPrestador);
        List<VehiculoDTO> resultado = new ArrayList<>();
        for (Vehiculo v : lista) {
            resultado.add(modelToDto(v));
        }
        return resultado;
    }
    
    private Vehiculo dtoToModel(VehiculoDTO dto) {
        return new Vehiculo(
            dto.getId(),
            dto.getMarca(),
            dto.getModelo(),
            dto.getPlaca(),
            dto.isTieneAire(),
            dto.getColor(),
            dto.getCapacidad(),
            new PrestadorDeServicio(dto.getIdPrestadorDeServicio())
        );
    }

    private VehiculoDTO modelToDto(Vehiculo vehiculo) {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setId(vehiculo.getIdVehiculo());
        dto.setMarca(vehiculo.getMarca());
        dto.setModelo(vehiculo.getModelo());
        dto.setPlaca(vehiculo.getPlaca());
        dto.setTieneAire(vehiculo.isTieneAire());
        dto.setColor(vehiculo.getColor());
        dto.setCapacidad(vehiculo.getCapacidad());
        dto.setIdPrestadorDeServicio(vehiculo.getPrestadorDeServicio().getId());
        return dto;
    }
}
