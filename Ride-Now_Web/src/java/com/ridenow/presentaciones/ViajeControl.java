/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ridenow.presentaciones;

import com.ridenow.modelos.Viaje;
import com.ridenow.servicios.ViajeServicio;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author crinc
 */
@WebServlet(name = "ViajeControl", urlPatterns = {"/ViajeControl"})
public class ViajeControl extends HttpServlet {
    
    private ViajeServicio viajeServicio;
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        viajeServicio = new ViajeServicio();
        
        String metodo = request.getParameter("metodo");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(("/jsp/viajes.jsp"));
        if ("filtrarViajes".equals(metodo)) {
            String origen = request.getParameter("origen");
            String destino = request.getParameter("destino");

            List<Viaje> viajes = viajeServicio.obtenerViajes(origen, destino);
            request.setAttribute("viajes", viajes);
            dispatcher.forward(request, response);
            return;
        }
        request.setAttribute("mensaje", "Hubo un error al consultar los viajes");
        dispatcher.forward(request, response);
    }
 // request.getRequestDispatcher("/jsp/viajes.jsp").forward(request, response);
}
