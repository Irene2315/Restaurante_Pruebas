package controladorCliente;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Evento;
import clases.Reserva;
import modeloCliente.ModeloCliente;
import modeloEvento.ModeloEvento;

/**
 * Servlet implementation class VerReservas
 */
@WebServlet("/VerReservas")
public class VerReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerReservas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		boolean error = false;
		
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
		
		request.setAttribute("error", error);
		request.setAttribute("reservas", reservas);
		request.setAttribute("eventos", eventos);
		
		request.getRequestDispatcher("VistaReservaCliente.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}