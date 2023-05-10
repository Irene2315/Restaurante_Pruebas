package controladorUsuarioEvento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Usuario;
import modeloEvento.ModeloEvento;


/**
 * Servlet implementation class EliminarEvento
 */
@WebServlet("/EliminarEvento")
public class EliminarEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarEvento() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

		if (usuarioLogueado == null) {// no logeado
			response.sendRedirect("PaginaReservaCliente");
		} else {
			
			if (usuarioLogueado.getRol().getId()==(1)) {
		int cEvento = Integer.parseInt(request.getParameter("c_evento"));
		
		ModeloEvento modeloevento = new ModeloEvento();
		modeloevento.conectar();
		modeloevento.eliminarEvento(cEvento);
		modeloevento.cerrar();
		
		response.sendRedirect("VerEventos");
	}
			else {
				response.sendRedirect("PaginaEvento");
			}
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