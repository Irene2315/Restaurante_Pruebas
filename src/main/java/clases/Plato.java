package clases;

import java.util.ArrayList;
/*El clase platos hace referencia
 * a los platos que hay en nuestro
 * restaurante al ser una relacion
 * n,n tenemos dos opciones 
 * -hacer un arraylist de productos en el plato
 * -hacer un arraylist de platos en producto
 * 
 * en mi caso he elegido el primero
 * porque me parece el mas lógico 
 * */

public class Plato {
	private int cPlato;
	private String nombre;
	private Double precio;
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	
	public Plato(int cPlato, String nombre, Double precio, ArrayList<Producto> productos) {
		super();
		this.cPlato = cPlato;
		this.nombre = nombre;
		this.precio = precio;
		this.productos = productos;
	}
	
	public Plato() {
		
	}

	public int getcPlato() {
		return cPlato;
	}

	public void setcPlato(int cPlato) {
		this.cPlato = cPlato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Plato [cPlato=" + cPlato + ", nombre=" + nombre + ", precio=" + precio + ", productos=" + productos
				+ "]";
	}
	
	
	
}
