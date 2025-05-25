package models;

import com.ridenow.models.Cliente;
import com.ridenow.models.PrestadorDeServicio;
import models.estados.EstadoDenuncia;
import models.estados.EstadoPendiente;

/*
clase de denuncia que usa el patrón state
 */
public class Denuncia {
    private int id;
    private String fecha;
    private String descripcion;
    private String estado;
    private Cliente cliente;
    private PrestadorDeServicio prestadorDeServicio;
    //atributo para el patrón state
    private EstadoDenuncia estadoDenuncia;

    public Denuncia(int id) {
        this.id = id;
        this.estadoDenuncia = new EstadoPendiente(); //estadode la denuncia por defecto
    }

    public Denuncia(int id, String fecha, String descripcion, String estado, Cliente cliente, PrestadorDeServicio prestadorDeServicio) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
        this.cliente = cliente;
        this.prestadorDeServicio = prestadorDeServicio;
        this.estadoDenuncia = new EstadoPendiente(); 
    }

    // metodos para el patrón State
    public void procesar() {
        estadoDenuncia.procesar(this);
    }

    public void rechazar() {
        estadoDenuncia.rechazar(this);
    }

    public void resolver() {
        estadoDenuncia.resolver(this);
    }

    public void setEstadoDenuncia(EstadoDenuncia estadoDenuncia) {
        this.estadoDenuncia = estadoDenuncia;
    }

    public EstadoDenuncia getEstadoDenuncia() {
        return estadoDenuncia;
    }

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PrestadorDeServicio getPrestadorDeServicio() {
        return prestadorDeServicio;
    }

    public void setPrestadorDeServicio(PrestadorDeServicio prestadorDeServicio) {
        this.prestadorDeServicio = prestadorDeServicio;
    }
} 