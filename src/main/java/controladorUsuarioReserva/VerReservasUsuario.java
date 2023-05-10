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
import modeloCliente.ModeloCliente;
import modeloEvento.ModeloEvento;

/**
 * Servlet implementation class VerReservasUsuario
 */
@WebServlet("/VerReservasUsuario")
public class VerReservasUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerReservasUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
		
		if(usuarioLogueado == null) {
			response.sendRedirect("PaginaReservaCliente");
		}else {
		ModeloCliente clienteM = new ModeloCliente();
		
		ArrayList <Reserva> reservas = new ArrayList<Reserva>();
		
		String DNI = request.getParameter("DNI");
		
		clienteM.conectar();
		reservas = clienteM.getReservasUsuario(DNI);
		
		clienteM.cerrar();
		
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