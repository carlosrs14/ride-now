package models.validacion;

import models.Denuncia;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
clase para validación de denuncias
implementación del principio de SOLID, Single Responsibility
 */
public class DenunciaValidador {
    
    // valido que todos los campos requeridos de una denuncia esten y sean correctos 
    
    public boolean validarDenuncia(Denuncia denuncia) {
        return validarCamposRequeridos(denuncia) &&
               validarFecha(denuncia.getFecha()) &&
               validarEstado(denuncia.getEstado());
    }
    
    private boolean validarCamposRequeridos(Denuncia denuncia) {
        return denuncia != null &&
               denuncia.getDescripcion() != null &&
               !denuncia.getDescripcion().trim().isEmpty() &&
               denuncia.getCliente() != null &&
               denuncia.getPrestadorDeServicio() != null;
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
    
    private boolean validarEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            return false;
        }
        
        return estado.equals("Pendiente") ||
               estado.equals("En Proceso") ||
               estado.equals("Resuelta") ||
               estado.equals("Rechazada");
    }
} 