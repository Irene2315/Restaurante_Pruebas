package controladorCliente;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Cliente;
import clases.Evento;
import clases.Reserva;
import modeloCliente.ModeloCliente;
import modeloEvento.ModeloEvento;

/**
 * Servlet implementation class AñadirReserva
 */
@WebServlet("/InsertarReserva")
public class InsertarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertarReserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Reserva reserva = new Reserva();
		ModeloCliente clienteM = new ModeloCliente();

		String DNI = request.getParameter("DNI2");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String telefono = request.getParameter("telefono");
		String correo = request.getParameter("correo");
		SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
		int idEvento = Integer.parseInt(request.getParameter("evento"));
		Date fecha = new Date();

		try {
			fecha = formatFecha.parse(request.getParameter("fecha"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean error = false;

		if (DNI.length() != 9 || !DNI.substring(0, 8).matches("\\d+")) {

			error = true;
		} else if (telefono.length() != 9 || !telefono.substring(0, 9).matches("\\d+")) {
			error = true;
		} else if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			error = true;
		}

		else if (fecha.before(new Date())) {
			error = true;
		}

		if (error == false) {
			Cliente cliente = new Cliente();
			
			cliente.setDni(DNI);
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setTelefono(telefono);
			cliente.setCorreo(correo);

			// comprobar que el cliente existe

			clienteM.conectar();

			// hace referencia ha si un usuario esta en la base de datos

			Boolean encontado = clienteM.DNIExiste(DNI);
			if (encontado == false) {
				clienteM.registrarCliente(cliente);

			}

			Evento evento = new Evento();
			evento.setcEvento(idEvento);

			if (error == false) {
				reserva.setFecha(fecha);
				reserva.setCliente(cliente);
				reserva.setEvento(evento);
				clienteM.crearReserva(reserva);
			}

			clienteM.cerrar();

		}
		ModeloEvento eventoM = new ModeloEvento();
		eventoM.conectar();
		ArrayList<Evento> eventos = eventoM.getEventos();

		eventoM.cerrar();

		request.setAttribute("error", error);
		request.setAttribute("eventos", eventos);
		request.getRequestDispatcher("VistaReservaCliente.jsp").forward(request, response);

	}

}
