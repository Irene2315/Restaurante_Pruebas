package clases;
/*Esta clase hará referencia a los distintos productos
 *  que hay en cada plato se podrán crear independientemente
 *  y selecionarlos en el plato*/

public class Producto {
	private int cProducto;
	private String nombre;
	private double calorias;
	private double proteinas;
	private int cantidad;
	private Double precio;
	
	
	public Producto(int cProducto, String nombre, double calorias, double proteinas, int cantidad, Double precio) {
		super();
		this.cProducto = cProducto;
		this.nombre = nombre;
		this.calorias = calorias;
		this.proteinas = proteinas;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public Producto() {
		
	}

	public int getcProducto() {
		return cProducto;
	}

	public void setcProducto(int cProducto) {
		this.cProducto = cProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public double getProteinas() {
		return proteinas;
	}

	public void setProteinas(double proteinas) {
		this.proteinas = proteinas;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
	
}

	
	