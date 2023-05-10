package modeloUsuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import clases.Cliente;
import clases.Evento;
import clases.Reserva;
import clases.RolUsuario;
import clases.Usuario;
import conexion.Conector;
import modeloEvento.ModeloEvento;

public class ModeloUsuario extends Conector{
	/**

	Base de datos de usuarios y gestion de reservas
	*/
	
	PreparedStatement prt;
	
	/**

	Obtiene la reserva asociada al numero de reserva
	@param nReserva: Envia el numero de reserva para poder conseguir el objeto asociado
	@return Reserva: Devuelve la reserva asociada al objeto reserva
	@throws SQLException en caso de error lo envia el aviso al padre
	*/
	public Reserva getReserva(int nReserva) throws SQLException {
	Reserva reserva = new Reserva();
	prt = con.prepareStatement("SELECT * FROM reservas WHERE n_reserva=?");
	prt.setInt(1, nReserva);
	ResultSet resultado = prt.executeQuery();
	resultado.next();
	
	reserva.setnReserva(resultado.getInt("n_reserva"));
	reserva.setFecha(resultado.getDate("fecha"));
	
	Evento evento = new Evento();
	evento.setcEvento(resultado.getInt("id_evento"));
	reserva.setEvento(evento);
	
	Cliente cliente = new Cliente();
	cliente.setDni(resultado.getString("DNI"));
	cliente.setNombre(resultado.getString("nombre"));
	cliente.setTelefono(resultado.getString("telefono"));
	reserva.setCliente(cliente);
	
	return reserva;
	}
	
	
	public ArrayList<Reserva> getReservas()  {
		ModeloEvento eventoM = new ModeloEvento();
		eventoM.setConexion(this.con);
		ArrayList <Reserva> reservas = new ArrayList<Reserva>();
		try {
			prt = con.prepareStatement("SELECT * FROM reservas ");
			ResultSet resultado = prt.executeQuery();
			while(resultado.next()) {
			Reserva reserva = new Reserva();
			reserva.setnReserva(resultado.getInt("n_reserva"));
			reserva.setFecha(resultado.getDate("fecha"));
			Cliente cliente = new Cliente();
			cliente.setDni(resultado.getString("DNI"));
			cliente.setNombre(resultado.getString("nombre"));
			cliente.setTelefono(resultado.getString("telefono"));
			reserva.setEvento(eventoM.getEvento(resultado.getInt("id_evento")));	
			reserva.setCliente(cliente);
			reservas.add(reserva);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return reservas;
	}
	
	/**
	 * Modifica una reserva ya existente
	 * 
	 * @param reserva: Recive el objeto que va a ser modificado
	 */
	
		public void modificarReserva(Reserva reserva) {
			try {
				prt = con.prepareStatement("UPDATE reservas SET fecha=?,nombre=?,telefono=?,id_evento=? WHERE n_reserva=?" );
				prt.setInt(5, reserva.getnReserva());

				prt.setDate(1, new Date(reserva.getFecha().getTime()));
				prt.setString(2, reserva.getCliente().getNombre());
				prt.setString(3, reserva.getCliente().getTelefono());
				prt.setInt(4, reserva.getEvento().getcEvento());
				
				prt.executeUpdate();
			} catch (Exception e) {
				
			}
		}
		
		/**
		 * Elimina la reserva asociado al numero de reserva
		 * @param nReserva: Recibe el parametro de numero de reserva y elimina el objeto asociado 
		 */
		public void eliminarReserva(int nReserva) {
			try {
				prt = con.prepareStatement("DELETE FROM reservas WHERE n_reserva = ?");
				
				prt.setInt(1, nReserva);

				prt.execute();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		/**
		 *  Inserta un nuevo usuario en la BDD 
		 * @param usuario: Recibe el objeto y se inserta en la BDD
		 */
		
		public void insertarUsuario(Usuario usuario) {
			try {
				prt = con.prepareStatement("INSERT INTO usuarios(nombre,apellido,contraseña,telefono,correo_trabajo,id_rol) VALUES (?,?,?,?,?,?)");
				
				
				prt.setString(1, usuario.getNombre());
				prt.setString(2, usuario.getApellido());
				prt.setString(3, usuario.getContrasena());
				prt.setString(4, usuario.getTelefono());
				prt.setString(5, usuario.getCorreoTrabajo());
				prt.setInt(6, usuario.getRol().getId());
				
				 
				
				prt.execute();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		/**
		 * Consigue todos los usuario registrados en la BDD
		 * 
		 * @return Usuarios: Devuelve los usuarios registrados
		 */
		public ArrayList<Usuario> getUsuarios(){
			ModeloRolUsuario rolUM = new ModeloRolUsuario ();
			   rolUM.setConexion(this.con);
			
			PreparedStatement prt;
			ArrayList <Usuario> usuarios = new ArrayList <>();
			Usuario usuario = new Usuario();
			try {
				prt = con.prepareStatement("SELECT * FROM usuarios");
				ResultSet resultado = prt.executeQuery();
				
				
				
				while(resultado.next()) {
					usuario = new Usuario();
					usuario.setcUsuario(resultado.getInt(1));
					usuario.setNombre(resultado.getString(2));
					usuario.setApellido(resultado.getString(3));
					usuario.setContrasena(resultado.getString(4));
					usuario.setTelefono(resultado.getString(5));
					usuario.setCorreoTrabajo(resultado.getString(6));
					
					
					usuario.setRol(rolUM.getRol(resultado.getInt(7)));
					
					usuarios.add(usuario);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return usuarios;
			}
		
		/**
		 * Coseguirmos el usuario asociado al codigo de usuario
		 * 
		 * @param cUsuario: A traves de este parametro conseguiremos el objeto asociado a este codigo de usuario
		 * @return usuario: Consigue el usuario asociado al codigo de usuario
		 * @throws SQLException: En caso de error envia los errores al padre
		 */
		public Usuario getUsuario(int cUsuario) throws SQLException  {
			ModeloRolUsuario rolUM = new ModeloRolUsuario ();
			   rolUM.setConexion(this.con);
			Usuario usuario = new Usuario();
		
				prt=con.prepareStatement("SELECT c_usuario,nombre,apellido,contraseña,telefono,correo_trabajo,id_rol FROM usuarios WHERE c_usuario=?");
				prt.setInt(1, cUsuario);
				
				ResultSet resultado = prt.executeQuery();
				resultado.next();
				
				usuario.setcUsuario(resultado.getInt("c_usuario"));
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setApellido(resultado.getString("apellido"));
				usuario.setContrasena(resultado.getString("contraseña"));
				usuario.setTelefono(resultado.getString("telefono"));
				usuario.setCorreoTrabajo(resultado.getString("correo_trabajo"));
				usuario.setRol(rolUM.getRol(resultado.getInt("id_rol")));
				
				return usuario;
				
		}
		
		/**
		 * Elimina un usuario 
		 * @param cUsuario: Se eliminara el usuario asociado al codigo de usuario
		 */
		
		public void eliminarUsuario(int cUsuario) {
			try {
				prt = con.prepareStatement("DELETE FROM usuarios WHERE c_usuario = ?");
				
				prt.setInt(1, cUsuario);

				prt.execute();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		/**
		 * Modifica los datos de un usuario existente
		 * @param usuario: Recibe los datos del usuario que va a modificar
		 */
		public void modificarUsuario(Usuario usuario) {
			
			try {
				 prt = con.prepareStatement("UPDATE usuarios SET c_usuario=?,nombre=?,apellido=?,contraseña=?,telefono=?,correo_trabajo=?,id_rol=? WHERE c_usuario=?");
				 
				 prt.setInt(1, usuario.getcUsuario());
				 prt.setString(2, usuario.getNombre());
				 prt.setString(3, usuario.getApellido());
				 prt.setString(4, usuario.getContrasena());
				 prt.setString(5, usuario.getTelefono());
				 prt.setString(6, usuario.getCorreoTrabajo());
				 prt.setInt(7, usuario.getRol().getId());
				 prt.setInt(8, usuario.getcUsuario());
				 
				 prt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		/**
		 * Consigue la contrasena asociada a un usuario
		 * @param cUsuario: A traves del codigo del usuario consigue la contrasena asociada
		 * @return contrasena: Envia la contrasena asociada a este usuario
		 */
		public String getContrasena(int cUsuario) {
			
			Usuario usuario = new Usuario();
			
			try {
				PreparedStatement prt;
				
				prt= con.prepareStatement("SELECT contraseña FROM usuarios WHERE c_usuario=?");
				
				prt.setInt(1, cUsuario);
				
				ResultSet result = prt.executeQuery();
				
				while(result.next()) {
				
					usuario.setContrasena(result.getString(1));
					

					
					return usuario.getContrasena();	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return usuario.getContrasena();	
			
			
		}
	
	}
