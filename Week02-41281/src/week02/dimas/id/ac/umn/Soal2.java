package week02.dimas.id.ac.umn;
import java.util.Scanner;

public class Soal2 {

	public static void main(String[] args) {
		int input;
		Scanner scanner = new Scanner(System.in);
		
		do {			
			System.out.print("Masukkan angka : ");
			input = scanner.nextInt();
		} while (input < 0);
		
		if (isPrime(input)) {
			System.out.println("Angka " + input + " adalah angka prima");
		} else {
			System.out.println("Angka " + input + " adalah bukan angka prima");
		}
		
		scanner.close();
	}
	
	private static boolean isPrime(int num) {
		if (num == 1 || num == 0) {
			return false;			
		}
		
		for (int i = 2; i <= num/2; ++i) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
