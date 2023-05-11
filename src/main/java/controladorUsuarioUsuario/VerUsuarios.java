package controladorUsuarioUsuario;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Usuario;
import modeloUsuario.ModeloUsuario;

/**
 * Servlet implementation class VerUsuarios
 */
@WebServlet("/VerUsuarios")
public class VerUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerUsuarios() {
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
		// recibe el atributo del usuario que se ha logueado
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

		// si no se ha logueado lo redigira a la pagina pública
		if (usuarioLogueado == null) {
			response.sendRedirect("PaginaReservaCliente");
		} else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			ModeloUsuario modeloUsuario = new ModeloUsuario();
			modeloUsuario.conectar();

			// Conseguiremos todos los usuarios y los introduciremos en un array
			ArrayList<Usuario> usuarios = modeloUsuario.getUsuarios();

			modeloUsuario.cerrar();

			// eviaremos los datos de los usuarios y del usuario que se ha logueado
			request.setAttribute("usuarios", usuarios);
			session.setAttribute("usuarioLogueado", usuarioLogueado);
			request.getRequestDispatcher("VistaUsuarios.jsp").forward(request, response);
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
