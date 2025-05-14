/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ridenow.models.Reserva;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import servicios.ReservaServicio;

/**
 *
 * @author xlancet
 */
@WebServlet(name = "ReservaServlet", urlPatterns = {"/ReservaServlet"})
public class ReservaServlet extends HttpServlet {
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
        ReservaServicio reservaServicio = new ReservaServicio();
        Map<String, Object> resultado;
        String idReservaStr = request.getParameter("idReserva");
        
        if(idReservaStr == null) {
            resultado = new HashMap<>();
            resultado.put("mensaje", "Debe proporcionar el id");
        } else {
            resultado = reservaServicio.buscar(idReservaStr);
        }
        escribirJson(response, resultado);  
    }

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
        Gson gson = new Gson();
        ReservaServicio reservaServicio = new ReservaServicio();
        Map<String, Object> resultado;
        
        BufferedReader reader = request.getReader();
        Reserva reserva = gson.fromJson(reader, Reserva.class);
        resultado = reservaServicio.saveReserva(reserva);
        escribirJson(response, resultado);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReservaServicio reservaServicio = new ReservaServicio();
        String idReservaStr = (String) request.getParameter("idReserva");
        Map<String, Object> resultado = reservaServicio.eliminar(idReservaStr);
        escribirJson(response, resultado);
    }
    
    private void escribirJson(HttpServletResponse response, Map<String, Object> resultado) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(resultado);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
