<%-- 
    Document   : viajes
    Created on : 24 mar 2025, 20:48:38
    Author     : crinc
--%>

<%@page import="com.ridenow.modelos.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ridenow.modelos.Viaje"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_viajes.css">
        <title>Ride Now</title>
    </head>
    <body>
        <header>
            <nav>
                <a href="#">Ride Now</a>
                <a href="#">Viajes</a>
                <a href="./reservas.jsp">Reservas</a>
                <a href="">Reseñas</a>
                <a href="">Denuncias</a>
                <div id="iniciar-sesion">
                    <a href=""><i class="bi bi-person-circle"></i></a>
                </div>
            </nav>
        </header>
        <main>
            <div id="titulo">
                <h1>Buscar un viaje</h1>
            </div>
            <div class="barras-buscar">
                <form action="http://localhost:8080/Ride-Now_Web/ViajeControl" method="GET" >
                    <input type="search" name="origen" id="barra-origen" placeholder="🔍 Origen">
                    <input type="search" name="destino" id="barra-destino" placeholder="🔍 Destino">
                    <input type="hidden" name="metodo" value="filtrarViajes">
                    <button type="submit" class="btn-buscar">Buscar</button>
                </form>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Origen</th>
                        <th>Destino</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Prestador de servicio</th>
                        <th>Precio</th>
                        <th>Rating</th>
                        <th>Reservar</th>
                    </tr>
                </thead>
                <tbody>
                <% 
                String mensaje = (String) request.getAttribute("mensaje");
                if (mensaje != null) {
                %>
        
                    <script>
                    alert("<%= mensaje %>");
                    </script>
        
                <% }
                List<Viaje> viajes = (ArrayList) request.getAttribute("viajes");
                Cliente cliente = (Cliente) session.getAttribute("cliente");
                if (viajes != null) {
                    for (Viaje viaje : viajes) {
                %>
                    <tr>
                        <td><%= viaje.getOrigen().getNombre() %></td>
                        <td><%= viaje.getDestino().getNombre() %></td>
                        <td><%= viaje.getFecha() %></td>
                        <td><%= viaje.getHora() %></td>
                        <td>prestador</td>
                        <td><%= viaje.getPrecio() %></td>
                        <td>estrellas</td>
                        <form action="http://localhost:8080/Ride-Now_Web/ReservaControl" method="POST">
                            <input type="hidden" name="idViaje" value=<%= viaje.getId() %>>
                            <input type="hidden" name="idCliente" value=<%= cliente.getId() %>>
                            <input type="hidden" name="monto" value=<%= viaje.getPrecio() %>>
                            <input type="hidden" name="metodo" value="reservar">
                        <td><button type="submit" class="btn-buscar">+</button></td>
                        </form>
                    </tr>
        
                <%
                    }
                } 
                %>
                </tbody>
            </table>
        </main>
    </body>
</html>
