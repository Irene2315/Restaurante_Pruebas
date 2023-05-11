package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	
		private static  final String HOST= "localhost";
		private static final String BBDD= "bbdd_restaurante";
		private static final String USERNAME="root";
		private static final String PASSWORD="";
		
		protected  Connection con;


		
		public void conectar() {
			try {
				 
				
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					con = (Connection) DriverManager.getConnection("jdbc:mysql://"+HOST+ "/"+BBDD,USERNAME,PASSWORD);
				
				
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public void cerrar() {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Connection getConexion() {
			return con;
		}

		public void setConexion(Connection conexion) {
			this.con = conexion;
		}
		
		
	}