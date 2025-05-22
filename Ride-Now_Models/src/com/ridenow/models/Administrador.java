/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.models;

/**
 *
 * @author xlancet
 */
public class Administrador extends Usuario {

    public Administrador(int id) {
        super(id);
    }

    public Administrador(int id, String fecha, String nombre, String apellido, String tipo, String correo, String telefono, String password) {
        super(id, fecha, nombre, apellido, tipo, correo, telefono, password);
    }
    
}
