package auth;


public class AuthController {

	public static void register(String username, char[] password, String fullname, String position) {
		System.out.println(username);
		System.out.println(password);
		System.out.println(fullname);
		System.out.println(position);
		
	}
	
	public static void login(String username, char[] password) {
		System.out.println(username);
		System.out.println(password);
	}
	
	public static void logout() {
		
	}
}
