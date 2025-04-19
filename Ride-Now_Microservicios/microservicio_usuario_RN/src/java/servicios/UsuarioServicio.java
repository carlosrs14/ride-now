/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import com.ridenow.models.Cliente;
import java.sql.SQLException;
import repositorios.UsuarioRepositorio;

/**
 *
 * @author xlancet
 */
public class UsuarioServicio {
    private UsuarioRepositorio repositorio;
    public UsuarioServicio() {
        repositorio = new UsuarioRepositorio();
    }
    
    public boolean saveCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        // todo hacer algunas validaciones
        // email
        // password
        // encriptador
        return repositorio.saveCliente(cliente);
    }
    
    public Cliente login(String email, String password) throws SQLException, ClassNotFoundException {
        return repositorio.login(email, password);
    }
}