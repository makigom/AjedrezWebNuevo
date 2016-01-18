package capaServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import capaDeEntidades.Jugador;
import capaDeEntidades.Partida;
import capaDeNegocio.CtrlJugador;
import capaDeNegocio.CtrlPartida;
import appExceptions.ApplicationException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginWeb")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CtrlJugador ctrlJug = new CtrlJugador();
	private CtrlPartida ctrlPart = new CtrlPartida();

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String dniJugadorBlanco = request.getParameter("dniJugadorBlanco");
		String dniJugadorNegro = request.getParameter("dniJugadorNegro");
				
		Jugador jugadorBlanco = ctrlJug.getByDni(dniJugadorBlanco);
		Jugador jugadorNegro = ctrlJug.getByDni(dniJugadorNegro);
		if (jugadorBlanco==null) {
			//redirige a altaJugador.jsp con dniJugadorBlanco de parametro
			request.setAttribute("dniJugadorARegistrar", dniJugadorBlanco);
			request.setAttribute("mensaje", "El usuario con dni " + dniJugadorBlanco + " no existe.");
			//para redirigir desde servlet a pagina altaJugador
			request.getRequestDispatcher("altaJugador.jsp").forward(request, response);
		} else if (jugadorNegro==null) {
			//redirige a altaJugador.jsp con dniJugadorNegro de parametro
			request.setAttribute("dniJugadorARegistrar", dniJugadorNegro);
			request.setAttribute("mensaje", "El usuario con dni " + dniJugadorNegro + " no existe.");
			//para redirigir desde servlet a pagina altaJugador
			request.getRequestDispatcher("altaJugador.jsp").forward(request, response);
		} else {
			try {
				HttpSession session = request.getSession(true);
				session.setAttribute("jugadorBlanco", jugadorBlanco);
				session.setAttribute("jugadorNegro", jugadorNegro);
				Partida part = ctrlPart.recuperarPartida(dniJugadorBlanco, dniJugadorNegro);
				session.setAttribute("partidaActual", part);
				
				request.getRequestDispatcher("juegoActual.jsp").forward(request, response);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Creo e inicializo la variable de sesion.
		}
	}

}
