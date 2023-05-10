package controladorCliente;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Cliente;
import clases.Evento;
import modeloCliente.ModeloCliente;
import modeloEvento.ModeloEvento;

/**
 * Servlet implementation class CargarUsuario
 */
@WebServlet("/CargarUsuario")
public class CargarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloCliente clienteM = new ModeloCliente();
		Cliente cliente = new Cliente();
		String DNI = request.getParameter("DNI");
		
		clienteM.conectar();
		
		cliente = clienteM.getCliente(DNI);
		
		
		clienteM.cerrar();
		ModeloEvento eventoM = new ModeloEvento();
		eventoM.conectar();
		ArrayList<Evento> eventos = eventoM.getEventos();
		
		eventoM.cerrar();
		
		request.setAttribute("cliente", cliente);
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
