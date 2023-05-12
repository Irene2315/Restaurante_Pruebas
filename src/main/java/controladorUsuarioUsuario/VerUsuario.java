package controladorUsuarioUsuario;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Usuario;
import modeloUsuario.ModeloUsuario;

/**
 * Servlet implementation class VerUsuario
 */
@WebServlet("/VerUsuario")
public class VerUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
		
		if (usuarioLogueado == null) {// no logeado
			response.sendRedirect("PaginaReservaCliente");
		} else {
			int id = Integer.parseInt(request.getParameter("cUsuario"));
			
			
			
				Usuario usuario = new Usuario();
				
				ModeloUsuario usuarioM = new ModeloUsuario();
				
				usuarioM.conectar();
				
				try {
					usuario = usuarioM.getUsuario(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				usuarioM.cerrar();
			
			request.setAttribute("usuario", usuario);
			
			request.getRequestDispatcher("VistaUsurio.jsp").forward(request, response);
			
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
