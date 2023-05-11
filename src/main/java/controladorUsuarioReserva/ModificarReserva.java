package controladorUsuarioReserva;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		boolean error = false;
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
				request.setAttribute("error", error);
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

		SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = new Date();
		boolean error = false;

		try {
			fecha = formatFecha.parse(request.getParameter("fecha"));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (telefono.length() != 9 || !telefono.substring(0, 9).matches("\\d+")) {
			error = true;
		}

		else if (fecha.before(new Date())) {
			error = true;
		}

		if (error == false) {
			ModeloUsuario modeloUsuario = new ModeloUsuario();
			Reserva reserva = new Reserva();
			reserva.setnReserva(nReserva);

			reserva.setFecha(fecha);
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
	
		else {
			ModeloUsuario modeloUsuario = new ModeloUsuario();
			Reserva reserva = new Reserva();
			int nReserva2 = Integer.parseInt(request.getParameter("nReserva"));
			try {
				modeloUsuario.conectar();
				reserva = modeloUsuario.getReserva(nReserva2);
				modeloUsuario.cerrar();
			} catch (Exception e) {
				// TODO: handle exception
			}
			ModeloEvento eventoM = new ModeloEvento();
			eventoM.conectar();
			ArrayList<Evento> eventos = eventoM.getEventos();

			eventoM.cerrar();

			request.setAttribute("eventos", eventos);
			request.setAttribute("error", error);
			request.setAttribute("reserva", reserva);
			request.getRequestDispatcher("ModificarReserva.jsp").forward(request, response);
		}

	}
}
