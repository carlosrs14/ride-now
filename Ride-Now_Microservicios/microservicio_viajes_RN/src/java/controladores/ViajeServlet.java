/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import models.ViajeServicio;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.List;
import models.DTOs.ViajeDTO;

/**
 *
 * @author xlancet
 */
@WebServlet(name = "ViajeServlet", urlPatterns = {"/ViajeServlet"})
public class ViajeServlet extends HttpServlet {
    
    private ViajeServicio servicio;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        servicio = new ViajeServicio();
    }

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        
        String idViajeParam = request.getParameter("id");
        try {
            if (idViajeParam == null) {
            String idPrestadorDeServicioParam = request.getParameter("idOwner");

                if (idPrestadorDeServicioParam == null) {
                    List<ViajeDTO> vehiculos = servicio.all();
                    response.getWriter().write(gson.toJson(vehiculos));
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                }
                int idPrestadorDeServicio = Integer.parseInt(idPrestadorDeServicioParam);
                List<ViajeDTO> viajes = servicio.filterByOwner(idPrestadorDeServicio);
                response.getWriter().write(gson.toJson(viajes));
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            int id = Integer.parseInt(idViajeParam);
            ViajeDTO viajeDTO = new ViajeDTO();
            viajeDTO.setId(id);
            ViajeDTO resultado = servicio.get(viajeDTO);
            
            if (resultado != null) {
                response.getWriter().write(gson.toJson(resultado));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.getWriter().write(gson.toJson(new Mensaje("Viaje no encontrado")));
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        BufferedReader reader = request.getReader();
        
        ViajeDTO viaje = gson.fromJson(reader, ViajeDTO.class);
        ViajeDTO created = null;
        
        try {
            created = servicio.save(viaje);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (created != null) {
            response.getWriter().write(gson.toJson(created));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("No creado")));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        int id = Integer.parseInt(request.getParameter("id"));
        ViajeDTO viajeDTO = new ViajeDTO();
        viajeDTO.setId(id);
        ViajeDTO eliminado = null;
        try {
            eliminado = servicio.delete(viajeDTO);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (eliminado != null) {
            response.getWriter().write(gson.toJson(eliminado));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("Viaje no encontrado")));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    class Mensaje {
        private String mensaje;

        public Mensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
