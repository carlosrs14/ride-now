/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DTOs;

/**
 *
 * @author xlancet
 */
public class ResenaDTO {
    private int id;
    private String fecha;
    private String descripcion;
    private int calificacion;
    private int idCliente;
    private int idPrestadorDeServicio;

    public ResenaDTO() {
    }

    public ResenaDTO(int id, String fecha, String descripcion, int calificacion, int idCliente, int idPrestadorDeServicio) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.idCliente = idCliente;
        this.idPrestadorDeServicio = idPrestadorDeServicio;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPrestadorDeServicio() {
        return idPrestadorDeServicio;
    }

    public void setIdPrestadorDeServicio(int idPrestadorDeServicio) {
        this.idPrestadorDeServicio = idPrestadorDeServicio;
    }
}
