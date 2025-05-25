/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import com.ridenow.models.Cliente;
import com.ridenow.models.Reserva;
import com.ridenow.models.Viaje;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.DAOs.ReservaDAO;
import models.DTOs.ReservaDTO;

/**
 *
 * @author xlancet
 */
public class ReservaServicio {
    private ReservaDAO repositorio;
    
    public ReservaServicio() {
        repositorio = new ReservaDAO();
    }
    
     public ReservaDTO save(ReservaDTO reservaDTO) throws SQLException, ClassNotFoundException{
        Reserva reserva = dtoToModel(reservaDTO);
        reserva = repositorio.create(reserva);
        return reserva != null? modelToDto(reserva): null;
    }
    
    public ReservaDTO delete(ReservaDTO reservaDTO) throws SQLException, ClassNotFoundException {
        boolean eliminado = repositorio.delete(reservaDTO.getId());
        return eliminado? reservaDTO: null;
    }
    public ReservaDTO update(ReservaDTO reservaDTO) throws SQLException, ClassNotFoundException {
        Reserva reserva = dtoToModel(reservaDTO);
        boolean actualizado = repositorio.update(reserva);
        return actualizado? modelToDto(reserva): null;
    }

    public ReservaDTO get(ReservaDTO reservaDTO) throws SQLException, ClassNotFoundException{
        Reserva reserva = repositorio.get(reservaDTO.getId());
        return reserva!= null? modelToDto(reserva): null;
    }
    
    public List<ReservaDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Reserva> lista = repositorio.getAll();
        List<ReservaDTO> reservas = new ArrayList<>();
        for(Reserva reserva: lista) {
            reservas.add(modelToDto(reserva));
        }
        return reservas;
    }
    public List<ReservaDTO> filterByOwner(int id) throws SQLException, ClassNotFoundException {
        List<Reserva> lista = repositorio.filterByOwner(id);
        List<ReservaDTO> reservas = new ArrayList<>();
        for(Reserva reserva: lista) {
            reservas.add(modelToDto(reserva));
        }
        return reservas;
    }

    private Reserva dtoToModel(ReservaDTO dto) {
        return new Reserva(
            dto.getId(),
            0,
            new Cliente(dto.getIdCliente()),
            dto.getFecha(),
            0,
            "",
            new Viaje(dto.getIdViaje())   
        );
    }

    private ReservaDTO modelToDto(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setIdCliente(reserva.getCliente().getId());
        dto.setFecha(reserva.getFecha());
        dto.setIdViaje(reserva.getViaje().getId());
        return dto;
    }
    
}
