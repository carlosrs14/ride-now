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
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author crinc
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
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
            JOptionPane.showMessageDialog(null, "aqui 1");
            clienteServicio.registrarCliente(cliente);
            JOptionPane.showMessageDialog(null, "aqui 2");
            request.setAttribute("mensaje", "El cliente ha sido creado correctamente.");
            request.getRequestDispatcher("./index.jsp").forward(request, response);
            //TODO enviar el cliente
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    */
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    /*
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    */

}
