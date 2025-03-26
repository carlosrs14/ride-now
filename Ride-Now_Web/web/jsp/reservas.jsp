<%-- 
    Document   : reservas
    Created on : 24 mar 2025, 23:06:15
    Author     : crinc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ride Now</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_viajes.css"/>
    </head>
    <body>
        <header>
            <nav>
                <a href="${pageContext.request.contextPath}/jsp/viajes.jsp">Ride Now</a>
                <a href="${pageContext.request.contextPath}/jsp/viajes.jsp">Viajes</a>
                <a href="#">Reservas</a>
                <a href="#">Reseñas</a>
                <a href="#">Denuncias</a>
                <div id="iniciar-sesion">
                    <a href=""><i class="bi bi-person-circle"></i></a>
                </div>
            </nav>
        </header>
        <main>
            <div id="titulo">
                <h1>Estas son tus reservas:</h1>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Origen</th>
                        <th>Destino</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Prestador de servicio</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>santa marta</td>
                        <td>barranquilla</td>
                        <td>11/03</td>
                        <td>7:30pm</td>
                        <td>Hector Mesa</td>
                        <td><i class="bi bi-trash" style="cursor: pointer;"></i></td>
                    </tr>
                    <tr>
                        <td>santa marta</td>
                        <td>bogotá</td>
                        <td>12/03</td>
                        <td>7:30pm</td>
                        <td>Fernando Rojas</td>
                        <td><i class="bi bi-trash" style="cursor: pointer;"></i></td>
                    </tr>
                    <tr>
                        <td>santa marta</td>
                        <td>barranquilla</td>
                        <td>13/03</td>
                        <td>7:30pm</td>
                        <td>Chespirito Gomez</td>
                        <td><i class="bi bi-trash" style="cursor: pointer;"></i></td>
                    </tr>
                    <tr>
                        <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                    </tr>
                </tbody>
            </table>
        </main>
    </body>
</html>
