/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import models.VehiculoServicio;
import models.DTOs.VehiculoDTO;
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


/**
 *
 * @author xlancet
 */
@WebServlet(name = "VehiculoServlet", urlPatterns = {"/VehiculoServlet"})
public class VehiculoServlet extends HttpServlet {
    
    private VehiculoServicio servicio;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        servicio = new VehiculoServicio();
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
        
        String idVehiculoParam = request.getParameter("id");
        try {
            if (idVehiculoParam == null) {
                String idPrestadorDeServicioParam = request.getParameter("idPrestadorDeServicio");
            
                if (idPrestadorDeServicioParam == null) {
                    List<VehiculoDTO> vehiculos = servicio.all();
                    response.getWriter().write(gson.toJson(vehiculos));
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                }
                
                int idPrestadorDeServicio = Integer.parseInt(idPrestadorDeServicioParam);
                List<VehiculoDTO> vehiculos = servicio.filtrarPorPrestador(idPrestadorDeServicio);
                response.getWriter().write(gson.toJson(vehiculos));
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            
            int id = Integer.parseInt(idVehiculoParam);
            VehiculoDTO vehiculoDTO = new VehiculoDTO();
            vehiculoDTO.setId(id);
            VehiculoDTO resultado = servicio.get(vehiculoDTO);
        
            if (resultado != null) {
                response.getWriter().write(gson.toJson(resultado));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.getWriter().write(gson.toJson(new Mensaje("Vehiculo no encontrado")));
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
            Gson gson = new Gson();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            BufferedReader reader = request.getReader();
            
            VehiculoDTO vehiculo = gson.fromJson(reader, VehiculoDTO.class);
            VehiculoDTO created = null;
        try {
            created = servicio.save(vehiculo);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (created != null) {
            response.getWriter().write(gson.toJson(created));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("El vehiculo no fue creado")));
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        int id = Integer.parseInt(request.getParameter("id"));
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(id);
        VehiculoDTO eliminado = null;
        try {
            eliminado = servicio.delete(vehiculoDTO);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (eliminado != null) {
            response.getWriter().write(gson.toJson(eliminado));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("Vehiculo no encontrado")));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } 
    }

    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("aplication/json");
       response.setCharacterEncoding("UTF-8");
       Gson gson = new Gson();
       
       BufferedReader reader = request.getReader();
       VehiculoDTO vehiculoDTO = gson.fromJson(reader, VehiculoDTO.class);
       
       VehiculoDTO actualizado = null;
        try {
            actualizado = servicio.update(vehiculoDTO);
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            return;
        }
        if(actualizado != null) {
            response.getWriter().write(gson.toJson(actualizado));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("Vehiculo no encontrado")));
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
