package clases;
/*Este atributo ará referencia a los distintos roles que puede 
 * tener cada empleado estos verdan asignados por defecto en la base 
 * de datos*/
public class RolUsuario {
	private int id;
	private String nombre;
	
	public RolUsuario(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public RolUsuario() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "RolUsuario [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
}
