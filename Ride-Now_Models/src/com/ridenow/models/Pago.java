/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.models;

/**
 *
 * @author xlancet
 */
public class Pago {
    private int id;
    private float monto;
    private String tipo;

    public Pago(int id) {
        this.id = id;
    }

    public Pago(int id, float monto, String tipo) {
        this.id = id;
        this.monto = monto;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pago{" + "id=" + id + ", monto=" + monto + ", tipo=" + tipo + '}';
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
     * @return the monto
     */
    public float getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(float monto) {
        this.monto = monto;
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
}
