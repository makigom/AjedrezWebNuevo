package capaServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import capaDeEntidades.Jugador;
import capaDeNegocio.CtrlJugador;

/**
 * Servlet implementation class AltaJugadorServlet
 */
@WebServlet("/altaJugadorWeb")
public class AltaJugadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private CtrlJugador ctrlJug = new CtrlJugador();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaJugadorServlet() {
        super();
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
		Jugador jugadorNuevo = new Jugador();
		String dniJugador = request.getParameter("dniJugador").toString();
		String nombreJugador = request.getParameter("nombreJugador").toString();
		String apellidoJugador = request.getParameter("apellidoJugador").toString();
		if (ctrlJug.getByDni(dniJugador) == null) {
			jugadorNuevo.setDni(dniJugador);
			jugadorNuevo.setNombre(nombreJugador);
			jugadorNuevo.setApellido(apellidoJugador);
			try {
				ctrlJug.save(jugadorNuevo);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("login.html");
		} else {
			request.setAttribute("mensaje", "El dni ingresado ya existe");
			request.setAttribute("dniJugadorARegistrar", dniJugador);
			request.setAttribute("nombreJugadorARegistrar", nombreJugador);
			request.setAttribute("apellidoJugadorARegistrar", apellidoJugador);
			request.getRequestDispatcher("/altaJugador.jsp").forward(request, response);
		}
	}
}
