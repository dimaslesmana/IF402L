package uaslab.dimas.id.ac.umn;

import exceptions.*;
import java.util.*;

public class Main {

	static Scanner s = new Scanner(System.in);
	private static User currentUser;
	private static boolean exit = false;
	
	public static void main(String[] args) {
		boolean loggedIn = false;
		boolean showMenu = true;
		
		/*
		 * Un-comment untuk seed data mahasiswa, dosen, dan kegiatan
		 * - Akun Mahasiswa (userid:password)
		 * mhs-n:mhs-n
		 * - Akun Dosen (userid:password)
		 * dosen-n:dosen-n
		 * userid == password
		 * */
		// Seeder.seed(5);
		// User.printAllUsers();
		
		while (!loggedIn && !exit) {
			loggedIn = auth();
			
			while (loggedIn) {
				System.out.println("------------------------------");
				currentUser.showUserDetails();
				showMenu = currentUser.showMenu();
				
				if (!showMenu) {
					loggedIn = false;
					break;
				}
			}
		}
		
		s.close();
		System.out.println("Program exited");
	}
	
	private static boolean auth() {
		boolean authStatus = false;
		int choice;
		int roleChoice;
		String name;
		String uid;
		String password;
		char role;
		
		System.out.println("------------------------------");
		System.out.println("1. Sign In");
		System.out.println("2. Sign Up");
		System.out.println("0. Keluar");
		System.out.print("Pilihan : ");
		choice = s.nextInt();
		clearScanner();
		
		switch (choice) {
		case 1:
			do {
				System.out.println("-----Pilih role user-----");
				System.out.println("1. Dosen");
				System.out.println("2. Mahasiswa");
				System.out.print("Pilihan : ");
				roleChoice = s.nextInt();
				clearScanner();
			} while (roleChoice != 1 && roleChoice != 2);
			
			role = roleChoice == 1 ? 'D' : 'M';
			
			do {
				System.out.print("User ID  : ");
				uid = s.nextLine();
				System.out.print("Password : ");
				password = s.nextLine();
				
				try {
					authStatus = handleLogin(uid, password, role);
				} catch (AuthenticationException ex) {
					System.out.println(ex.getMessage());
					return false;
				}
			} while (!authStatus && !User.loginLimitReached);
			
			return authStatus;
		case 2:
			do {
				 System.out.println("-----Pilih role user-----");
				 System.out.println("1. Dosen");
				 System.out.println("2. Mahasiswa");
				 System.out.print("Pilihan : ");	
				 roleChoice = s.nextInt();
				 clearScanner();
			 } while (roleChoice != 1 && roleChoice != 2);
			
			role = roleChoice == 1 ? 'D' : 'M';
			
			System.out.print("Name     : ");
			name = s.nextLine();
			
			do {
				System.out.print("User ID  : ");
				uid = s.nextLine();
			} while (Helpers.isUidExists(uid));
			
			System.out.print("Password : ");
			password = s.nextLine();
			 
			if (role == 'D') {
				User.setUsers(new Dosen(name, uid, password, role));
				System.out.println("Dosen berhasil ditambahkan!");
			} else {
				User.setUsers(new Mahasiswa(name, uid, password, role));
				System.out.println("Mahasiswa berhasil ditambahkan!");
			}
			return false;
		case 0:
			exit = true;
			return false;
		default:
			return false;
		}
	}
	
	private static boolean handleLogin(String uid, String password, char role) throws AuthenticationException {
		boolean userFound = false;
		
		try {
			for (User user : User.getUsers()) {			
				if (user.getUserID().substring(1).equals(uid) && user.getRole() == role) {
					userFound = true;
					
					if (user.doLogin(uid, password)) {
						currentUser = user;
						return true;
					}
				}
			}
		} catch (ExcessiveFailedLoginException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		
		if (!userFound) {
			throw new AuthenticationException("User tidak ditemukan!");
		}
		
		return false;
	}
	
	protected static void clearScanner() {
		s.nextLine();
	}

}
