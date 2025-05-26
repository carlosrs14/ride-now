
package models.DTOs;

/*
DTO para la transfrencia de datos de las denuncias
 */
public class DenunciaDTO {
    private int id;
    private String fecha;
    private String descripcion;
    private String estado;
    private int idCliente;
    private int idPrestadorDeServicio;

    public DenunciaDTO() {
    }

    public DenunciaDTO(int id, String fecha, String descripcion, String estado, int idCliente, int idPrestadorDeServicio) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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