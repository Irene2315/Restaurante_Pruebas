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
		boolean error =false;
		boolean productosLleno = true;
		

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
		request.setAttribute("error", error);
		request.setAttribute("productosLleno", productosLleno );
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
		String precio = request.getParameter("precio");
		ModeloUsuarioPr modeloPr = new ModeloUsuarioPr();
		modeloPr.conectar();
		ArrayList<Producto> todosProductos = modeloPr.getProductos();
		ArrayList<Producto> productosPlato = new ArrayList<Producto>();
		boolean error =false;
		boolean productosLleno =false;

		/*compara entre todos los productos cuales están selecionados
		 los selecionados los añade al plato*/
		for (Producto producto:todosProductos) {
			try {
				if (!request.getParameter(producto.getcProducto()+"").equals(null)) {
					productosPlato.add(producto);
					productosLleno=true;
				}
			} catch (Exception e) {
			}
		}
		modeloPr.cerrar();
//		ArrayList <Producto> productos =request.getParameter("producto");
		if (true==esDouble(precio) )
			{
					error=true;
			}	
		if(error==false && productosLleno ==true) {
		Plato plato = new Plato();
		plato.setNombre(nombre);
		plato.setPrecio(Double.parseDouble(precio));
		plato.setProductos(productosPlato);
		
		ModeloUsuarioPl modeloPl = new ModeloUsuarioPl();
		modeloPl.conectar();
		
		modeloPl.CrearPlato(plato);
		modeloPl.getCodigoPlatoNombre(plato);
		modeloPl.AnadirProductosPlato(plato);
		
		modeloPl.cerrar();
		response.sendRedirect("VerPlatos");
		}
		
		else {
			ModeloUsuarioPr usuarioMPR = new ModeloUsuarioPr();
			usuarioMPR.conectar();

			ArrayList<Producto> productos = usuarioMPR.getProductos();

			usuarioMPR.cerrar();

			request.setAttribute("productos", productos);
			request.setAttribute("error", error);
			request.setAttribute("productosLleno", productosLleno );
			request.getRequestDispatcher("VistaRegistrarPlato.jsp").forward(request, response);
		}
		
		
		
	}
	
	public static boolean esDouble(String stringDouble) {
	    try {
	        double d = Double.parseDouble(stringDouble);
	    } catch (NumberFormatException nfe) {
	        return true; //Error no es numerico
	    }
	    return false; //Es numerico
	}

}
