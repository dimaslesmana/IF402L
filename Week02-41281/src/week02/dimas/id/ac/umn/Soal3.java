package week02.dimas.id.ac.umn;
import java.util.Scanner;

public class Soal3 {

	public static void main(String[] args) {
		int min, max, result = 0;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Masukkan nilai terkecil : ");
		min = scanner.nextInt();
		
		System.out.print("Masukkan nilai terbesar : ");
		max = scanner.nextInt();
		
		if (min > max || min < 0 || max < 0) {
			System.out.println("Invalid Input!");
		} else {			
			for (int i = min; i <= max; i++) {
				if (isPrime(i)) {
					result += i;
				}
			}
			
			System.out.println("Jumlah dari semua nilai prima dari " + min + " sampai " + max + " adalah " + result);
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
