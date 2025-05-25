package models.estados;

import models.Denuncia;

/*
estado Pendiente de una denuncia
 */
public class EstadoPendiente implements EstadoDenuncia {
    @Override
    public void procesar(Denuncia denuncia) {
        denuncia.setEstado("En Proceso");
        denuncia.setEstadoDenuncia(new EstadoEnProceso());
    }

    @Override
    public void rechazar(Denuncia denuncia) {
        denuncia.setEstado("Rechazada");
        denuncia.setEstadoDenuncia(new EstadoRechazado());
    }

    @Override
    public void resolver(Denuncia denuncia) {
        //no podemos pasar de pendiente a resuelto de una vez
    }

    @Override
    public String getEstado() {
        return "Pendiente";
    }
} 