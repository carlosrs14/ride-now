/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOs.ResenaDTO;
import models.ResenaServicio;

/**
 *
 * @author xlancet
 */
@WebServlet(name = "ResenaServlet", urlPatterns = {"/ResenaServlet"})
public class ResenaServlet extends HttpServlet {
    
    private ResenaServicio servicio;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servicio = new ResenaServicio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        String idResenaParam = request.getParameter("id");
        try {
            if (idResenaParam == null) {
                List<ResenaDTO> resenas = servicio.all();
                response.getWriter().write(gson.toJson(resenas));
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            
            int id = Integer.parseInt(idResenaParam);
            ResenaDTO resenaDTO = new ResenaDTO();
            resenaDTO.setId(id);
            ResenaDTO resultado = servicio.get(resenaDTO);
        
            if (resultado != null) {
                response.getWriter().write(gson.toJson(resultado));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.getWriter().write(gson.toJson(new Mensaje("Reseña no encontrada")));
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        BufferedReader reader = request.getReader();
        ResenaDTO resena = gson.fromJson(reader, ResenaDTO.class);
        ResenaDTO created = null;
        
        try {
            created = servicio.save(resena);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        if (created != null) {
            response.getWriter().write(gson.toJson(created));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("La reseña no fue creada")));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        BufferedReader reader = request.getReader();
        ResenaDTO resenaDTO = gson.fromJson(reader, ResenaDTO.class);
        ResenaDTO actualizada = null;
        
        try {
            actualizada = servicio.update(resenaDTO);
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            return;
        }
        
        if (actualizada != null) {
            response.getWriter().write(gson.toJson(actualizada));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("Reseña no encontrada")));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        int id = Integer.parseInt(request.getParameter("id"));
        ResenaDTO resenaDTO = new ResenaDTO();
        resenaDTO.setId(id);
        ResenaDTO eliminada = null;
        
        try {
            eliminada = servicio.delete(resenaDTO);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        if (eliminada != null) {
            response.getWriter().write(gson.toJson(eliminada));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("Reseña no encontrada")));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar reseñas";
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
