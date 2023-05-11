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
 * Servlet implementation class RegistrarProducto
 */
@WebServlet("/RegistrarProducto")
public class RegistrarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
		boolean error =false;
		
		
		if (usuarioLogueado == null) {// no logeado
			
			response.sendRedirect("PaginaReservaCliente");
		} else {
			
			if (usuarioLogueado.getRol().getId()==(1)) {
		request.setAttribute("error", error);
		request.getRequestDispatcher("VistaRegistrarProducto.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("PaginaProductos");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre =request.getParameter("nombre");
		String calorias = request.getParameter("calorias");
		String proteinas = request.getParameter("proteinas");
		String cantidad = request.getParameter("cantidad");
		String precio = request.getParameter("precio");
		boolean error =false;
		
		if (true==esDouble(calorias) || true==esDouble(proteinas) ||
				true==esInt(cantidad) || true==esDouble(precio))
			{
					error=true;
			}	
		
		
		if(error==false) {
		Producto producto = new Producto ();
		producto.setNombre(nombre);
		producto.setCalorias(Double.parseDouble(calorias));
		producto.setProteinas(Double.parseDouble(proteinas));
		producto.setCantidad(Integer.parseInt(cantidad));
		producto.setPrecio(Double.parseDouble(precio));
		
		ModeloUsuarioPr usuarioM = new ModeloUsuarioPr();
		usuarioM.conectar();
		
		usuarioM.registrarProducto(producto);
		
		usuarioM.cerrar();
		
		response.sendRedirect("VerProductos");
		}
		
		else {
			request.setAttribute("error", error);
			request.getRequestDispatcher("VistaRegistrarProducto.jsp").forward(request, response);
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
	
	public static boolean esInt(String stringInt) {
	    try {
	        int d =Integer.parseInt(stringInt) ;
	    } catch (NumberFormatException nfe) {
	        return true; //Error no es numerico
	    }
	    return false; //Es numerico
	}

}
