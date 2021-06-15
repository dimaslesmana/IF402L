package week10.dimas.id.ac.umn;

import exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static Scanner s = new Scanner(System.in);
	private static List<User> listOfUser = new ArrayList<User>();
	private static final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,16}$";

	public static void main(String[] args) {
		int choice;
		
		initialize();
		
		while (true) {
			mainMenu();
			choice = s.nextInt();
			s.nextLine();
			
			switch (choice) {
			case 1:
				login();
				break;
			case 2:
				signUp();
				break;
			}
		}
	}
	
	private static void initialize() {		
		listOfUser.add(new User("John", "Doe", 'L', "Jl. Merpati No. 1 RT 1 RW 1, Banten", "admin", "admin"));
	}
	
	private static void mainMenu() {
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.print("Pilihan : ");
	}
	
	private static void login() {
		String username;
		String password;
		boolean status = false;
		
		do {
			System.out.print("Username : ");
			username = s.nextLine();
			
			System.out.print("Password : ");
			password = s.nextLine();
			
			try {
				status = handleLogin(username, password);
			} catch (AuthenticationException ex) {
				System.out.println(ex.getMessage());
				break;
			}
		} while (!status);
	}
	
	private static void signUp() {
		String firstName;
		String lastName;
		Character gender;
		String address;
		String userName;
		String password;
		
		do {
			System.out.print("Nama Depan : ");
			firstName = s.nextLine();
			
			System.out.print("Nama Belakang : ");
			lastName = s.nextLine();
			
			System.out.print("Jenis Kelamin (L/P) : ");
			gender = s.nextLine().toUpperCase().charAt(0);
			
			System.out.print("Alamat : ");
			address = s.nextLine();
			
			System.out.print("Username : ");
			userName = s.nextLine();
			try {
				handleSignUp(userName, null);
			} catch (InvalidPropertyException ex) {
				System.out.println(ex.getMessage());
				continue;
			}
			
			System.out.print("Password : ");
			password = s.nextLine();
			
			try {
				handleSignUp(userName, password);
			} catch (InvalidPropertyException ex) {
				System.out.println(ex.getMessage());
				continue;
			}
			
			listOfUser.add(new User(firstName, lastName, gender, address, userName, password));
			System.out.println("User telah berhasil didaftarkan!\n");
			break;
		} while (true);
	}
	
	private static boolean handleLogin(String username, String password) throws AuthenticationException {
		boolean userFound = false;
		boolean error = false;
		
		for (User user : listOfUser) {
			try {
				if (user.getUserName().equals(username)) {
					userFound = true;
					
					if (user.login(username, password)) {
						System.out.println(user.greeting() + "\n");
						return true;
					}
				}
			} catch (ExcessiveFailedLoginException ex) {
				System.out.println(ex.getMessage());
				error = true;
			}
		}
		
		if (!userFound) {
			throw new AuthenticationException("Gagal Login!");
		}
		
		return error;
	}
	
	private static void handleSignUp(String userName, String password) throws InvalidPropertyException {
		if (userName.trim().length() <= 8) {
			throw new InvalidPropertyException("Username harus lebih dari 8 karakter!");
		}
		
		if (password != null) {
			if (password.isEmpty()) {
				throw new InvalidPropertyException("Password tidak boleh kosong!");
			}
			
			if (!password.matches(passwordRegex)) {
				throw new InvalidPropertyException("Password harus mengandung huruf besar, angka, minimum 6 karakter dan maksimum 16 karakter!");
			}
		}
	}

}
