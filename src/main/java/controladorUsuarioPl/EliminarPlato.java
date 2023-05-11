package controladorUsuarioPl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Usuario;
import modeloUsuario.ModeloUsuarioPl;

/**
 * Servlet implementation class EliminarPlato
 */
@WebServlet("/EliminarPlato")
public class EliminarPlato extends HttpServlet {
	public static final int ROL_GERENTE = 1;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarPlato() {
		super();
		// TODO Auto-generated constructor stub
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

			if (usuarioLogueado.getRol().getId() == (ROL_GERENTE)) { // rol gerente
				// TODO Auto-generated method stub
				int cPlato = Integer.parseInt(request.getParameter("cPlato"));
				ModeloUsuarioPl usuarioM = new ModeloUsuarioPl();

				usuarioM.conectar();
				usuarioM.EliminarProductosPlato(cPlato);
				usuarioM.EliminarPlato(cPlato);
				usuarioM.cerrar();

				response.sendRedirect("VerPlatos");

			} else {//EL USUARIO NO TIENE PERMISO
				response.sendRedirect("PaginaPlato");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
