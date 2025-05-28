/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.ridenow.models.Cliente;
import com.ridenow.models.PrestadorDeServicio;
import com.ridenow.models.Resena;
import java.util.ArrayList;
import java.util.List;
import models.DAOs.ResenaDAO;
import models.DTOs.ResenaDTO;
import models.factory.ResenaFactory;
import models.validacion.ResenaValidador;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author xlancet
 */
public class ResenaServicio {
    private final ResenaDAO repositorio;
    private final ResenaValidador validador;
    private final ResenaFactory resenaFactory;
    
    public ResenaServicio() {
        this.repositorio = new ResenaDAO();
        this.validador = new ResenaValidador();
        this.resenaFactory = new ResenaFactory();
    }
    
    public ResenaDTO save(ResenaDTO resenaDTO) throws SQLException, ClassNotFoundException {
        // Crear reseña usando la fábrica
        Resena resena = resenaFactory.crearResena(resenaDTO);
        
        // Validar la reseña antes de guardar
        if (!validador.validarResena(resena)) {
            return null;
        }
        
        resena = repositorio.create(resena);
        return resena != null ? modelToDto(resena) : null;
    }
    
    public ResenaDTO get(ResenaDTO resenaDTO) throws SQLException, ClassNotFoundException {
        Resena resena = repositorio.get(resenaDTO.getId());
        return resena != null ? modelToDto(resena) : null;
    }
    
    public List<ResenaDTO> all() throws SQLException, ClassNotFoundException {
        List<Resena> lista = repositorio.getAll();
        List<ResenaDTO> resultado = new ArrayList<>();
        for (Resena resena: lista) {
            resultado.add(modelToDto(resena));
        }
        return resultado;
    }
    
    public ResenaDTO update(ResenaDTO resenaDTO) throws SQLException, ClassNotFoundException {
        Resena resena = dtoToModel(resenaDTO);
        boolean actualizado = repositorio.update(resena);
        return actualizado ? modelToDto(resena) : null;
    }
    
    public ResenaDTO delete(ResenaDTO resenaDTO) throws SQLException, ClassNotFoundException {
        boolean eliminado = repositorio.delete(resenaDTO.getId());
        return eliminado ? resenaDTO : null;
    }
    
    public List<ResenaDTO> getResenasByPrestador(int idPrestador) throws SQLException, ClassNotFoundException {
        List<Resena> lista = repositorio.filterByOwner(idPrestador);
        List<ResenaDTO> resultado = new ArrayList<>();
        for (Resena resena: lista) {
            resultado.add(modelToDto(resena));
        }
        return resultado;
    }
    
    private Resena dtoToModel(ResenaDTO dto) {
        return new Resena(
            dto.getId(),
            dto.getCalificacion(),
            dto.getDescripcion(),
            new Date(),
            new Cliente(dto.getIdCliente()),
            new PrestadorDeServicio(dto.getIdPrestadorDeServicio())
        );
    }
    
    private ResenaDTO modelToDto(Resena resena) {
        return new ResenaDTO(
            resena.getId(),
            "",
            resena.getComentario(),
            resena.getCalificacion(),
            resena.getCliente().getId(),
            resena.getPrestadorDeServicio().getId()
        );
    }
    
}
