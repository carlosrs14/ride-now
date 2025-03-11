/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import models.Cliente;
import models.Viaje;

/**
 *
 * @author crinc
 */
public class ClienteController {

    public ClienteController() {
        
    }
    
    public static Cliente crearCliente(String nombre, String apellido, int dia, int mes, int anio, String telefono,  String correo, String password) {
        return new Cliente(new ArrayList<Viaje>(), 0, dia, mes, anio, nombre, apellido, telefono, correo, password);
    }
    
}
