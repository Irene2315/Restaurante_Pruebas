package controladorUsuarioEvento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Evento;
import clases.Usuario;
import modeloEvento.ModeloEvento;


/**
 * Servlet implementation class InsertarEvento
 */
@WebServlet("/InsertarEvento")
public class InsertarEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarEvento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Evento/VistaEventos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

		if (usuarioLogueado == null) {// no logeado
			response.sendRedirect("PaginaReservaCliente");
		} else {
			
			if (usuarioLogueado.getRol().getId()==(1)) {
		Evento evento = new Evento();
		ModeloEvento modeloevento = new ModeloEvento();
		
		String nombre = request.getParameter("nombre");
		
		evento.setNombre(nombre);
		
		modeloevento.conectar();
		modeloevento.insertarEvento(evento);
		modeloevento.cerrar();
		response.sendRedirect("VerEventos");
			}
			else {
				response.sendRedirect("PaginaEvento");
			}
		
	}
		
	}

}