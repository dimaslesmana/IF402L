package week03.dimas.id.ac.umn;
import java.util.Scanner;

public class Tugas2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Scanner scannerInput = new Scanner(System.in);
		
		System.out.print("Masukkan Nama Anda : ");
		String name = s.nextLine();
		System.out.println("----------------------------------");
		
		System.out.println("String anda : " + name);
		menu();
		System.out.print("Pilih menu : ");
		int choice = s.nextInt(); 
		
		int inputInt = 0;
		String inputStr = new String();
		
		switch (choice) {
			case 1:
				System.out.println("-----charAt-----");
				System.out.println("Nama : " + name);
				System.out.print("Input : ");
				inputInt = scannerInput.nextInt();
				
				System.out.println("Hasil : " + name.charAt(inputInt));
				break;
			case 2:
				System.out.println("-----length-----");
				System.out.println("Nama : " + name);
				System.out.println("Hasil : " + name.length());
				break;
			case 3:
				System.out.println("-----substring(n)-----");
				System.out.println("Nama : " + name);
				System.out.print("Input : ");
				inputInt = scannerInput.nextInt();
				
				System.out.println("Hasil : " + name.substring(inputInt));
				break;
			case 4:
				System.out.println("-----substring(m,n)-----");
				System.out.println("Nama : " + name);
				System.out.print("Input mulai : ");
				int start = scannerInput.nextInt();
				
				System.out.print("Input akhir : ");
				int end = scannerInput.nextInt();
				
				System.out.println("Hasil : " + name.substring(start, end));
				break;
			case 5:
				System.out.println("-----contains-----");
				System.out.println("Nama : " + name);
				System.out.print("Input : ");
				inputStr = scannerInput.nextLine();
				
				System.out.println("Hasil : " + name.contains(inputStr));
				break;
			case 6:
				System.out.println("-----concat-----");
				System.out.println("Nama : " + name);
				System.out.print("Input : ");
				inputStr = scannerInput.nextLine();
				
				System.out.println("Hasil : " + name.concat(inputStr));
				break;
			case 7:
				System.out.println("-----replace-----");
				System.out.println("Nama : " + name);
				System.out.print("Input kata yang diganti : ");
				String searchChar = scannerInput.nextLine();
				
				System.out.print("Input kata pengganti : ");
				String newChar = scannerInput.nextLine();
				
				System.out.println("Hasil : " + name.replace(searchChar, newChar));
				break;
			case 8:
				System.out.println("-----split-----");
				System.out.println("Nama : " + name);
				System.out.print("Input : ");
				inputStr = scannerInput.nextLine();

				for (String result : name.split(inputStr)) {
					System.out.println("Hasil : " + result);
				}
				break;
			case 9:
				System.out.println("-----lowerCase-----");
				System.out.println("Nama : " + name);
				System.out.println("Hasil : " + name.toLowerCase());
				break;
			case 10:
				System.out.println("-----upperCase-----");
				System.out.println("Nama : " + name);
				System.out.println("Hasil : " + name.toUpperCase());
				break;
			default:
				System.out.println("Pilihan tidak valid");
		}
		
		s.close();
		scannerInput.close();
	}
	
	private static void menu() {
		final String format = "%-24s%s%n";
		
		System.out.format(format, "1. charAt", "2. length");
		System.out.format(format, "3. substring(n)", "4. substring(m,n)");
		System.out.format(format, "5. contains", "6. concat");
		System.out.format(format, "7. replace", "8. split");
		System.out.format(format, "9. lowerCase", "10. upperCase");
	}

}
