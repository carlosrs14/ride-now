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
public class Denuncia {
    private int id;
    private String motivo;
    private Date fecha;
    private String estado;
    private Cliente cliente;
    private PrestadorDeServicio prestadorDeServicio;

    public Denuncia(int id) {
        this.id = id;
    }

    public Denuncia(int id, String motivo, Date fecha, String estado, Cliente cliente, PrestadorDeServicio prestadorDeServicio) {
        this.id = id;
        this.motivo = motivo;
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.prestadorDeServicio = prestadorDeServicio;
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
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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
