/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xlancet
 */
public class PrestadorDeServicio extends Usuario {
    private float estrellas;
    private List<Recarga> recargas;

    public PrestadorDeServicio(int id) {
        super(id);
    }

    public PrestadorDeServicio(int id, String fecha, String nombre, String apellido, String tipo, String correo, String telefono, String password, float estrellas) {
        super(id, fecha, nombre, apellido, tipo, correo, telefono, password);
        this.estrellas = estrellas;
        recargas = new ArrayList<>();
    }
    
    public void addRegacarga(Recarga recarga) {
        recargas.add(recarga);
    }
    
    /**
     * @return the estrellas
     */
    public float getEstrellas() {
        return estrellas;
    }

    /**
     * @param estrellas the estrellas to set
     */
    public void setEstrellas(float estrellas) {
        this.estrellas = estrellas;
    }

    /**
     * @return the recargas
     */
    public List<Recarga> getRecargas() {
        return recargas;
    }

    /**
     * @param recargas the recargas to set
     */
    public void setRecargas(List<Recarga> recargas) {
        this.recargas = recargas;
    }
    
}