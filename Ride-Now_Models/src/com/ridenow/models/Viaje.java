/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.models;

import java.util.Date;

/**
 *
 * @author xlancet
 */
public class Viaje {
    private int id;
    private int hora;
    private float precio;
    private Date fecha;
    private Locacion origen;
    private Locacion destino;
    private String tipo;
    private Vehiculo vehiculo;

    public Viaje(int id, int hora, float precio, String tipo, Date fecha, Locacion origen, Locacion destino, Vehiculo vehiculo) {
        this.id = id;
        this.hora = hora;
        this.precio = precio;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.tipo = tipo;
        this.vehiculo = vehiculo;
        
    }

    public Viaje(int idViaje) {
        this.id = idViaje;
    }

    @Override
    public String toString() {
        return "Viaje{" + "id=" + id + ", hora=" + hora + ", precio=" + precio + ", fecha=" + fecha + ", origen=" + origen + ", destino=" + destino + '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the hora
     */
    public int getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(int hora) {
        this.hora = hora;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the origen
     */
    public Locacion getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Locacion origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public Locacion getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Locacion destino) {
        this.destino = destino;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the vehiculo
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
