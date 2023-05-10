package controladorUsuarioUsuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.RolUsuario;
import clases.Usuario;
import modeloUsuario.ModeloRolUsuario;
import modeloUsuario.ModeloUsuario;

/**
 * Servlet implementation class InsertarUsuario
 */
@WebServlet("/InsertarUsuario")
public class InsertarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
		boolean error = false;

		if (usuarioLogueado == null) {// no logeado
			response.sendRedirect("PaginaReservaCliente");
		} else {
			/* si el usuario logeado es gerente podrá realizar
			registros sinó no*/
			if (usuarioLogueado.getRol().getId()==(1)) {
		
		ModeloRolUsuario rolM = new ModeloRolUsuario();
		rolM.conectar();
		/*conseguimos el arraylist de los roles*/
		ArrayList<RolUsuario> roles = rolM.getRolesUsuarios();
		rolM.cerrar();
		
		//enviamos los roles y cargamos la vista
		request.setAttribute("roles", roles);
		request.setAttribute("error", error);
		request.getRequestDispatcher("RegistrarUsuario.jsp").forward(request, response);
	}
			else {
				response.sendRedirect("PaginaUsuario");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		ModeloUsuario modeloUsuario = new ModeloUsuario();
		
		//recibimos los atributos del nuevo usuario
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String contrasena =request.getParameter("contrasena");
		String telefono = request.getParameter("telefono");
		String correoTrabajo = request.getParameter("correoTrabajo");
		
		int rol = Integer.parseInt(request.getParameter("rol"));
		
		
		boolean error = false;

		if (telefono.length() != 9 || !telefono.substring(0, 9).matches("\\d+")) {
			error = true;
		} else if (!correoTrabajo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			error = true;
		}

		
		if (error==false) {
		//contruimos el objeto de usuario
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setContrasena(contrasena);
		usuario.setTelefono(telefono);
		usuario.setCorreoTrabajo(correoTrabajo);
		
		RolUsuario rolUsuario = new RolUsuario();

		rolUsuario.setId(rol);
		usuario.setRol(rolUsuario);
		
		modeloUsuario.conectar();
		//se lo enviamos al modelo para que lo inserte en la BDD
		modeloUsuario.insertarUsuario(usuario);
		modeloUsuario.cerrar();
		
		response.sendRedirect("VerUsuarios");
		
		
	}
		else {
		ModeloRolUsuario rolM = new ModeloRolUsuario();
		rolM.conectar();
		/*conseguimos el arraylist de los roles*/
		ArrayList<RolUsuario> roles = rolM.getRolesUsuarios();
		rolM.cerrar();
		request.setAttribute("roles", roles);
		request.setAttribute("error", error);
		request.getRequestDispatcher("RegistrarUsuario.jsp").forward(request, response);
		}
	}

}
