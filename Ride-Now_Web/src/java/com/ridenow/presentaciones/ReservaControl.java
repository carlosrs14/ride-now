/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ridenow.presentaciones;

import com.ridenow.modelos.Cliente;
import com.ridenow.modelos.Reserva;
import com.ridenow.modelos.Viaje;
import com.ridenow.servicios.ReservaServicio;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author crinc
 */
@WebServlet(name = "ReservaControl", urlPatterns = {"/ReservaControl"})
public class ReservaControl extends HttpServlet {
    private ReservaServicio reservaServicio;
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reservaServicio = new ReservaServicio();
        
        String metodo = request.getParameter("metodo");
        if ("reservar".equals(metodo)) {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            int idViaje = Integer.parseInt(request.getParameter("idViaje"));
            float precio = Float.parseFloat(request.getParameter("monto"));
            Reserva reserva = new Reserva(1, new Cliente(idCliente), 0, 0, 0, 0, "Efectivo", new Viaje(idViaje));
            if (reservaServicio.registrarReserva(reserva)) {
                request.setAttribute("mensaje", "Reserva hecha correctamente");
            } else {
                request.setAttribute("mensaje", "Hubo un error al reservar");
            }
        } else {
            request.setAttribute("mensaje", "Hubo un error no se especifico la accion");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/viajes.jsp");
        dispatcher.forward(request, response);
        
    }

}
