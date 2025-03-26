/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ridenow.presentaciones;

import com.ridenow.modelos.Cliente;
import com.ridenow.servicios.ClienteServicio;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 *
 * @author crinc
 */
@WebServlet(name = "ClienteControl", urlPatterns = {"/ClienteControl"})
public class ClienteControl extends HttpServlet {
    private ClienteServicio clienteServicio;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        clienteServicio = new ClienteServicio();
        
        String metodo = request.getParameter("metodo");
        
        if ("registrar".equals(metodo)) {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            
            // parsear la fecha
            String fechaStr = request.getParameter("fecha");
            String[] fechaParts = fechaStr.split("-");
            int anio = Integer.parseInt(fechaParts[0]);
            int mes = Integer.parseInt(fechaParts[1]);
            int dia = Integer.parseInt(fechaParts[2]);
            
            // TODO no solo se registran clientes
            
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordConfirm");
            if (!password.equals(passwordConfirm)) {
                request.setAttribute("mensaje", "El cliente NO ha sido creado correctamente.");
                
                request.getRequestDispatcher("./index.jsp").forward(request, response);
                PrintWriter out = response.getWriter();
                out.print("error con las contrasenas");
                out.flush();
                return;
            }
            
            Cliente cliente = new Cliente(0, dia, mes, anio, nombre, apellido, telefono, correo, password);
            clienteServicio.registrarCliente(cliente);
            request.setAttribute("mensaje", "El cliente ha sido creado correctamente.");
            request.getRequestDispatcher("./index.jsp").forward(request, response);
            //TODO enviar el cliente
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        clienteServicio = new ClienteServicio();
        
        String metodo = request.getParameter("metodo");
        if ("login".equals(metodo)) {
            String correo = request.getParameter("email");
            String password = request.getParameter("password");
            Cliente cliente = clienteServicio.login(correo, password);
            if (cliente != null ) {
                request.setAttribute("mensaje", "Login exitoso");
                HttpSession sesion = request.getSession();
                sesion.setAttribute("cliente", cliente);
                request.getRequestDispatcher("./jsp/viajes.jsp").forward(request, response);
            } else {
                
                request.setAttribute("mensaje", "Hubo un error");    
                request.getRequestDispatcher("./jsp/login.jsp").forward(request, response);
            }
        
        }
    }
    

}
