package controladorUsuarioPr;

import java.io.IOException;
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
 * Servlet implementation class VerProducto
 */
@WebServlet("/VerProducto")
public class VerProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerProducto() {
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
			
			
		int cProducto = Integer.parseInt(request.getParameter("cProducto"));
			
			ModeloUsuarioPr usuarioM = new ModeloUsuarioPr();
			
			usuarioM.conectar();
			//consigue los detalles del producto
			Producto nombreP = usuarioM.getProducto(cProducto);
 			
			usuarioM.cerrar();
			
			request.setAttribute("producto", nombreP);
			
			request.getRequestDispatcher( "VerProducto.jsp").forward(request, response);
		
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
