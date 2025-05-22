/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.models;

/**
 *
 * @author xlancet
 */
public class Reserva {
    private int id;
    private int numeroDePuestos;
    private Cliente cliente;
    private Pago pago;
    private String fecha;
    private Viaje viaje;

    public Reserva(int id) {
        this.id = id;
    }
            
    public Reserva(int id, int numeroDePuestos, Cliente cliente, String fecha, float monto, String tipoPago, Viaje viaje) {
        this.id = id;
        this.numeroDePuestos = numeroDePuestos;
        this.cliente = cliente;
        this.fecha = fecha;
        this.pago = null; //new Pago(monto, tipoPago);
        this.viaje = viaje;
    }

    /**
     * @return the numeroDePuestos
     */
    public int getNumeroDePuestos() {
        return numeroDePuestos;
    }

    /**
     * @param numeroDePuestos the numeroDePuestos to set
     */
    public void setNumeroDePuestos(int numeroDePuestos) {
        this.numeroDePuestos = numeroDePuestos;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the pago
     */
    public Pago getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(Pago pago) {
        this.pago = pago;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the viaje
     */
    public Viaje getViaje() {
        return viaje;
    }

    /**
     * @param viaje the viaje to set
     */
    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
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
}
