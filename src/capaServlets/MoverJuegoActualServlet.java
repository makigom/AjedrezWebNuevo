package capaServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import capaDeEntidades.*;
import capaDeNegocio.CtrlPartida;

/**
 * Servlet implementation class MoverJuegoActualServlet
 */
@WebServlet("/moverJuegoActualWeb")
public class MoverJuegoActualServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CtrlPartida ctrlPart = new CtrlPartida();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoverJuegoActualServlet() {
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
		Posicion posOrigen = new Posicion();
		Posicion posDestino = new Posicion();
		HttpSession session = request.getSession(true);
		Partida partidaActual = (Partida)session.getAttribute("partidaActual");
		posOrigen.setLetra(request.getParameter("letraOrigen").charAt(0));
		posOrigen.setNumero(Integer.parseInt((request.getParameter("numeroOrigen"))));
		posDestino.setLetra(request.getParameter("letraDestino").charAt(0));
		posDestino.setNumero(Integer.parseInt(request.getParameter("numeroDestino")));
		if(ctrlPart.validarJugada(posOrigen, posDestino, partidaActual)){
			if(partidaActual.getTurno()){
				partidaActual.setTurno(false);				
			} else {
				partidaActual.setTurno(true);
			}
			session.setAttribute("partidaActual", partidaActual);
			response.sendRedirect("juegoActual.jsp");
		}
		else {
			request.setAttribute("mensaje", "El movimiento no es valido");
			request.getRequestDispatcher("/juegoActual.jsp").forward(request, response);
		}
		
	}

}
