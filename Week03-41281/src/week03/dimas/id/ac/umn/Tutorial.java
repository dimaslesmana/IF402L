package week03.dimas.id.ac.umn;
import java.util.Scanner;

public class Tutorial {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String matkulIF[] = {
				"Matematika Diskrit",
				"Dasar-dasar pemrograman",
				"Pemrograman Berorientasi Objek"
		};
		
		String matkulCE[] = {
				"Riset Operasi",
				"Jaringan Komputer",
				"Aljabar Linear"
		};
		
		String matkulIS[] = {
				"Sistem Database",
				"Administrasi Database"
		};
		
		System.out.println("Pilih kategori mata kuliah :");
		System.out.println("1. Informatics\n2. Computer Engineering\n3. Information System");
		System.out.print("Pilih : ");
		int input = scanner.nextInt();
		
		switch (input) {
			case 1:
				System.out.println("Daftar mata kuliah IF : ");
				show(matkulIF);
				break;
			case 2:
				System.out.println("Daftar mata kuliah CE : ");
				show(matkulCE);
				break;
			case 3:
				System.out.println("Daftar mata kuliah IS : ");
				show(matkulIS);
				break;
			default:
				System.out.println("Pilihan tidak valid");
		}
		
		scanner.close();
	}
	
	public static void show(String matkuls[]) {
		for (int i = 0; i < matkuls.length; i++) {
			System.out.println("- " + matkuls[i]);
		}
	}

}
