package uaslab.dimas.id.ac.umn;

import java.util.*;

public class Mahasiswa extends User {
	public ArrayList<Magang> magangLogs = new ArrayList<Magang>();
	public ArrayList<PertukaranPelajar> ppLogs = new ArrayList<PertukaranPelajar>();
	
	public Mahasiswa() {}
	public Mahasiswa(String name, String userID, String password, char role) {
		super(name, userID, password, role);
	}
	
	public void insertIntoMagangLogs(Magang magang) {
		magangLogs.add(magang);
	}
	
	public void insertIntoPPLogs(PertukaranPelajar pp) {
		ppLogs.add(pp);
	}
	
	public ArrayList<Magang> getMagangLogs() {
		return this.magangLogs;
	}
	
	public ArrayList<PertukaranPelajar> getPPLogs() {
		return this.ppLogs;
	}
	
	@Override
	public String getUserID() {
		return String.format("M%s", super.getUserID());
	}
	
	@Override
	public void showUserDetails() {
		System.out.println("-----Info Mahasiswa---");
		System.out.println("Nama     : " + this.getName());
		System.out.println("User ID  : " + this.getUserID());
	}
	
	@Override
	public boolean showMenu() {
		int choice;
		
		System.out.println("-----Menu Mahasiswa-----");
		System.out.println("1. Lihat semua logs");
		System.out.println("2. Tambah log kegiatan");
		System.out.println("3. Credits");
		System.out.println("0. Logout");
		System.out.print("Pilihan : ");
		choice = Main.s.nextInt();
		Main.clearScanner();
		
		switch (choice) {
		case 1:
			viewAllLogs();
			return true;
		case 2:
			addLogsActivity();
			return true;
		case 3:
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
		boolean loopMenu = true;
		
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
				this.viewMagangLogs(this);
				break;
			case 2:
				this.viewPPLogs(this);
				break;
			case 0:
				loopMenu = false;
				break;
			default:
				System.out.println("Pilihan tidak sesuai!");
				break;
			}
		}
		
		System.out.println("------------------------------");
	}
	
	private void addLogsActivity() {
		int choice;
		String name;
		String kegiatanType;
		String date;
		boolean loopMenu = true;
		
		while (loopMenu) {
			System.out.println("-----Tambah Logs Kegiatan-----");
			System.out.println("1. Magang");
			System.out.println("2. Pertukaran Pelajar");
			System.out.println("0. Kembali");
			System.out.print("Pilihan : ");
			choice = Main.s.nextInt();
			Main.clearScanner();
			
			switch (choice) {
			case 1:
				String companyName;
				System.out.println("-----Masukkan detail kegiatan Magang-----");
				System.out.print("Nama                 : ");
				name = Main.s.nextLine();
				
				System.out.print("Tipe Kegiatan        : ");
				kegiatanType = Main.s.nextLine();
				
				System.out.print("Nama Perusahaan      : ");
				companyName = Main.s.nextLine();
				
				do {
					System.out.print("Tanggal (DD/MM/YYYY) : ");
					date = Main.s.nextLine();
					
					if (date.trim().isEmpty()) {
						date = Helpers.getCurrentDate();
					}
				} while (!Helpers.isDateValid(date));
				
				insertIntoMagangLogs(new Magang(this.getUserID(), name, date, kegiatanType, companyName));
				System.out.println("Log magang baru berhasil ditambahkan!");
				break;
			case 2:
				String kampus;
				String matkul;
				
				System.out.println("-----Masukkan detail Pertukaran Pelajar-----");
				System.out.print("Nama                 : ");
				name = Main.s.nextLine();
				
				System.out.print("Tipe Kegiatan        : ");
				kegiatanType = Main.s.nextLine();
				
				System.out.print("Kampus               : ");
				kampus = Main.s.nextLine();
				
				System.out.print("Matkul               : ");
				matkul = Main.s.nextLine();
				
				do {
					System.out.print("Tanggal (DD/MM/YYYY) : ");
					date = Main.s.nextLine();
					
					if (date.trim().isEmpty()) {
						date = Helpers.getCurrentDate();
					}
				} while (!Helpers.isDateValid(date));
				
				insertIntoPPLogs(new PertukaranPelajar(this.getUserID(), name, date, kegiatanType, kampus, matkul));
				System.out.println("Log pertukaran pelajar baru berhasil ditambahkan!");
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
	
	public void viewMagangLogs(Mahasiswa mahasiswa) {
		String date;
		boolean logsFound = false;
		
		do {
			System.out.print("Masukkan waktu kegiatan (DD/MM/YYYY) *jika ada : ");
			date = Main.s.nextLine();
			
			if (date.trim().isEmpty()) {
				date = Helpers.getCurrentDate();
			}
		} while (!Helpers.isDateValid(date));
		
		System.out.println("-----Daftar Logs Magang-----");
		if (magangLogs.isEmpty()) {
			System.out.println("Logbook masih kosong!");
			return;
		}
		
		for (Magang magang : magangLogs) {
			if (mahasiswa.getUserID().equals(magang.getUserID()) && magang.getDate().equals(date)) {
				logsFound = true;
				
				System.out.println("------------------------------");
				System.out.println("User ID         : " + magang.getUserID());
				System.out.println("Nama            : " + magang.getName());
				System.out.println("Tipe Kegiatan   : " + magang.getKegiatanType());
				System.out.println("Nama Perusahaan : " + magang.getCompanyName());
				System.out.println("Tanggal         : " + magang.getDate());
			}
		}
		
		if (!logsFound) {
			System.out.println("Logbook masih kosong!");
			return;
		}
	}
	
	public void viewPPLogs(Mahasiswa mahasiswa) {
		String date;
		boolean logsFound = false;
		
		do {
			System.out.print("Masukkan waktu kegiatan (DD/MM/YYYY) *jika ada : ");
			date = Main.s.nextLine();
			
			if (date.trim().isEmpty()) {
				date = Helpers.getCurrentDate();
			}
		} while (!Helpers.isDateValid(date));
		
		System.out.println("-----Daftar Logs Pertukaran Pelajar-----");
		
		if (ppLogs.isEmpty()) {
			System.out.println("Logbook masih kosong!");
			return;
		}
		
		for (PertukaranPelajar pp : ppLogs) {
			if (mahasiswa.getUserID().equals(pp.getUserID()) && pp.getDate().equals(date)) {
				logsFound = true;
				
				System.out.println("------------------------------");
				System.out.println("User ID       : " + pp.getUserID());
				System.out.println("Nama          : " + pp.getName());
				System.out.println("Tipe Kegiatan : " + pp.getKegiatanType());
				System.out.println("Kampus        : " + pp.getKampus());
				System.out.println("Mata Kuliah   : " + pp.getMatkul());
				System.out.println("Tanggal       : " + pp.getDate());
			}
		}
		
		if (!logsFound) {
			System.out.println("Logbook masih kosong!");
			return;
		}
	}
	
}
