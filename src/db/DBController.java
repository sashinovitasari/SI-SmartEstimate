package db;

import java.sql.*;

public class DBController {
	public static Connection conn;
	
	public static void main(String args[]) {
		/*** CARA PAKAI DBController ***/
		String query = "SELECT * FROM user";
		ResultSet rs = queryDatabase(query);
		try {
			while(rs.next()) {
				String username = rs.getString("username");
				System.out.println(username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DONE");
	}
	
	public static Connection connectDatabase() {
		Connection conn = null;
		try {
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/smartestimate";
			Class.forName(myDriver);
			conn = DriverManager.getConnection(myUrl, "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static ResultSet queryDatabase(String query) {
		ResultSet rs = null;
		Connection conn = connectDatabase();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void closeDatabase() {
		
	}
}
