package models.estados;

import models.Denuncia;

/*
estado Resuelto de una denuncia
 */
public class EstadoResuelto implements EstadoDenuncia {
    @Override
    public void procesar(Denuncia denuncia) {
        //no ha de poderseprocesar una denuncia resuelta
    }

    @Override
    public void rechazar(Denuncia denuncia) {
        //no se puede rechazar una denuncia resuelta
    }

    @Override
    public void resolver(Denuncia denuncia) {
        //ya estría resuelta
    }

    @Override
    public String getEstado() {
        return "Resuelta";
    }
} 