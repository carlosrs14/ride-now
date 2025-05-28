/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.validacion;

import com.ridenow.models.Resena;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author xlancet
 */
public class ResenaValidador {
    public boolean validarResena(Resena resena) {
        return validarCamposRequeridos(resena) &&
               validarCalificacion(resena.getCalificacion());
    }
    
    private boolean validarCamposRequeridos(Resena resena) {
        return resena != null &&
               resena.getComentario()!= null &&
               !resena.getComentario().trim().isEmpty() &&
               resena.getCliente() != null &&
               resena.getPrestadorDeServicio() != null;
    }
    
    private boolean validarFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            return false;
        }
        
        try {
            LocalDate.parse(fecha, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    private boolean validarCalificacion(int calificacion) {
        return calificacion >= 1 && calificacion <= 5;
    }
}
