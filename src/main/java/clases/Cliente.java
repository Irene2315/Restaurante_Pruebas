package clases;

import java.util.ArrayList;

/* Los clientes seran las personas que ingresen a nuestra pagina web 
 * en busca de informacion de nuestro restaurante
 * estos podrán realizar reservas  */

public class Cliente {
	
	/*definimos la variables*/
	private String dni;
	private String nombre;
	private String apellido;
	private String telefono;
	private String correo;
	
	/*creamos los constructores*/
	public Cliente(String dni, String nombre, String apellido, String telefono, String correo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correo = correo;	
	}
	
	public Cliente() {
		
	}
	/*Creamos los getters y seters de cada atributo
	 * para consegir los datos de la base de datos o
	 * para enviarlos a la misma*/
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", correo=" + correo + "]";
	}

	public void anadirReserva() {
		
	}
	public void verReservas(ArrayList<Reserva> reservas) {
		
	}
	
 }
