/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.models;

/**
 *
 * @author xlancet
 */
public class Cliente extends Usuario {

    public Cliente(int id) {
        super(id);
    }

    public Cliente(int id, String fecha, String nombre, String apellido, String tipo, String correo, String telefono, String password) {
        super(id, fecha, nombre, apellido, tipo, correo, telefono, password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
