<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta jugador</title>
<link href="styles/styles.css" rel="stylesheet"/>
</head>
<body>
<%
	String dni;
	String nombre;
	String apellido;
	if(request.getAttribute("dniJugadorARegistrar") != null) dni = String.valueOf(request.getAttribute("dniJugadorARegistrar"));
	else dni = "";
	if(request.getAttribute("nombreJugadorARegistrar") != null) nombre = String.valueOf(request.getAttribute("nombreJugadorARegistrar"));
	else nombre = "";
	if(request.getAttribute("apellidoJugadorARegistrar") != null) apellido = String.valueOf(request.getAttribute("apellidoJugadorARegistrar"));
	else apellido = "";
%>

<div class="links">
	 <a href="login.html"><img src="images/peon_negro.svg" alt="Peon negro"/>Home</a>
</div>

<div align="center" class="main">
	<h1>¡Hola!</h1>
	<h2>Ingresa los datos del nuevo jugador</h2>
	<form action="altaJugadorWeb" method="POST" onsubmit="registrar" class="container">
		<div style="color: #FF0000;">${mensaje}</div>
		<input type="text" value="<%= dni %>" name="dniJugador" id="dniJugador" placeholder="DNI Jugador" required="required">
		<input type="text" value="<%= nombre %>" name="nombreJugador" id="nombreJugador" placeholder="Nombre Jugador" required="required">
		<input type="text" value="<%= apellido %>" name="apellidoJugador" id="apellidoJugador" placeholder="Apellido Jugador" required="required">
		<button type="submit" class="btn btn-primary btn-block btn-large" id="registrar" name="registrar">REGISTRAR</button>
	</form>
</div>
</body>
</html>