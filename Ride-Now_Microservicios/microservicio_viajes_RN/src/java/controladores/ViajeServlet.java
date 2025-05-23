/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ridenow.models.Viaje;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xlancet
 */
@WebServlet(name = "ViajeServlet", urlPatterns = {"/ViajeServlet"})
public class ViajeServlet extends HttpServlet {


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
        ViajeServicio viajeServicio = new ViajeServicio();
        Map<String, Object> resultado;
        String metodo = request.getParameter("metodo");
        
        if (metodo == null) {
            resultado = new HashMap<>();
            resultado.put("mensaje", "debe especificar el metodo");
            escribirJson(response, resultado);
            return;
        }
        
        switch (metodo) {
            case "listar" -> resultado = viajeServicio.listar();
            case "buscar" -> {
                String idViajeStr = request.getParameter("idViaje");
                resultado = viajeServicio.buscar(idViajeStr);
            }
            default -> {
                resultado = new HashMap<String, Object>();
                resultado.put("mensaje", "no existe el metodo");
            }
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
        ViajeServicio viajeServicio = new ViajeServicio();
        Map<String, Object> resultado;
        
        BufferedReader reader = request.getReader();
        Viaje viaje = gson.fromJson(reader, Viaje.class);
        resultado = viajeServicio.saveViaje(viaje);
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
        ViajeServicio viajeServicio = new ViajeServicio();
        String idViajeStr = (String) request.getParameter("idViaje");
        Map<String, Object> resultado = viajeServicio.eliminarViaje(idViajeStr);
        escribirJson(response, resultado);
    }
    
    private void escribirJson(HttpServletResponse response, Map<String, Object> resultado) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(resultado);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
