/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DTOs;

/**
 *
 * @author xlancet
 */
public class VehiculoDTO {
    private int id;
    private String marca;
    private int modelo;
    private String placa;
    private boolean tieneAire;
    private String color;
    private int capacidad;
    private int idPrestadorDeServicio;

    public VehiculoDTO() {
    }
    
    public VehiculoDTO(int id, String marca, int modelo, String placa, boolean tieneAire, String color, int capacidad, int idPrestadorDeServicio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.tieneAire = tieneAire;
        this.color = color;
        this.capacidad = capacidad;
        this.idPrestadorDeServicio = idPrestadorDeServicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isTieneAire() {
        return tieneAire;
    }

    public void setTieneAire(boolean tieneAire) {
        this.tieneAire = tieneAire;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getIdPrestadorDeServicio() {
        return idPrestadorDeServicio;
    }

    public void setIdPrestadorDeServicio(int idPrestadorDeServicio) {
        this.idPrestadorDeServicio = idPrestadorDeServicio;
    }
}
