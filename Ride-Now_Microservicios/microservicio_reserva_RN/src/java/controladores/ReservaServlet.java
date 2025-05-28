/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import modelos.ReservaServicio;
import models.DTOs.ReservaDTO;

/**
 *
 * @author Rossimar
 */
@WebServlet(name = "ReservaServlet", urlPatterns = {"/ReservaServlet"})
public class ReservaServlet extends HttpServlet {
    private ReservaServicio servicio;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        servicio = new ReservaServicio();
    }
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        String idOwnerParam = request.getParameter("idowner");
        
        if (idOwnerParam != null) {
            try {
                int idowner = Integer.parseInt(idOwnerParam);
                List<ReservaDTO> reservas = servicio.filterByOwner(idowner);
                response.getWriter().write(gson.toJson(reservas));
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (SQLException | ClassNotFoundException ex) {
                response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            return;
        }
        
        String idReservaParam = request.getParameter("id");
        
        if (idReservaParam == null) {
            try {
                List<ReservaDTO> reservas = servicio.getAll();
                response.getWriter().write(gson.toJson(reservas));
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (SQLException | ClassNotFoundException ex) {
                response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            return;
        }
        int id = Integer.parseInt(idReservaParam);
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId(id);
        try {
            ReservaDTO resultado = servicio.get(reservaDTO);
            response.getWriter().write(gson.toJson(resultado));
            response.setStatus(HttpServletResponse.SC_OK);
            
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
        
        ReservaDTO reserva = gson.fromJson(reader, ReservaDTO.class);
        ReservaDTO created = null;
        
        try {
            created = servicio.save(reserva);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (created != null) {
            response.getWriter().write(gson.toJson(created));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("La reserva no fue creada")));
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
    ReservaDTO reserva = gson.fromJson(reader, ReservaDTO.class);

    ReservaDTO actualizada = null;

    try {
        actualizada = servicio.update(reserva);
    } catch (SQLException | ClassNotFoundException ex) {
        response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return;
    }

    if (actualizada != null) {
        response.getWriter().write(gson.toJson(actualizada));
        response.setStatus(HttpServletResponse.SC_OK);
    } else {
        response.getWriter().write(gson.toJson(new Mensaje("No se pudo actualizar la reserva")));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
 }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        int id = Integer.parseInt(request.getParameter("idReserva"));
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId(id);
        ReservaDTO eliminado = null;
        try {
            eliminado = servicio.delete(reservaDTO);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (eliminado != null) {
            response.getWriter().write(gson.toJson(eliminado));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("Reserva no encontrada")));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
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
