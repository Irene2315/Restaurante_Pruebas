package controladorUsuarioLogin;

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
 * Servlet implementation class LoginEmpleado
 */
@WebServlet("/LoginEmpleado")
public class LoginEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginEmpleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean error = false;
		//Cargar la vista de login
		request.setAttribute("error", error);
		request.getRequestDispatcher("VistaLoginEmpleado.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recibe los parámetros
		int cUsuario = Integer.parseInt(request.getParameter("cUsuario"));
		String contrasena= request.getParameter("contrasena");
		
		ModeloUsuario usuarioM = new ModeloUsuario();
		usuarioM.conectar();
		//busca la contraseña que tiene el usuario con ese código de usuario
		String contrasenaBdd = usuarioM.getContrasena(cUsuario);
		
		if (contrasena.equals(contrasenaBdd)) {//login ok
			//conseguir el usuario de la BBDD
			
			Usuario usuarioLogueado;
		
				try {
					usuarioLogueado = usuarioM.getUsuario(cUsuario);
					//guardar el usuario en session
					HttpSession session = request.getSession();
					session.setAttribute("usuarioLogueado", usuarioLogueado);
					
					//dará paso al primer serlet de usuario
					
					request.getRequestDispatcher("Entrada.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			
		}
		else {
			Boolean error = true;
			request.setAttribute("error",error);
			request.getRequestDispatcher("VistaLoginEmpleado.jsp").forward(request, response);
		}
		
		usuarioM.cerrar();
	}

}
