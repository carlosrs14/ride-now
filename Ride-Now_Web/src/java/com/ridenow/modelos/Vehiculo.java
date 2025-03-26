/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.modelos;

/**
 *
 * @author crinc
 */
public class Vehiculo {
    private int id;
    private int modelo;
    private String placa;
    private String marca;
    private String color;
    private boolean tieneAire;
    private PrestadorDeServicio prestadorDeServicio;

    public Vehiculo(int id, int modelo, String placa, String marca, String color, boolean tieneAire, PrestadorDeServicio prestadorDeServicio) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.tieneAire = tieneAire;
        this.prestadorDeServicio = prestadorDeServicio;
    }

    /**
     * @return the modelo
     */
    public int getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the tieneAire
     */
    public boolean isTieneAire() {
        return tieneAire;
    }

    /**
     * @param tieneAire the tieneAire to set
     */
    public void setTieneAire(boolean tieneAire) {
        this.tieneAire = tieneAire;
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
     * @return the prestadorDeServicio
     */
    public PrestadorDeServicio getPrestadorDeServicio() {
        return prestadorDeServicio;
    }

    /**
     * @param prestadorDeServicio the prestadorDeServicio to set
     */
    public void setPrestadorDeServicio(PrestadorDeServicio prestadorDeServicio) {
        this.prestadorDeServicio = prestadorDeServicio;
    }
}
