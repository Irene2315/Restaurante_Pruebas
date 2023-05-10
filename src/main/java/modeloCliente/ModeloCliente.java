package modeloCliente;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import clases.Cliente;
import clases.Reserva;
import conexion.Conector;
import modeloEvento.ModeloEvento;


public class ModeloCliente extends Conector{
	

	/**
	 * Proyecto: Base de datos Cliente
	 * 
	 * @autor Irene H. SanJose y Eneko Martinez
	 */

	/**
	 * El metodo registrar cliente pedira un cliente de del objeto cliente y lo
	 * insertara a la base de datos
	 * 
	 * @param cliente del Objeto Cliente: Este metodo recibe el objeto cliente
	 * @throws SQLException En caso de que no funcione llamara al padre
	 */
	public void registrarCliente(Cliente cliente) {

		PreparedStatement prt;

		try {
			prt = con.prepareStatement("INSERT INTO clientes(DNI, nombre,apellido, telefono,correo) VALUES (?,?,?,?,?)");

			prt.setString(1, cliente.getDni());
			prt.setString(2, cliente.getNombre());
			prt.setString(3, cliente.getApellido());
			prt.setString(4, cliente.getTelefono());
			prt.setString(5, cliente.getCorreo());

			prt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * El metodo DNI existe comprobara si el usuario asociado existe
	 * 
	 * @param DNI : Este parametro rego el DNI que queremos comprobar
	 * @return boolean Se enviara si el DNI se ha encontrado en la BDD
	 * @throws SQLException En caso de que no funcione llamara al padre
	 */
	public boolean DNIExiste(String DNI) {

		Boolean encontrado = false;
		PreparedStatement prt;
		try {
			prt = con.prepareStatement("SELECT DNI FROM clientes WHERE DNI=?");

			prt.setString(1, DNI);

			ResultSet resultado = prt.executeQuery();

			if (resultado.next()) {
				encontrado = true;
			} else {
				encontrado = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encontrado;

	}

	/**
	 * El metodo getCliente consigue el objeto cliente a traves del DNI
	 * 
	 * @param DNI: Se envia este parametro para conseguir el objeto cliente
	 * @return Cliente Devolvera el cliente que tiene ese DNI
	 * @throws SQLException En caso de que no funcione llamara al padre
	 */
	public Cliente getCliente(String DNI) {
		Cliente cliente = new Cliente();

		PreparedStatement prt;

		try {
			prt = con.prepareStatement("SELECT nombre,apellido,telefono,correo FROM clientes WHERE DNI=?");

			prt.setString(1, DNI);

			ResultSet resultado = prt.executeQuery();

			if (resultado.next()) {
				cliente.setDni(DNI);
				cliente.setNombre(resultado.getString("nombre"));
				cliente.setApellido(resultado.getString("Apellido"));
				cliente.setTelefono(resultado.getString("telefono"));
				cliente.setCorreo(resultado.getString("correo"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cliente;

	}

	/**
	 * Inserta una reserva
	 * 
	 * @param reserva Recibe el objeto reserva para insertar en la BDD
	 * @throws SQLException En caso de que no funcione llamara al padre
	 *  
	 */
	
	
	
	
	
	public void crearReserva(Reserva reserva) {
		
		PreparedStatement prt;
		
		try {
			prt= con.prepareStatement("INSERT INTO reservas (fecha,DNI,nombre, telefono,id_evento) VALUES (?,?,?,?,?)");
			prt.setDate(1, new Date (reserva.getFecha().getTime() ));
			prt.setString(2, reserva.getCliente().getDni());
			
			prt.setString(3, reserva.getCliente().getNombre());
			
			prt.setString(4, reserva.getCliente().getTelefono());
			
			prt.setInt(5, reserva.getEvento().getcEvento());
			
		
			
			prt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}





		public ArrayList<Reserva> getReservasUsuario(String DNI){
		ModeloEvento eventoM = new ModeloEvento();
			eventoM.setConexion(this.con);
		PreparedStatement prt;

		ArrayList <Reserva> reservas = new ArrayList<>();

		Reserva reserva = new Reserva();

		try {

		prt = con.prepareStatement("SELECT n_reserva,fecha,id_evento  FROM reservas WHERE DNI=?");
		
		prt.setString(1, DNI);

		ResultSet resultado = prt.executeQuery();

		while(resultado.next()) {

		reserva = new Reserva();

		reserva.setnReserva(resultado.getInt(1));

		reserva.setFecha(resultado.getDate(2));
		reserva.setEvento(eventoM.getEvento(resultado.getInt(3)));	
		

		
		
		reservas.add(reserva);

		}

		} catch (SQLException e) {

		e.printStackTrace();

		}

		return reservas;

		}


	
	
}