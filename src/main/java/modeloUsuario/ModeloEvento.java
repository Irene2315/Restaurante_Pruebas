package modeloUsuario;


	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import clases.Evento;
	import conexion.Conector;

	public class ModeloEvento  extends Conector{
		
				/**
				 * Proyecto: Base de datos Evento
				 * 
				 * @author Irene H. SanJose y Eneko Martinez
				 *
				 */
			
			PreparedStatement prt;
			
			/**

			Consigue el objeto evento que tiene este codigo evento
			@param cEvento: Es el parametro que necesitamos para conseguir el evento
			@return devuelve el evento con ese codigo de evento
			@throws SQLException envia al padre el error
			*/
			public Evento getEvento(int cEvento) throws SQLException {
			Evento evento = new Evento();


			prt = con.prepareStatement("SELECT * FROM eventos WHERE c_evento=?");
			prt.setInt(1, cEvento);

			ResultSet resultado = prt.executeQuery();
			resultado.next();

			evento.setcEvento(resultado.getInt("c_evento"));
			evento.setNombre(resultado.getString("nombre"));

			return evento;
			}

			/**

			Consigue todos los eventos de nuetra BDD

			@return eventos: devuelve todos los eventos de la BDD
			*/
			public ArrayList<Evento> getEventos() {
			PreparedStatement prt;
			ArrayList<Evento> eventos = new ArrayList<>();
			Evento evento = new Evento();
			try {
			prt = con.prepareStatement("SELECT * FROM eventos");
			ResultSet resultado = prt.executeQuery();

			
			 while (resultado.next()) {
			 	evento = new Evento();
			 	evento.setcEvento(resultado.getInt(1));
			 	evento.setNombre(resultado.getString(2));

			 	eventos.add(evento);

			 }
			} catch (SQLException e) {
			e.printStackTrace();
			}

			return eventos;
			}
			}