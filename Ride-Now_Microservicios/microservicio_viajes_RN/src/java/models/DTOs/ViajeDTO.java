/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DTOs;

import java.util.Date;

/**
 *
 * @author xlancet
 */
public class ViajeDTO {
    private int id;
    private int hora;
    private float precio;
    private Date fecha;
    private String tipo;
    private int idLocacionOrigen;
    private int idLocacionDestino;
    private int idVehiculo;

    public ViajeDTO() {
    }

    public ViajeDTO(int id, int hora, float precio, Date fecha, String tipo, int idLocacionOrigen, int idLocacionDestino, int idVehiculo) {
        this.id = id;
        this.hora = hora;
        this.precio = precio;
        this.fecha = fecha;
        this.tipo = tipo;
        this.idLocacionOrigen = idLocacionOrigen;
        this.idLocacionDestino = idLocacionDestino;
        this.idVehiculo = idVehiculo;
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
     * @return the idLocacionOrigen
     */
    public int getIdLocacionOrigen() {
        return idLocacionOrigen;
    }

    /**
     * @param idLocacionOrigen the idLocacionOrigen to set
     */
    public void setIdLocacionOrigen(int idLocacionOrigen) {
        this.idLocacionOrigen = idLocacionOrigen;
    }

    /**
     * @return the idLocacionDestino
     */
    public int getIdLoacionDestino() {
        return idLocacionDestino;
    }

    /**
     * @param idLoccionDestino the idLoccionDestino to set
     */
    public void setIdLocacionDestino(int idLocacionDestino) {
        this.idLocacionDestino = idLocacionDestino;
    }

    /**
     * @return the idVehiculo
     */
    public int getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * @param idVehiculo the idVehiculo to set
     */
    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
}
