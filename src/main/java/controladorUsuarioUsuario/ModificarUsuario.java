package controladorUsuarioUsuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class ModificarUsuario
 */
@WebServlet("/ModificarUsuario")
public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarUsuario() {
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
			//solo el gerente puede acceder a esta funcionalidad
			if (usuarioLogueado.getRol().getId()==(1)) {
		int id = Integer.parseInt(request.getParameter("cUsuario"));
		
		ModeloUsuario usuarioM = new ModeloUsuario();
		Usuario usuario = new Usuario();
		try {
			usuarioM.conectar();
			//se consigue los datos del viejo usuario por su id
			usuario = usuarioM.getUsuario(id);
			usuarioM.cerrar();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//conseguimos los roles del usuario
				ModeloRolUsuario rolM = new ModeloRolUsuario();
				rolM.conectar();
				ArrayList<RolUsuario> roles = rolM.getRolesUsuarios();
				rolM.cerrar();
				//enviamos los roles y cargamos la vista
		request.setAttribute("usuario", usuario);
		request.setAttribute("roles", roles);	
		request.setAttribute("error", error);
		request.getRequestDispatcher("ModificarUsuario.jsp").forward(request, response);
	}
			else {
				response.sendRedirect("PaginaUsuario");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario usuario = new Usuario();
		ModeloUsuario modeloUsuario = new ModeloUsuario();
		
		//recibimos los atributos del nuevo usuario
		int cUsuario = Integer.parseInt(request.getParameter("cUsuario"));
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
		
		//contruimos el usuario
		usuario.setcUsuario(cUsuario);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setContrasena(contrasena);
		usuario.setTelefono(telefono);
		usuario.setCorreoTrabajo(correoTrabajo);
		
		RolUsuario rolUsuario = new RolUsuario();

		rolUsuario.setId(rol);
		usuario.setRol(rolUsuario);
		
		modeloUsuario.conectar();
		//recibimos los datos del nuevo usuario y lo modificamos
		modeloUsuario.modificarUsuario(usuario);
		modeloUsuario.cerrar();
		
		response.sendRedirect("VerUsuarios");
		}
		else {
			int id = Integer.parseInt(request.getParameter("cUsuario"));
			ModeloUsuario usuarioM = new ModeloUsuario();
			Usuario usuario_2 = new Usuario();
			usuarioM.conectar();
			//se consigue los datos del viejo usuario por su id
			try {
				usuario_2 = usuarioM.getUsuario(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			usuarioM.cerrar();
			//conseguimos los roles del usuario
			ModeloRolUsuario rolM = new ModeloRolUsuario();
			rolM.conectar();
			ArrayList<RolUsuario> roles = rolM.getRolesUsuarios();
			rolM.cerrar();
			//enviamos los roles y cargamos la vista
			request.setAttribute("usuario", usuario_2);
			request.setAttribute("roles", roles);
			request.setAttribute("error", error);
			request.getRequestDispatcher("ModificarUsuario.jsp").forward(request, response);
		}
		
		
	}

}
