
package controladores;

import models.DenunciaServicio;
import models.DTOs.DenunciaDTO;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
@WebServlet(name = "DenunciaServlet", urlPatterns = {"/DenunciaServlet"})
public class DenunciaServlet extends HttpServlet {
    
    private DenunciaServicio servicio;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servicio = new DenunciaServicio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        
        String idOwnerParam = request.getParameter("idowner");
        if(idOwnerParam != null) {
            int idowner = Integer.parseInt(idOwnerParam);
            try {
                List<DenunciaDTO> denuncias = servicio.getDenunciasByCliente(idowner);
                response.getWriter().write(gson.toJson(denuncias));
                response.setStatus(HttpServletResponse.SC_OK);
                
            } catch (SQLException | ClassNotFoundException ex) {
                response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        
        String idDenunciaParam = request.getParameter("id");
        try {
            if (idDenunciaParam == null) {
                List<DenunciaDTO> denuncias = servicio.all();
                response.getWriter().write(gson.toJson(denuncias));
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            
            int id = Integer.parseInt(idDenunciaParam);
            DenunciaDTO denunciaDTO = new DenunciaDTO();
            denunciaDTO.setId(id);
            DenunciaDTO resultado = servicio.get(denunciaDTO);
        
            if (resultado != null) {
                response.getWriter().write(gson.toJson(resultado));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.getWriter().write(gson.toJson(new Mensaje("Denuncia no encontrada")));
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
        DenunciaDTO denuncia = gson.fromJson(reader, DenunciaDTO.class);
        DenunciaDTO created = null;
        
        try {
            created = servicio.save(denuncia);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        if (created != null) {
            response.getWriter().write(gson.toJson(created));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("La denuncia no fue creada")));
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
        DenunciaDTO denunciaDTO = gson.fromJson(reader, DenunciaDTO.class);
        DenunciaDTO actualizada = null;
        
        try {
            actualizada = servicio.update(denunciaDTO);
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            return;
        }
        
        if (actualizada != null) {
            response.getWriter().write(gson.toJson(actualizada));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("Denuncia no encontrada")));
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
        DenunciaDTO denunciaDTO = new DenunciaDTO();
        denunciaDTO.setId(id);
        DenunciaDTO eliminada = null;
        
        try {
            eliminada = servicio.delete(denunciaDTO);
        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().write(gson.toJson(new Mensaje(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        if (eliminada != null) {
            response.getWriter().write(gson.toJson(eliminada));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().write(gson.toJson(new Mensaje("Denuncia no encontrada")));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar denuncias";
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