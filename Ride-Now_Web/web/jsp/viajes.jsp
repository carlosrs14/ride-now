<%-- 
    Document   : viajes
    Created on : 24 mar 2025, 20:48:38
    Author     : crinc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../css/style_viajes.css">
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
      <input type="search" id="barra-origen" placeholder="🔍 Origen">
      <input type="search" id="barra-destino" placeholder="🔍 Destino">
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
        </tr>
      </thead>
      <tbody>
        <tr>
          <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
        </tr>
        <tr>
          <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
        </tr>
        <tr>
          <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
        </tr>
        <tr>
          <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
        </tr>
        <tr>
          <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
        </tr>
      </tbody>
    </table>
  </main>
</body>
</html>
