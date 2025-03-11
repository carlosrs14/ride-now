/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

/**
 *
 * @author crinc
 */
public class Cliente extends Persona {
    private List<Viaje> misViajes;

    public Cliente(List<Viaje> misViajes, int id, int dia, int mes, int anio, String nombre, String apellido, String telefono, String correo, String password) {
        super(id, dia, mes, anio, nombre, apellido, telefono, correo, password);
        this.misViajes = misViajes;
    }

    @Override
    public String toString() {
        return "Cliente{" + "misViajes=" + misViajes + '}';
    }

    /**
     * @return the misViajes
     */
    public List<Viaje> getMisViajes() {
        return misViajes;
    }

    /**
     * @param misViajes the misViajes to set
     */
    public void setMisViajes(List<Viaje> misViajes) {
        this.misViajes = misViajes;
    }
    
    
    
}
