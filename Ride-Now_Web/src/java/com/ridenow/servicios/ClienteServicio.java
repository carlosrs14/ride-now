/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.servicios;

import com.ridenow.modelos.Cliente;
import com.ridenow.repositorios.ClienteRepositorio;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author crinc
 */
public class ClienteServicio {
    private ClienteRepositorio clienteRepositorio;
    
    public ClienteServicio() {
        clienteRepositorio = new ClienteRepositorio();
    }
    
    public void registrarCliente(Cliente cliente) {
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "servicio 1");
            clienteRepositorio.saveCliente(cliente);
            JOptionPane.showMessageDialog(null, "servicio 2");
        } else {
            throw new IllegalArgumentException("los datos del cliente son invalidos");
        }
    }
    
    public Cliente login(String email, String password) {
        return clienteRepositorio.login(email, password);
    }
}
