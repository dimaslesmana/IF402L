package uaslab.dimas.id.ac.umn;

import java.util.*;

public class Dosen extends User {

	public Dosen() {}
	public Dosen(String name, String userID, String password, char role) {
		super(name, userID, password, role);
	}
	
	@Override
	public String getUserID() {
		return String.format("D%s", super.getUserID());
	}
	
	@Override
	public void showUserDetails() {
		System.out.println("-----Info Dosen---");
		System.out.println("Nama     : " + this.getName());
		System.out.println("User ID  : " + this.getUserID());
	}
	
	@Override
	public boolean showMenu() {
		int choice;
		
		System.out.println("-----Menu Dosen-----");
		System.out.println("1. Lihat semua logs Mahasiswa");
		System.out.println("2. Credits");
		System.out.println("0. Logout");
		System.out.print("Pilihan : ");
		choice = Main.s.nextInt();
		Main.clearScanner();
		
		switch (choice) {
		case 1:
			viewAllLogs();
			return true;
		case 2:
			Helpers.menuCredits();
			return true;
		case 0:
			return false;
		default:
			System.out.println("Pilihan tidak sesuai!");
			return true;
		}
	}
	
	private void viewAllLogs() {
		int choice;
		ArrayList<Mahasiswa> students = new ArrayList<Mahasiswa>();
		
		for (User user : User.getUsers()) {
			if (user instanceof Mahasiswa) {
				students.add((Mahasiswa) user);
			}
		}
		
		while (true) {
			System.out.println("-----List Mahasiswa-----");
			
			if (User.getUsers().isEmpty()) {
				System.out.println("Belum ada daftar mahasiswa");
				break;
			}
			
			for (int i = 0; i < students.size(); i++) {
				Mahasiswa mahasiswa = students.get(i);
				
				System.out.println(String.format("%d. %s", i+1, mahasiswa.getName()));
			}
			
			if (students.isEmpty()) {
				System.out.println("Belum ada daftar mahasiswa");
				break;
			}
			
			System.out.println("----------------");
			System.out.println("0. Kembali");
			
			try {
				System.out.print("Pilihan : ");
				choice = Main.s.nextInt();
				Main.clearScanner();
				
				if (choice == 0) {
					break;
				}
				
				showMahasiswaLogs(students.get(choice-1));
			} catch (IndexOutOfBoundsException  ex) {
				System.out.println("------------------------------");
				System.out.println("Pilihan melebihi jumlah list mahasiswa!");
				System.out.println("------------------------------");
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				break;
			}
		}
		
		System.out.println("------------------------------");
	}
	
	private void showMahasiswaLogs(Mahasiswa mahasiswa) {
		boolean loopMenu = true;
		int choice;
		
		while (loopMenu) {
			System.out.println("-----Pilih Jenis Kegiatan-----");
			System.out.println("1. Magang");
			System.out.println("2. Pertukaran Pelajar");
			System.out.println("0. Kembali");
			System.out.print("Pilihan : ");
			choice = Main.s.nextInt();
			Main.clearScanner();
			
			switch (choice) {
			case 1:
				mahasiswa.viewMagangLogs(mahasiswa);
				break;
			case 2:
				mahasiswa.viewPPLogs(mahasiswa);
				break;
			case 0:
				loopMenu = false;
				break;
			default:
				System.out.println("Pilihan tidak sesuai!");
				break;
			}
		}
	}

}
