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
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarProducto() {
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
			
			if (usuarioLogueado.getRol().getId()==(1)) {
		// TODO Auto-generated method stub
		int cProducto = Integer.parseInt(request.getParameter("cProducto"));
		
		ModeloUsuarioPr usuarioM = new ModeloUsuarioPr();
		usuarioM.conectar();
		
		Producto producto = usuarioM.getProducto(cProducto);
		
		usuarioM.cerrar();
		
		request.setAttribute("producto", producto);
		
		request.getRequestDispatcher("Producto/VistaModificarProducto.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("PaginaProductos");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cProducto = Integer.parseInt(request.getParameter("cProducto"));
		String nombre = request.getParameter("nombre");
		double calorias = Double.parseDouble(request.getParameter("calorias"));
		double proteinas = Double.parseDouble(request.getParameter("proteinas"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		
		Producto producto = new Producto();
		producto.setcProducto(cProducto);
		producto.setNombre(nombre);
		producto.setCalorias(calorias);
		producto.setProteinas(proteinas);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		
		
		ModeloUsuarioPr usuarioM = new ModeloUsuarioPr();
		usuarioM.conectar();
		
		usuarioM.modificarProducto(producto);
		
		usuarioM.cerrar();
		
		response.sendRedirect("VerProductos");
		
		
		
		
		
		
	
	}

}
