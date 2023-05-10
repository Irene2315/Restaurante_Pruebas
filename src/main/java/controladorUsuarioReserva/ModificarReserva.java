package controladorUsuarioReserva;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Cliente;
import clases.Evento;
import clases.Reserva;
import clases.Usuario;
import modeloEvento.ModeloEvento;
import modeloUsuario.ModeloUsuario;

/**
 * Servlet implementation class ModificarReserva
 */
@WebServlet("/ModificarReserva")
public class ModificarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarReserva() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

		if (usuarioLogueado == null) {// no logeado
			response.sendRedirect("PaginaReservaCliente");
		} else {

			if (usuarioLogueado.getRol().getId() == (1)) {
				ModeloUsuario modeloUsuario = new ModeloUsuario();
				Reserva reserva = new Reserva();
				int nReserva = Integer.parseInt(request.getParameter("nReserva"));
				try {
					modeloUsuario.conectar();
					reserva = modeloUsuario.getReserva(nReserva);
					modeloUsuario.cerrar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				ModeloEvento eventoM = new ModeloEvento();
				eventoM.conectar();
				ArrayList<Evento> eventos = eventoM.getEventos();

				eventoM.cerrar();

				request.setAttribute("eventos", eventos);

				request.setAttribute("reserva", reserva);
				request.getRequestDispatcher("ModificarReserva.jsp").forward(request, response);
			}

			else {
				response.sendRedirect("PaginaReservaUsuario");
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int nReserva = Integer.parseInt(request.getParameter("nReserva"));
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		int idEvento = Integer.parseInt(request.getParameter("evento"));

		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		ModeloUsuario modeloUsuario = new ModeloUsuario();
		Reserva reserva = new Reserva();
		reserva.setnReserva(nReserva);
		try {
			reserva.setFecha(fecha.parse(request.getParameter("fecha")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Cliente cliente = new Cliente();

		cliente.setNombre(nombre);
		cliente.setTelefono(telefono);
		reserva.setCliente(cliente);

		Evento evento = new Evento();
		evento.setcEvento(idEvento);
		reserva.setEvento(evento);

		modeloUsuario.conectar();
		modeloUsuario.modificarReserva(reserva);
		modeloUsuario.cerrar();
		request.getRequestDispatcher("ReservasU").forward(request, response);
	}

}
