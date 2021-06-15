package uaslab.dimas.id.ac.umn;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import exceptions.*;

public abstract class User {
	private String name;
	private String userID;
	private String password;
	private char role;
	private static ArrayList<User> users = new ArrayList<User>();
	
	private static int loginAttempts = 0;
	private static final int maxLoginAttempts = 3;
	protected static boolean loginLimitReached = false;
	private MessageDigest digest;
	
	public User() {}
	public User(String name, String userID, String password, char role) {
		this.name = name;
		this.userID = userID;
		this.password = hash(password);
		this.role = role;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public char getRole() {
		return this.role;
	}
	
	public abstract void showUserDetails();
	
	public abstract boolean showMenu();
	
	public static void setUsers(User user) {
		users.add(user);
	}
	
	public static ArrayList<User> getUsers() {
		return users;
	}
	
	public static void printAllUsers() {
		for (User user : users) {
			System.out.println("Nama    : " + user.getName());
			System.out.println("User ID : " + user.getUserID());
			System.out.println("Role    : " + user.getRole());
			System.out.println("-------------------------------------------------");
		}
	}
	
	public boolean doLogin(String uid, String password) throws ExcessiveFailedLoginException {
		if (this.password.equals(hash(password)) && !loginLimitReached) {
			loginAttempts = 0;
				
			return true;
		}
		
		if (!loginLimitReached) {
			System.out.println("User ID / password salah!");
		}
		
		loginAttempts++;
		if (loginAttempts >= maxLoginAttempts) {
			loginLimitReached = true;
			throw new ExcessiveFailedLoginException();
		}
		
		return false;
	}
	
	private String hash(String strToHash) {
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(strToHash.getBytes(StandardCharsets.UTF_8));
			
			return DatatypeConverter.printHexBinary(hash);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "";
	}

}
