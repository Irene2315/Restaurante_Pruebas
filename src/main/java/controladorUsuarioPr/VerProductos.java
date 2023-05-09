package controladorUsuarioPr;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Producto;
import clases.Usuario;
import modeloUsuario.ModeloUsuarioPr;

/**
 * Servlet implementation class VerProductos
 */
@WebServlet("/VerProductos")
public class VerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerProductos() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
		
		if(usuarioLogueado == null) {
			response.sendRedirect("PaginaReservaCliente");
		}else {
		ModeloUsuarioPr usuarioM = new ModeloUsuarioPr();
		
		ArrayList <Producto> productos = new ArrayList <>();
		
		usuarioM.conectar();
		
		productos = usuarioM.getProductos();
		
		usuarioM.cerrar();
		
		request.setAttribute("productos", productos);
		
		request.getRequestDispatcher("VistaProductos.jsp").forward(request, response);
		
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
