package modeloUsuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Plato;
import clases.Producto;
import conexion.Conector;

public class ModeloUsuarioPl extends Conector {
	/**
	 * Gestiona la BDD referenta al los platos
	 * 
	 */

	/**
	 * Consigue todos los platos del la BDD
	 * @return platos: devuelve todos los platos del la BDD
	 */
	public ArrayList<Plato> getPlatos() {

		PreparedStatement prt;
		ArrayList<Plato> platos = new ArrayList<>();

		try {
			prt = con.prepareStatement("SELECT c_plato, nombre, precio FROM platos");

			ResultSet result = prt.executeQuery();

			while (result.next()) {
				Plato plato = new Plato();
				plato.setcPlato(result.getInt(1));
				plato.setNombre(result.getString(2));
				plato.setPrecio(result.getDouble(3));

				platos.add(plato);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return platos;
	}

	/**
	 * Consigue todos los productos asociados a un plato
	 * @param cPlato: Recibe el codigo del plato y a traves de la tabla de relacion consigo los productos que pertenecen a ese plato
	 * @return productos: Devuelve todos los productos que pertecen al plato
	 */
	public ArrayList<Producto> getProductosPlato(int cPlato) {
		PreparedStatement prt;
		ArrayList<Producto> productos = new ArrayList<Producto>();

		try {
			prt = con.prepareStatement(
					"SELECT  c_producto,nombre  FROM productos INNER JOIN plato_producto ON c_producto = codigo_producto WHERE codigo_plato=?");
			prt.setInt(1, cPlato);

			ResultSet result = prt.executeQuery();

			while (result.next()) {
				Producto producto = new Producto();
				producto.setcProducto(result.getInt(1));
				producto.setNombre(result.getString(2));

				productos.add(producto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;

	}

	/**
	 * Consigue el plato concreto
	 * @param cPlato: En base al codigo del plato recibe el objeto plato asociado
	 * @return plato: devuelve el plato con ese codigo determinado
	 */
	public Plato getPlato(int cPlato) {

		PreparedStatement prt;
		Plato plato = new Plato();

		try {
			prt = con.prepareStatement("SELECT c_plato,nombre,precio FROM platos WHERE c_plato=?");

			prt.setInt(1, cPlato);

			ResultSet result = prt.executeQuery();

			if (result.next()) {

				plato.setcPlato(result.getInt("c_Plato"));
				plato.setNombre(result.getString("nombre"));
				plato.setPrecio(result.getDouble("precio"));

				plato.setProductos(getProductosPlato(cPlato));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plato;

		// cargar plato

	}
	/**
	 * Crea recibe plato nuevo y lo envia a la BDD
	 * @param plato: Recibe el objeto plato y lo importa en la BDD
	 */

	public void CrearPlato(Plato plato) {

		PreparedStatement prt;

		try {
			prt = con.prepareStatement("INSERT INTO platos(nombre,precio) VALUES (?,?)");

			prt.setString(1, plato.getNombre());
			prt.setDouble(2, plato.getPrecio());

			prt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Consigue el codigo de un plato en base al plato recibido
	 * @param plato: recibe el plato y consigue el código del plato
	 */
	public void getCodigoPlatoNombre(Plato plato) {
		PreparedStatement prt;
		
		try {
			prt = con.prepareStatement("select c_plato from platos where nombre=?");
			
			prt.setString(1, plato.getNombre());
			ResultSet result = prt.executeQuery();
			while(result.next()) {
				plato.setcPlato(result.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Anade los nuevos productos al plato para ello anadimos líneas a la tabla refereciada
	 * @param plato: recive el plato que va a insertar en la base de datos incluyendo cada producto relacionada en la tabla producto plato
	 */
	public void AnadirProductosPlato(Plato plato) {

		PreparedStatement prt;
		
		for(Producto producto:plato.getProductos()) {
			try {
				prt = con.prepareStatement("INSERT INTO plato_producto (codigo_plato,codigo_producto) VALUES (?,?)");
				prt.setInt(1, plato.getcPlato());
				prt.setInt(2, producto.getcProducto());
				prt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	/**
	 * Modifica un plato existente
	 * 
	 * @param plato: Recibe el objeto que se va a modificar
	 */
	public void ModiFicarPlato(Plato plato) {

		PreparedStatement prt;

		try {
			prt = con.prepareStatement("UPDATE platos SET nombre=?,precio=? WHERE c_plato=?");
			prt.setString(1, plato.getNombre());
			prt.setDouble(2, plato.getPrecio());
			prt.setInt(3,plato.getcPlato());
			prt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Modifica los productos asociados a un  plato para ello eliminaremos la relacion y luego crearemos los productos que se añadiran al plato
	 * @param plato: recive los datos de los productos que se van a  modificar en la base de datos
	 * 
	 */
	public void ModificarProductosPlato(Plato plato) {
		
		PreparedStatement prt;
		try {
			prt = con.prepareStatement("DELETE FROM `plato_producto` WHERE codigo_plato=?");
			prt.setInt(1, plato.getcPlato());
			prt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnadirProductosPlato(plato);
		
	}
	
	/**
	 * Eliminara los productos asociados a un plato
	 * @param cPlato: Recibira los productos asociados 
	 * 
	 */
	public void EliminarProductosPlato(int cPlato) {
		PreparedStatement prt;
		
		try {
			prt = con.prepareStatement("DELETE FROM plato_producto WHERE codigo_plato=?");
			
			prt.setInt(1, cPlato);
			prt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Eliminara un plato
	 * @param cPlato: En base al codigo del plato eliminara el plato
	 */
	public void EliminarPlato(int cPlato) {
		PreparedStatement prt;
		
		try {
			prt = con.prepareStatement("DELETE FROM platos WHERE c_plato=?");
			prt.setInt(1, cPlato);
			prt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
