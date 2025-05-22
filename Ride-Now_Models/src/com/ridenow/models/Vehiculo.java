/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.models;

/**
 *
 * @author xlancet
 */
public class Vehiculo {
    private int id;
    private String marca;
    private int modelo;
    private String placa;
    private boolean tieneAire;
    private String color;
    private int capacidad;
    private PrestadorDeServicio prestadorDeServicio;

    public Vehiculo(int id) {
        this.id = id;
    }

    public Vehiculo(int idVehiculo, String marca, int modelo, String placa, boolean tieneAire, String color, int capacidad, PrestadorDeServicio prestadorDeServicio) {
        this.id = idVehiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.tieneAire = tieneAire;
        this.color = color;
        this.capacidad = capacidad;
        this.prestadorDeServicio = prestadorDeServicio;
    }

    /**
     * @return the idVehiculo
     */
    public int getIdVehiculo() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setIdVehiculo(int id) {
        this.id = id;
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
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
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
