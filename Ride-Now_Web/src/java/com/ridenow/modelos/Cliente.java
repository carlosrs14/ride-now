/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.modelos;

import java.util.List;

/**
 *
 * @author crinc
 */
public class Cliente extends Persona {

    public Cliente(int id) {
        super(id);
    }

    public Cliente(int id, int dia, int mes, int anio, String nombre, String apellido, String telefono, String correo, String password) {
        super(id, dia, mes, anio, nombre, apellido, telefono, correo, password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
