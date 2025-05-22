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
public class Resena {
    private int id;
    private int calificacion;
    private String comentario;
    private Date fecha;
    private Cliente cliente;
    private PrestadorDeServicio prestadorDeServicio;

    public Resena(int id) {
        this.id = id;
    }

    public Resena(int id, int calificacion, String comentario, Date fecha, Cliente cliente, PrestadorDeServicio prestadorDeServicio) {
        this.id = id;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
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
     * @return the calificacion
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
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
    
}
