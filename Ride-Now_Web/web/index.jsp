<%-- 
    Document   : index
    Created on : 24 mar 2025, 19:25:14
    Author     : crinc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/style_index.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
        <title>Registrarse - Ride Now</title>
    </head>
    <body>
        <header>
    <h1>Regístrate en nuestra App RideNow</h1>
  </header>
 
        <form action="Registro" method="POST" class="formulario">
    <div class="formulario-items">
        <input type="hidden" value="registrar" name="metodo">
      <label>Datos Personales</label>
      <div class="recuadro">
          <input type="text" name="nombre" placeholder="Nombre" required="true">
          <input type="text" name="apellido" placeholder="Apellido" required="true">
          <input type="email" name="correo" placeholder="Email" required="true">
      </div>
 
      <label>Información de usuario</label>
      <div class="recuadro">
          <input type="text" name="telefono" placeholder="Número de teléfono" required="true">
          <label hidden="true" for="fecha">Fecha:</label>
          <input type="date" id="fecha" name="fecha" placeholder="Fecha de nacimiento" required="true">
        <label>Tipo de usuario</label>
        <select>
            <option value="cliente">Cliente</option>
            <option value="prestador" >Prestador de Servicio</option>
        </select>
        <input type="password" name="password" placeholder="Contraseña" required="true" autocomplete="new-password">
        <input type="password" name="passwordConfirm" placeholder="Contraseña" required="true" autocomplete="new-password">

      </div>
 
      <div class="botones">
          <button type="submit" class="btn-registrarse">Registrarse</button>
          <button type="button" class="btn-cancelar" onclick="window.location.href='index.jsp'">Cancelar</button>
      </div>
 
      <p>¿Ya tienes una cuenta?</p>
      <a href="./jsp/login.jsp" class="iniciar-sesion">Iniciar Sesión</a>
    </div>
  </form>
    </body>
</html>
