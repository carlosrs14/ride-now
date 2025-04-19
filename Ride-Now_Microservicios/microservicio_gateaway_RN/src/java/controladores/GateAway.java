/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import javax.swing.JOptionPane;

/**
 *
 * @author xlancet
 */
@WebServlet(name = "GateAway", urlPatterns = {"/GateAway"})
public class GateAway extends HttpServlet {
    
    
    private String resolveServiceUrl(String servlet) {
        if (servlet.equals("usuario")) {
            return "http://localhost:8081";
        //} else if (path.startsWith("/ViajeServlet")) {
        //    return "http://localhost:8082";
        //} else if (path.startsWith("/ResenasServlet")) {
        //    return "http://localhost:8083";
        }
        return "http://localhost:8081"; // servicio no encontrado
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
        String servlet = request.getParameter("servlet");
        String serviceUrl = resolveServiceUrl(servlet);
        if (serviceUrl == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Servicio no encontrado");
            return;
        }
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        URL url = new URL("http://localhost:8081/UsuarioServlet?correo=" + correo + "&password=" + password);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        int status = conn.getResponseCode();
        InputStream inputStream = (status >= 200 && status < 300) ? conn.getInputStream() : conn.getErrorStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        // Parsear la respuesta JSON
        String json = content.toString();
        JsonObject jsonResponse = JsonParser.parseString(json).getAsJsonObject();
        String mensaje = jsonResponse.get("mensaje").getAsString();

        // Enviar el mensaje a la vista
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("index.jsp").forward(request, response);

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
