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
 * Servlet implementation class ModificarPlato
 */
@WebServlet("/ModificarPlato")
public class ModificarPlato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarPlato() {
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

			if (usuarioLogueado.getRol().getId() == (1)) {
				int codPlato = Integer.parseInt(request.getParameter("cPlato"));
				boolean error = false;
				boolean productosLleno = true;
				ModeloUsuarioPl usuarioM = new ModeloUsuarioPl();
				usuarioM.conectar();
				Plato plato = usuarioM.getPlato(codPlato);

				usuarioM.cerrar();

				ArrayList<Producto> productosPlato = plato.getProductos();

				ModeloUsuarioPr usuarioMPR = new ModeloUsuarioPr();
				usuarioMPR.conectar();

				ArrayList<Producto> Todosproductos = usuarioMPR.getProductos();
				ArrayList<Producto> productosNOplato = new ArrayList<>();

				// conseguimos los productos que no estan en el plato
				for (int i = 0; i < Todosproductos.size(); i++) {
					boolean esta = false;

					for (int j = 0; j < productosPlato.size(); j++) {
						if (productosPlato.get(j).getcProducto() == Todosproductos.get(i).getcProducto()) {

							esta = true;
							break;
						}
					}
					if (esta == false) {
						productosNOplato.add(Todosproductos.get(i));
					}
				}

				usuarioMPR.cerrar();

				request.setAttribute("plato", plato);
				request.setAttribute("productosPlato", productosPlato);
				request.setAttribute("productosNOplato", productosNOplato);
				request.setAttribute("error", error);
				request.setAttribute("productosLleno", productosLleno );
				request.getRequestDispatcher("VistaModificarPlato.jsp").forward(request, response);

			} else {
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
		int cPlato = Integer.parseInt(request.getParameter("cPlato"));
		String nombre = request.getParameter("nombre");
		String precio = request.getParameter("precio");

		ModeloUsuarioPr modeloPr = new ModeloUsuarioPr();
		modeloPr.conectar();

		ArrayList<Producto> todosProductos = modeloPr.getProductos();
		ArrayList<Producto> productosPlato = new ArrayList<>();

		boolean errorPlato = false;
		boolean productosLleno = false;

		// compare de todos los productos los productos selecionados
		for (Producto producto : todosProductos) {
			try {
				if (!request.getParameter(producto.getcProducto() + "").equals(null)) {
					productosPlato.add(producto);
					productosLleno = true;
				}
			} catch (Exception e) {
			}
		}
		modeloPr.cerrar();
		if (true == esDouble(precio) ) {
			errorPlato = true;
		}
		if (errorPlato == false && productosLleno ==true ) {
			Plato plato = new Plato();
			plato.setcPlato(cPlato);
			plato.setNombre(nombre);
			plato.setPrecio(Double.parseDouble(precio));
			plato.setProductos(productosPlato);

			ModeloUsuarioPl modeloPl = new ModeloUsuarioPl();
			modeloPl.conectar();
			modeloPl.ModiFicarPlato(plato);
			modeloPl.ModificarProductosPlato(plato);
			modeloPl.cerrar();

			response.sendRedirect("VerPlatos");
		}

		else {
			int codPlato = Integer.parseInt(request.getParameter("cPlato"));

			ModeloUsuarioPl usuarioM = new ModeloUsuarioPl();
			usuarioM.conectar();
			Plato plato = usuarioM.getPlato(codPlato);

			usuarioM.cerrar();

			ArrayList<Producto> productosPlato2 = plato.getProductos();

			ModeloUsuarioPr usuarioMPR = new ModeloUsuarioPr();
			usuarioMPR.conectar();

			ArrayList<Producto> Todosproductos = usuarioMPR.getProductos();
			ArrayList<Producto> productosNOplato = new ArrayList<>();

			// conseguimos los productos que no estan en el plato
			for (int i = 0; i < Todosproductos.size(); i++) {
				boolean esta = false;

				for (int j = 0; j < productosPlato2.size(); j++) {
					if (productosPlato2.get(j).getcProducto() == Todosproductos.get(i).getcProducto()) {

						esta = true;
						break;
					}
				}
				if (esta == false) {
					productosNOplato.add(Todosproductos.get(i));
				}
			}

			usuarioMPR.cerrar();

			request.setAttribute("plato", plato);
			request.setAttribute("productosPlato", productosPlato2);
			request.setAttribute("productosNOplato", productosNOplato);
			request.setAttribute("error", errorPlato);
			request.setAttribute("productosLleno", productosLleno );
			request.getRequestDispatcher("VistaModificarPlato.jsp").forward(request, response);

		}
	}

	public static boolean esDouble(String stringDouble) {
		try {
			double d = Double.parseDouble(stringDouble);
		} catch (NumberFormatException nfe) {
			return true; // Error no es numerico
		}
		return false; // Es numerico
	}
}
