package com.ridenow.servicios;


import com.ridenow.modelos.Viaje;
import com.ridenow.repositorios.ViajeRepositorio;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author crinc
 */
public class ViajeServicio {
    private ViajeRepositorio viajeRepositorio;
    
    public ViajeServicio() {
        viajeRepositorio = new ViajeRepositorio();
    }
    
    public List<Viaje> obtenerViajes(String origen, String destino) {
        return viajeRepositorio.filtrarViajes(origen, destino);
    }
}
