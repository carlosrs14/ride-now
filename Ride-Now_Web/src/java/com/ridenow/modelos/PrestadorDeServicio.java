/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.modelos;

/**
 *
 * @author crinc
 */
public class PrestadorDeServicio extends Persona {
    private float estrellas;

    public PrestadorDeServicio(int id, int dia, int mes, int anio, String nombre, String apellido, String telefono, String correo, String password, float estrellas) {
        super(id, dia, mes, anio, nombre, apellido, telefono, correo, password);
        this.estrellas = estrellas;
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
    
}
