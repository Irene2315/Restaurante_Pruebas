package controladorUsuarioPl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Plato;
import clases.Producto;
import clases.Usuario;
import modeloUsuario.ModeloUsuarioPl;
import modeloUsuario.ModeloUsuarioPr;

/**
 * Servlet implementation class RegistrarPlato
 */
@WebServlet("/RegistrarPlato")
public class RegistrarPlato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarPlato() {
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
			
			if (usuarioLogueado.getRol().getId()==(1)) {
		// TODO Auto-generated method stub
		ModeloUsuarioPr usuarioMPR = new ModeloUsuarioPr();
		usuarioMPR.conectar();

		ArrayList<Producto> productos = usuarioMPR.getProductos();

		usuarioMPR.cerrar();

		request.setAttribute("productos", productos);

		request.getRequestDispatcher("VistaRegistrarPlato.jsp").forward(request, response);
	}
			else {
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
		String nombre = request.getParameter("nombre");
		double precio = Double.parseDouble(request.getParameter("precio"));
		ModeloUsuarioPr modeloPr = new ModeloUsuarioPr();
		modeloPr.conectar();
		ArrayList<Producto> todosProductos = modeloPr.getProductos();
		ArrayList<Producto> productosPlato = new ArrayList<Producto>();
		Plato plato = new Plato();

		/*compara entre todos los productos cuales están selecionados
		 los selecionados los añade al plato*/
		for (Producto producto:todosProductos) {
			try {
				if (!request.getParameter(producto.getcProducto()+"").equals(null)) {
					productosPlato.add(producto);
				}
			} catch (Exception e) {
			}
		}
		modeloPr.cerrar();
//		ArrayList <Producto> productos =request.getParameter("producto");

		plato.setNombre(nombre);
		plato.setPrecio(precio);
		plato.setProductos(productosPlato);
		
		ModeloUsuarioPl modeloPl = new ModeloUsuarioPl();
		modeloPl.conectar();
		
		modeloPl.CrearPlato(plato);
		modeloPl.getCodigoPlatoNombre(plato);
		modeloPl.AnadirProductosPlato(plato);
		
		modeloPl.cerrar();
		
		
		response.sendRedirect("VerPlatos");
		
	}

}
