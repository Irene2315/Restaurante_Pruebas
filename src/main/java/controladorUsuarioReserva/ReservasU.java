package controladorUsuarioReserva;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Evento;
import clases.Reserva;
import clases.Usuario;
import modeloEvento.ModeloEvento;
import modeloUsuario.ModeloUsuario;


/**
 * Servlet implementation class ReservasU
 */
@WebServlet("/ReservasU")
public class ReservasU extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservasU() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
		
		if(usuarioLogueado == null) {
			response.sendRedirect("PaginaReservaCliente");
		}else {
		ModeloUsuario usuarioM = new ModeloUsuario();
		
		ArrayList <Reserva> reservas = new ArrayList<Reserva>();
		
		
		usuarioM.conectar();
		//consigue las reservas que ha realizado ese cliente
		reservas = usuarioM.getReservas();
		
		usuarioM.cerrar();
		
		ModeloEvento eventoM = new ModeloEvento();
		eventoM.conectar();
		ArrayList<Evento> eventos = eventoM.getEventos();
		
		eventoM.cerrar();
		
		
		request.setAttribute("reservas", reservas);
		request.setAttribute("eventos", eventos);
		
		
		request.getRequestDispatcher("VistaReservaUsuario.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
