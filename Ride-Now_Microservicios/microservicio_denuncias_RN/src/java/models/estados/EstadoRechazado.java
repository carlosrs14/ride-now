package models.estados;

import models.Denuncia;

/*
estado Rechazado de una deuncia
 */
public class EstadoRechazado implements EstadoDenuncia {
    @Override
    public void procesar(Denuncia denuncia) {
        //no podemos procesar una denuncia rechazada
    }

    @Override
    public void rechazar(Denuncia denuncia) {
        //ya estarechazada
    }

    @Override
    public void resolver(Denuncia denuncia) {
        //no podemos resolver una deuncia rechazada
    }

    @Override
    public String getEstado() {
        return "Rechazada";
    }
} 