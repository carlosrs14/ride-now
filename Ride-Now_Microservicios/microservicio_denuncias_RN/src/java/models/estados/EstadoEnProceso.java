package models.estados;

import models.Denuncia;

/*
estado En Proceso de una denuncia
 */
public class EstadoEnProceso implements EstadoDenuncia {
    @Override
    public void procesar(Denuncia denuncia) {
        //ya esta en proceso
    }

    @Override
    public void rechazar(Denuncia denuncia) {
        denuncia.setEstado("Rechazada");
        denuncia.setEstadoDenuncia(new EstadoRechazado());
    }

    @Override
    public void resolver(Denuncia denuncia) {
        denuncia.setEstado("Resuelta");
        denuncia.setEstadoDenuncia(new EstadoResuelto());
    }

    @Override
    public String getEstado() {
        return "En Proceso";
    }
} 