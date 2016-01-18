<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="capaDeEntidades.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Juego Actual</title>
<link href="styles/styles.css" rel="stylesheet"/>
</head>
<body>
<%
	Partida partidaActual = (Partida)session.getAttribute("partidaActual");
	String nombreJugadorProximo;
	Jugador jug;
	if (partidaActual.getTurno()) {
		jug = (Jugador)session.getAttribute("jugadorBlanco");
		nombreJugadorProximo = jug.getNombre() + " " + jug.getApellido() + " (jugador blanco)";
	}
	else {
		jug = (Jugador)session.getAttribute("jugadorNegro");
		nombreJugadorProximo = jug.getNombre() + " " + jug.getApellido() + " (jugador negro)";
	}
%>

<div class="links">
	 <a href="login.html"><img src="images/peon_negro.svg" alt="Peon negro"/>Home</a> <!-- Podria usar un script que pregunte si esta seguro de salir, y de asi serlo, reiniciar la variable de sesion  -->
</div>

<div class="tablaPropia">
<h2>Tabla propia</h2>
	<table>
		<tr>
			<th>Tipo ficha</th>
			<th>Letra</th>
			<th>Numero</th>
			<th>Color</th>
		</tr>
		<%
			String color;
			if (partidaActual.getTurno()) color = "B";
			else color = "N";
			for (Ficha f: partidaActual.getFichas()) {
				if (f.getEstado() && f.getColor().equals(color)) {
		%>
		<tr>
			<td><%= f.getTipoFicha() %></td>
			<td><%= f.getPosicion().getLetra() %></td>
			<td><%= f.getPosicion().getNumero() %></td>
			<td><%= f.getColor() %>
		</tr>
		<%
				}
			}
		%>
	</table>
</div>

<div class="tablaOponente">
<h2>Tabla del oponente</h2>
	<table>
		<tr>
			<th>Tipo ficha</th>
			<th>Letra</th>
			<th>Numero</th>
			<th>Color</th>
		</tr>
		<%
			for (Ficha f: partidaActual.getFichas()) {
				if (f.getEstado() && !f.getColor().equals(color)) {
		%>
		<tr>
			<td><%= f.getTipoFicha() %></td>
			<td><%= f.getPosicion().getLetra() %></td>
			<td><%= f.getPosicion().getNumero() %></td>
			<td><%= f.getColor() %>
		</tr>
		<%
				}
			}
		%>
	</table>
</div>

<div align="center" class="main" >
<h1>Es el turno de: <%= nombreJugadorProximo %></h1>

<form action="moverJuegoActualWeb" method="POST" onsubmit="mover" class="container">
		<div style="color: #FF0000;">${mensaje}</div>
		<input type="text" value="" name="letraOrigen" id="letraOrigen" placeholder="Letra Origen" required="required">
		<input type="text" value="" name="numeroOrigen" id="numeroOrigen" placeholder="Numero Origen" required="required">
		<input type="text" value="" name="letraDestino" id="letraDestino" placeholder="Letra Destino" required="required">
		<input type="text" value="" name="numeroDestino" id="numeroDestino" placeholder="Numero Destino" required="required">
		<button type="submit" class="btn btn-primary btn-block btn-large" id="mover" name="mover">MOVER</button>
</form>

<form action="guardarJuegoActualWeb" method="POST" onsubmit="guardar" class="container">
		<button type="submit" class="btn btn-primary btn-block btn-large" id="guardar" name="guardar">GUARDAR</button>
</form>

</div>

</body>
</html>