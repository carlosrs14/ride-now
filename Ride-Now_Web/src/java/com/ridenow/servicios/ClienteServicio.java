/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.servicios;

import com.ridenow.modelos.Cliente;
import com.ridenow.repositorios.ClienteRepositorio;

/**
 *
 * @author crinc
 */
public class ClienteServicio {
    private ClienteRepositorio clienteRepositorio;
    
    public ClienteServicio() {
        clienteRepositorio = new ClienteRepositorio();
    }
    
    public boolean registrarCliente(Cliente cliente) {
        if (cliente != null) {
            return clienteRepositorio.saveCliente(cliente);
        } else {
            return false;
        }
    }
    
    public Cliente login(String email, String password) {
        return clienteRepositorio.login(email, password);
    }
}
