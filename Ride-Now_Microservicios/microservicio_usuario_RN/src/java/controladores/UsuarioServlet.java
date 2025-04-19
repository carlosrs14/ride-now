/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import com.ridenow.models.Cliente;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import servicios.UsuarioServicio;

/**
 *
 * @author xlancet
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {


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
        
        try {
            servicio = new UsuarioServicio();
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");
            
            Cliente cliente = servicio.login(correo, password);
            
            response.setContentType("application/json");
            
            if (cliente != null) {
                response.getWriter().write("{\"mensaje\": \"Login exitoso\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"mensaje\": \"Login fallido\"}");
            }
        } catch (SQLException ex) {
            response.getWriter().write("{\"mensaje\": " + ex.toString() + "\"}");
        } catch (ClassNotFoundException ex) {
            response.getWriter().write("{\"mensaje\": " + ex.toString() + "\"}");
            
        }
    }
    private UsuarioServicio servicio;
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
        servicio = new UsuarioServicio();
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        
        String fechaStr = request.getParameter("fecha");
        String[] fechaParts = fechaStr.split("-");
        int anio = Integer.parseInt(fechaParts[0]);
        int mes = Integer.parseInt(fechaParts[1]);
        int dia = Integer.parseInt(fechaParts[2]);
        
        Cliente cliente = new Cliente(0, dia, mes, anio, nombre, apellido, correo, telefono, password);
        try {
            if (servicio.saveCliente(cliente)) {
                response.getWriter().write("{\"mensaje\": \"Registro exitoso\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"mensaje\": \"Registro fallido\"}");
            }
        } catch (SQLException ex) {
            response.getWriter().write("{\"mensaje\": " + ex.toString() + "\"}");
        } catch (ClassNotFoundException ex) {
            response.getWriter().write("{\"mensaje\": " + ex.toString() + "\"}");
            
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
