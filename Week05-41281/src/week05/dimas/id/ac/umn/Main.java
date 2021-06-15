package week05.dimas.id.ac.umn;
import java.util.Scanner;

public class Main {

	private static void calcCircle(Scanner s) {
		System.out.println("-----------Lingkaran-----------");
		
		System.out.print("Masukkan jari-jari : ");
		double radius = s.nextDouble();
		
		Circle circle = new Circle(radius, "Biru");
		
		System.out.println("Warna              : " + circle.getColor());
		System.out.println("Jari-jari          : " + circle.getRadius());
		System.out.println("Keliling Lingkaran : " + circle.getPerimeter());
		System.out.println("Luas Lingkaran     : " + circle.getArea());
	}
	
	private static void calcSquare(Scanner s) {
		System.out.println("-----------Persegi-----------");
		
		System.out.print("Masukkan panjang sisi : ");
		double side = s.nextDouble();
		
		Square square = new Square(side, "Merah");
		
		System.out.println("Warna            : " + square.getColor());
		System.out.println("Panjang Sisi     : " + square.getSideLength());
		System.out.println("Keliling Persegi : " + square.getPerimeter());
		System.out.println("Luas Persegi     : " + square.getArea());
	}
	
	private static void calcRectangle(Scanner s) {
		System.out.println("-----------Persegi Panjang-----------");
		
		System.out.print("Masukkan panjang : ");
		double length = s.nextDouble();
		System.out.print("Masukkan lebar : ");
		double width = s.nextDouble();
		
		Rectangle rectangle = new Rectangle(length, width, "Ungu");
		
		System.out.println("Warna                    : " + rectangle.getColor());
		System.out.println("Panjang & Lebar          : " + rectangle.getLength() + " & " + rectangle.getWidth());
		System.out.println("Keliling Persegi Panjang : " + rectangle.getPerimeter());
		System.out.println("Luas Persegi Panjang     : " + rectangle.getArea());
	}
	
	private static void calcTriangle(Scanner s) {
		System.out.println("-----------Segitiga Siku-Siku-----------");
		
		System.out.print("Masukkan alas : ");
		double base = s.nextDouble();
		System.out.print("Masukkan tinggi : ");
		double height = s.nextDouble();
		
		Triangle triangle = new Triangle(base, height, "Hitam");
		
		System.out.println("Warna             : " + triangle.getColor());
		System.out.println("Alas & Tinggi     : " + triangle.getBase() + " & " + triangle.getHeight());
		System.out.println("Keliling Segitiga : " + triangle.getPerimeter());
		System.out.println("Luas Segitiga     : " + triangle.getArea());
	}
	
	private static void mainMenu() {
		System.out.println("--------Program Menghitung Bangun Ruang--------");
		System.out.println("1. Lingkaran");
		System.out.println("2. Persegi");
		System.out.println("3. Persegi Panjang");
		System.out.println("4. Segitiga");
		System.out.println("5. Keluar");
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean showMenu = true;
		
		while (showMenu) {
			mainMenu();
			System.out.print("Pilihan : ");
			int choice = s.nextInt();
			
			switch (choice) {
				case 1:
					calcCircle(s);
					break;
				case 2:
					calcSquare(s);
					break;
				case 3:
					calcRectangle(s);
					break;
				case 4:
					calcTriangle(s);
					break;
				case 5:
					showMenu = false;
					System.out.println("Keluar program...");
					break;
				default:
					System.out.println("Input tidak valid!");
			}
			
			System.out.println();
		}
		
		s.close();
	}

}
