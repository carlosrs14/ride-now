package models.factory;

import com.ridenow.models.Cliente;
import models.Denuncia;
import com.ridenow.models.PrestadorDeServicio;
import models.DTOs.DenunciaDTO;
import models.estados.EstadoPendiente;

/*
fabrica para crear denuncias
aplicación del patrón Factory Method
 */
public class DenunciaFactory {
    public Denuncia crearDenuncia(DenunciaDTO dto) {
        Denuncia denuncia = new Denuncia(
            dto.getId(),
            dto.getFecha(),
            dto.getDescripcion(),
            dto.getEstado() == null? "Pendiente": dto.getEstado(),
            new Cliente(dto.getIdCliente()),
            new PrestadorDeServicio(dto.getIdPrestadorDeServicio())
        );
        denuncia.setEstadoDenuncia(new EstadoPendiente());
        return denuncia;
    }
} 