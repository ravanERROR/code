package nvidia.in;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionJDBC {

	Connection c;
	Statement s;
	ResultSet rs;
	private String url = "jdbc:mysql://localhost:3306/linkedin_db";
	private String user = "root";
	private String pass = "pavan";

	/**
	 * 
	 */
	public ConnectionJDBC() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			c = DriverManager.getConnection(url, user, pass);

			System.out.println("database connection!");

			s = c.createStatement();
			
//			System.out.println("connected");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		
		new ConnectionJDBC();

	}
	
	
	
}
