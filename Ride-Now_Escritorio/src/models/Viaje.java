/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author crinc
 */
public class Viaje {
    private int id;
    private int hora;
    private float precio;
    private Fecha fecha;
    private Locacion origen;
    private Locacion destino;

    public Viaje(int id, int hora, float precio, Fecha fecha, Locacion origen, Locacion destino) {
        this.id = id;
        this.hora = hora;
        this.precio = precio;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
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
    public Fecha getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Fecha fecha) {
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
    
}
