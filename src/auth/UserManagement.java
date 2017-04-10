package auth;

import java.sql.ResultSet;

import db.DBController;

public class UserManagement {
	
	public static int USERNAME_ALREADY_EXISTS = 100;
	public static int PASSWORD_TOO_SHORT = 200;
	public static int REGISTER_SUCCESS = 0;

	public static int registerUser(String username, char[] password, String fullname, String position) {
		if (matchUserID(username)) {
			return USERNAME_ALREADY_EXISTS;
		} else if (password.length < 6) {
			return PASSWORD_TOO_SHORT;
		}
		
		String query = "INSERT INTO user (Nama_user, Password, login_state, position, username) VALUE ('"
				+ fullname + "', '" + String.valueOf(password) + "', '" + "1" + "', '" 
				+ (position.equals("Store Manager") ? "s" : "c") + "', '" 
				+ username + "')";
		ResultSet rs = DBController.queryDatabase(query);
		
		return REGISTER_SUCCESS;
	}
	
	public static void login(String username, char[] password) {
		System.out.println(username);
		System.out.println(password);
	}
	
	public static void logout() {
		
	}
	
	private static boolean matchUserID(String username) {
		String query = "SELECT * FROM user WHERE username = '" + username + "'";
		ResultSet rs = DBController.queryDatabase(query);
		
		try {
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
