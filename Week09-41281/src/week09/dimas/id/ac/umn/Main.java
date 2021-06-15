package week09.dimas.id.ac.umn;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
	private static Scanner s = new Scanner(System.in);
	private static List<Karyawan> employees = new ArrayList<Karyawan>();

	public static void main(String[] args) {
		int choice;
		
		do {
			mainMenu();
			System.out.print("Pilih : ");
			choice = s.nextInt();
			
			switch (choice) {
			case 1:
				lihatKaryawan();
				break;
			case 2:
				tambahKaryawan();
				break;
			case 3:
				break;
			default:
				System.out.println("Pilihan tidak valid!");
				break;
			}
			System.out.println();
		}  while (choice != 3);
	}

	private static void mainMenu() {
		System.out.println("-----Program Data Karyawan-----");
		System.out.println("1. Lihat Karyawan");
		System.out.println("2. Tambah Karyawan");
		System.out.println("3. Keluar");
	}
	
	private static boolean isEmployeeExists(String type) {
		for (Karyawan employee : employees) {
			if (employee instanceof Manager && type.equalsIgnoreCase("manager")) {
				return true;
			} else if (employee instanceof Tetap && type.equalsIgnoreCase("tetap")) {
				return true;
			} else if (employee instanceof Magang && type.equalsIgnoreCase("magang")) {
				return true;
			}
		}
		
		return false;
	}
	
	private static String formatCurrency(int amount) {
		return NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(amount);
	}
	
	private static void lihatManager() {
		System.out.println("-----Daftar Manajer-----");
		
		if (!isEmployeeExists("manager")) {
			System.out.println("Tidak ada");
			return;
		}
		
		for (Karyawan employee : employees) {
			if (employee instanceof Manager) {
				Manager manager = (Manager)employee;
				System.out.println("ID   : " + manager.getId());
				System.out.println("Nama : " + manager.getNama());
				System.out.println("Gaji : " + formatCurrency(manager.getGaji()));
			}
		}
		System.out.println("--------------------");
	}
	
	private static void lihatTetap() {
		System.out.println("-----Daftar Pegawai Tetap-----");
		
		if (!isEmployeeExists("tetap")) {
			System.out.println("Tidak ada");
			return;
		}
		
		for (Karyawan employee : employees) {
			if (employee instanceof Tetap) {
				Tetap tetap = (Tetap)employee;
				System.out.println("ID   : " + tetap.getId());
				System.out.println("Nama : " + tetap.getNama());
				System.out.println("Gaji : " + formatCurrency(tetap.getGaji()));
			}
		}
		System.out.println("--------------------");
	}
	
	private static void lihatMagang() {
		System.out.println("-----Daftar Pegawai Magang-----");
		
		if (!isEmployeeExists("magang")) {
			System.out.println("Tidak ada");
			return;
		}
		
		for (Karyawan employee : employees) {
			if (employee instanceof Magang) {
				Magang magang = (Magang)employee;
				System.out.println("ID   : " + magang.getId());
				System.out.println("Nama : " + magang.getNama());
				System.out.println("Gaji : " + formatCurrency(magang.getGaji()));
			}
		}
		System.out.println("--------------------");
	}
	
	private static void lihatKaryawan() {
		lihatManager();
		lihatTetap();
		lihatMagang();
	}
	
	private static void tambahKaryawan() {
		int choice;
		String nama;
		String id;
		
		System.out.println();
		System.out.println("-----Tambah Karyawan-----");
		System.out.println("1. Manajer");
		System.out.println("2. Karyawan Tetap");
		System.out.println("3. Karyawan Magang");
		
		do {
			System.out.print("Pilih : ");
			choice = s.nextInt();
		} while (choice < 1 || choice > 3);
		s.nextLine();
		
		System.out.print("Nama : ");
		nama = s.nextLine();
		
		if (nama.trim().isEmpty()) {
			System.out.println("Input tidak valid!");
			return;
		}
		
		switch (choice) {
		case 1:
			id = String.format("M%s", countIdByType("manager"));
			employees.add(new Manager(id, nama));
			System.out.println("Manajer baru telah ditambahkan");
			break;
		case 2:
			id = String.format("R%s", countIdByType("tetap"));
			employees.add(new Tetap(id, nama));
			System.out.println("Pegawai tetap baru telah ditambahkan");
			break;
		case 3:
			id = String.format("I%s", countIdByType("magang"));
			employees.add(new Magang(id, nama));
			System.out.println("Pegawai magang baru telah ditambahkan");
			break;
		}
	}
	
	private static int countIdByType(String type) {
		int count = 0;
		
		for (Karyawan employee : employees) {
			if (employee instanceof Manager && type.equalsIgnoreCase("manager")) {
				count++;
			} else if (employee instanceof Tetap && type.equalsIgnoreCase("tetap")) {
				count++;
			} else if (employee instanceof Magang && type.equalsIgnoreCase("magang")) {
				count++;
			}
		}
		
		return count + 1;
	}
}
