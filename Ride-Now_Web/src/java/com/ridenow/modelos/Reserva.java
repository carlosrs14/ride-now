/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.modelos;

/**
 *
 * @author crinc
 */
public class Reserva {
    private int numeroDePuestos;
    private Cliente cliente;
    private Pago pago;
    private Fecha fecha;

    public Reserva(int numeroDePuestos, Cliente cliente, int dia, int mes, int anio, float monto, String tipo) {
        this.numeroDePuestos = numeroDePuestos;
        this.cliente = cliente;
        this.fecha = new Fecha(dia, mes, anio);
        this.pago = new Pago(monto, tipo) {};
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
    public Fecha getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
}
