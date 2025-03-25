<%-- 
    Document   : login
    Created on : 24 mar 2025, 21:09:20
    Author     : crinc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="../css/style_login.css">
        <title>Login - Ride Now</title>
    </head>
    <body>
  <div class="container">
    <form class="login-form">
      <div class="header">
        <i class="fas fa-sign-in-alt icon"></i>
        <h1>Iniciar Sesión</h1>
      </div>
      <div class="input-group">
        <input type="email" placeholder="Email">
      </div>
      <div class="input-group">
        <input type="password" placeholder="Contraseña">
      </div>
      <button type="submit" class="btn-ingresar">Ingresar</button>
    </form>
  </div>
</body>
</html>
