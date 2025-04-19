<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Ride Now</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <% 
        String mensaje = (String) request.getAttribute("mensaje"); 
        if (mensaje != null) {%>
        
        <p><%= mensaje %></p>
        
    <%} %>
    <body>
        <form id="loginForm" method="GET" action="GateAway">
            <input type="hidden" name="servlet" value="user" />
            <input type="text" name="correo" placeholder="Correo" required />
            <input type="password" name="password" placeholder="Contraseña" required />
            <button type="submit">Iniciar sesión</button>
        </form>
    </body>
</html>
