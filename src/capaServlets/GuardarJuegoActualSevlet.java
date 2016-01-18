package capaServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appExceptions.ApplicationException;
import capaDeEntidades.Partida;
import capaDeNegocio.CtrlPartida;

/**
 * Servlet implementation class GuardarJuegoActualSevlet
 */
@WebServlet("/guardarJuegoActualWeb")
public class GuardarJuegoActualSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CtrlPartida ctrlPart = new CtrlPartida();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarJuegoActualSevlet() {
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
		HttpSession session = request.getSession(true);
		Partida partidaActual = (Partida)session.getAttribute("partidaActual");
		try {
			if(ctrlPart.guardarPartida(partidaActual)){
				request.setAttribute("mensaje", "Partida guardada con exito");
				request.getRequestDispatcher("/juegoActual.jsp").forward(request, response);
			} else {
				request.setAttribute("mensaje", "No se ha podido guardar la partida");
				request.getRequestDispatcher("/juegoActual.jsp").forward(request, response);
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
