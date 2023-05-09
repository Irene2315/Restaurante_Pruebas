package clases;

import java.util.Date;

/*Con esta clase el cliente podrá realizar reservas
 * y ademas usuario con rol gerente  podrá modificar o
 * eliminar la reserva */

/*Como no hay un registro de clientes cuando se cree 
 * una reserva tambien se creará el cliente*/

/*Se podrá selecionar el evento que se realizará en esa
 * reserva*/
public class Reserva {
	private int nReserva;
	private Date fecha;
	private Cliente cliente; //recibe los datos del odjeto usuario
	private Evento evento;//recibe los datos del odjeto evento
	
	public Reserva(int nReserva, Date fecha, Cliente cliente, Evento evento) {
		super();
		this.nReserva = nReserva;
		this.fecha = fecha;
		this.cliente = cliente;
		this.evento = evento;
	}
	
	public Reserva() {
		
	}

	public int getnReserva() {
		return nReserva;
	}

	public void setnReserva(int nReserva) {
		this.nReserva = nReserva;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public String toString() {
		return "Reserva [nReserva=" + nReserva + ", fecha=" + fecha + ", cliente=" + cliente + ", evento=" + evento
				+ "]";
	}
	

	
	
}
