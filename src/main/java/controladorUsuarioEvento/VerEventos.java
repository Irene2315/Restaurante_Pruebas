package controladorUsuarioEvento;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Evento;
import clases.Usuario;
import modeloEvento.ModeloEvento;


/**
 * Servlet implementation class VerEventos
 */
@WebServlet("/VerEventos")
public class VerEventos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerEventos() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
		
		if(usuarioLogueado == null) {
			response.sendRedirect("PaginaReservaCliente");
		}else {
			
			ModeloEvento modeloevento = new ModeloEvento();
			modeloevento.conectar();
			
			ArrayList <Evento> eventos = modeloevento.getEventos();				
			eventos = modeloevento.getEventos();
			
			modeloevento.cerrar();
			
			request.setAttribute("eventos", eventos);
			session.setAttribute("usuarioLogueado", usuarioLogueado);
			
			request.getRequestDispatcher("VistaEventos.jsp").forward(request, response);
			
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
