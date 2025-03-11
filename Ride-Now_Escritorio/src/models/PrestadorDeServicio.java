/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crinc
 */
public class PrestadorDeServicio extends Persona {
    private float estrellas;
    private List<Vehiculo> vehiculos;

    public PrestadorDeServicio(int id, int dia, int mes, int anio, String nombre, String apellido, String telefono, String correo, String password, float estrellas) {
        super(id, dia, mes, anio, nombre, apellido, telefono, correo, password);
        vehiculos = new ArrayList<>();
        this.estrellas = estrellas;
    }
    
    public void addVehiculo(Vehiculo x) {
        vehiculos.add(x);
    }
   
    /**
     * @return the vehiculos
     */
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    /**
     * @param vehiculos the vehiculos to set
     */
    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
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
