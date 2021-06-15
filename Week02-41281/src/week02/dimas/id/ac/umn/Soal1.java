package week02.dimas.id.ac.umn;
import java.util.Scanner;
import java.time.*;

public class Soal1 {

	public static void main(String[] args) {
		int month, days;
		Scanner scanner = new Scanner(System.in);
		LocalDate currentDate = LocalDate.now();
		
		do {
			System.out.print("Masukkan bulan (1-12) : ");
			month = scanner.nextInt();			
		} while (month <= 0 || month > 12);
		
		switch (month) {
			case 1:
				days = Month.JANUARY.maxLength();
				System.out.println("Bulan Januari memiliki " + days + " hari");
				break;
			case 2:
				boolean leapYear = currentDate.isLeapYear();
				days = Month.FEBRUARY.length(leapYear);
				System.out.println("Bulan Februari memiliki " + days + " hari");
				break;
			case 3:
				days = Month.MARCH.maxLength();
				System.out.println("Bulan Maret memiliki " + days + " hari");
				break;
			case 4:
				days = Month.APRIL.maxLength();
				System.out.println("Bulan April memiliki " + days + " hari");
				break;
			case 5:
				days = Month.MAY.maxLength();
				System.out.println("Bulan Mei memiliki " + days + " hari");
				break;
			case 6:
				days = Month.JUNE.maxLength();
				System.out.println("Bulan Juni memiliki " + days + " hari");
				break;
			case 7:
				days = Month.JULY.maxLength();
				System.out.println("Bulan Juli memiliki " + days + " hari");
				break;
			case 8:
				days = Month.AUGUST.maxLength();
				System.out.println("Bulan Agustus memiliki " + days + " hari");
				break;
			case 9:
				days = Month.SEPTEMBER.maxLength();
				System.out.println("Bulan September memiliki " + days + " hari");
				break;
			case 10:
				days = Month.OCTOBER.maxLength();
				System.out.println("Bulan Oktober memiliki " + days + " hari");
				break;
			case 11:
				days = Month.NOVEMBER.maxLength();
				System.out.println("Bulan November memiliki " + days + " hari");
				break;
			case 12:
				days = Month.DECEMBER.maxLength();
				System.out.println("Bulan Desember memiliki " + days + " hari");
				break;
			default:
				System.out.println("Pilihan tidak valid!");
				break;
		}
		
		scanner.close();
	}

}
