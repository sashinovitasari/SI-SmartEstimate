package auth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dashboard.DashboardCashierPage;
import dashboard.DashboardSMPage;
import db.DBController;
import news.InfoNews;

public class UserManagement {
	
	public static int USERNAME_ALREADY_EXISTS = 1;
	public static int PASSWORD_TOO_SHORT = 2;
	public static int EMPTY_FIELD = 3;
	public static int AUTHENTICATION_FAILED = 4;
	public static int REGISTER_SUCCESS = 0;
	public static int LOGIN_SUCCESS = 0;

	public static int registerUser(String username, char[] password, String fullname, String position) {
		if (username.matches("") || password.length == 0 || fullname.matches("")) {
			return EMPTY_FIELD;
		} else if (matchUserID(username)) {
			return USERNAME_ALREADY_EXISTS;
		} else if (password.length < 6) {
			return PASSWORD_TOO_SHORT;
		}
		
		String query = "INSERT INTO user (Nama_user, Password, login_state, position, username) VALUE ('"
				+ fullname + "', '" + String.valueOf(password) + "', '" + "1" + "', '" 
				+ (position.equals("Store Manager") ? "s" : "c") + "', '" 
				+ username + "')";
		DBController.connectDatabase();
		ResultSet rs = DBController.queryDatabase(query);
		DBController.closeDatabase();
		
		return REGISTER_SUCCESS;
	}
	
	public static int doLogin(String username, char[] password) {
		if (username.matches("") || password.length == 0) {
			return EMPTY_FIELD;
		} else if (validateUser(username, password)) {
			String query = "UPDATE user SET login_state = 1 WHERE username = '" + username 
					+ "' AND password = '" + String.valueOf(password) + "'";
			DBController.connectDatabase();
			DBController.queryDatabase(query);
			DBController.closeDatabase();
			return LOGIN_SUCCESS;
		} else {
			return AUTHENTICATION_FAILED;
		}
	}
	
	public static void logout() {
		
	}
	
	private static boolean matchUserID(String username) {
		String query = "SELECT * FROM user WHERE username = '" + username + "'";
		DBController.connectDatabase();
		ResultSet rs = DBController.queryDatabase(query);
		boolean result = false;
		try {
			result = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBController.closeDatabase();
		}
		
		return result;
	}
	
	private static boolean validateUser(String username, char[] password) {
		String query = "SELECT * FROM user WHERE username = '" + username 
				+ "' AND password = '" + String.valueOf(password) + "'";
		DBController.connectDatabase();
		ResultSet rs = DBController.queryDatabase(query);
		boolean result = true;
		try {
			result = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBController.closeDatabase();
		}
		return result;
	}
	
	public static String getRole(String username){
		String prn = "s";	
		Connection con = DBController.connectDatabase();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT position FROM user WHERE username=\""+username+"\"";
			ResultSet res = stmt.executeQuery(query);
			while (res.next()) prn = res.getString("position");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBController.closeDatabase();
		return prn;	
	}
}
