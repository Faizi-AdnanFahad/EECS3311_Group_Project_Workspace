package application;

import java.security.MessageDigest;
import persistence.RealDatabase;

public class AccountsController {
	static RealDatabase db = new RealDatabase();
	
	public AccountsController() {
		
	}
	
	/**
	 * Verifies if login username and password (hashed) is correct by 
	 * checking the accounts database.
	 * @return boolean true or false.
	 */
	public static boolean accountVerification(String username, String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] passHash = md.digest(password.getBytes());
		password= passHash.toString();
		
	  return db.getUser(username, password);
	}
	
	/**
	 * Checks if username already exists. 
	 * If not, a new account is created and added to the accounts database
	 */
	public static String registerAccount(String username, String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] passHash = md.digest(password.getBytes());
		password = passHash.toString();
		
			if (db.getUser(username, password)) {
				return "User already exists!";
			}	

		db.addUser(username, password);
		return "Account created!";
	}

}
