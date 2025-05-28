/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.factory;

import com.ridenow.models.Cliente;
import com.ridenow.models.PrestadorDeServicio;
import com.ridenow.models.Resena;
import java.util.Date;
import models.DTOs.ResenaDTO;

/**
 *
 * @author xlancet
 */
public class ResenaFactory {
    public Resena crearResena(ResenaDTO dto) {
        return new Resena(
            dto.getId(),
            dto.getCalificacion(),
            dto.getDescripcion(),
            new Date(dto.getFecha()),
            new Cliente(dto.getIdCliente()),
            new PrestadorDeServicio(dto.getIdPrestadorDeServicio())
        );
    }
}
