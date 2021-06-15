package week04.dimas.id.ac.umn;
import java.util.Scanner;

public class Tugas1 {
	
	private static String namaToko = "Toko Multiguna";
	static Barang[] listBarang = new Barang[5];
	static Order[] orders = new Order[50];
	
	private static void seedBarang() {
		listBarang[0] = new Barang(1, "Pulpen Easy Gel 0.5mm", 120, 2000);
		listBarang[1] = new Barang(2, "Penggaris 30cm", 30, 5000);
		listBarang[2] = new Barang(3, "Tipe-x Roller", 30, 7000);
		listBarang[3] = new Barang(4, "Pensil Mekanik", 50, 5000);
		listBarang[4] = new Barang(5, "Buku Tulis", 100, 6000);
	}
	
	private static void seedOrder(int id, Barang barang, int qty) {
		orders[id] = new Order(id+1, barang, qty);
	}
	
	private static void mainMenu() {
		System.out.println("------------Menu " + namaToko + "------------");
		System.out.println("1. Pesan Barang");
		System.out.println("2. Lihat Pesanan");
	}
	
	private static void showListBarang() {
		System.out.println("Daftar Barang " + namaToko);
		for (Barang barang : listBarang) {
			System.out.println("ID    : " + barang.getID());
			System.out.println("Nama  : " + barang.getNama());
			System.out.println("Stock : " + barang.getStock());
			System.out.println("Harga : " + barang.getHarga());
			System.out.println("--------------------------------------------");
		}
	}
	
	private static boolean isIdValid(int id) {
		for (Barang barang : listBarang) {
			if (id == barang.getID()) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean hasEnoughStock(int id, int qty) {
		for (Barang barang : listBarang) {
			if (id == barang.getID()) {
				if (qty > 0 && qty < barang.getStock()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static int placeOrder(int id, int qty) {
		for (Barang barang : listBarang) {
			if (id == barang.getID()) {
				int totalHarga = qty * barang.getHarga();
				
				barang.minusStock(qty);
				seedOrder(Order.total++, barang, qty);
				System.out.println(qty + " @ "+ barang.getNama() + " dengan total harga " + totalHarga);
				
				return totalHarga;
			}
		}
		return 0;
	}
	
	private static void ordersList() {
		System.out.println("Daftar Pesanan " + namaToko);
		
		for (int i = 0; i < Order.total; i++) {
			System.out.println("ID : " + orders[i].getID());
			System.out.println("Nama : " + orders[i].getBarang().getNama());
			System.out.println("Jumlah : " + orders[i].getJumlah());
			System.out.println("Total : " + orders[i].getBarang().getHarga() * orders[i].getJumlah());
			System.out.println("--------------------------------------------");
        }
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int id, qty, uang, totalHarga;
		
		seedBarang();
		
		while (true) {
			mainMenu();
			System.out.print("Pilihan : ");
			int choice = in.nextInt();
			
			switch (choice) {
				case 1:
					showListBarang();
					System.out.println("Ketik 0 untuk batal");
					System.out.print("Pesan barang (ID) : ");
					id = in.nextInt();

					if (!isIdValid(id)) {
						break;
					}
					
					do {
						System.out.print("Masukkan Jumlah : ");
						qty = in.nextInt();				
					} while (!hasEnoughStock(id, qty));
					
					totalHarga= placeOrder(id, qty);
					
					System.out.print("Masukkan jumlah uang : ");
					uang = in.nextInt();

					if (uang >= totalHarga) {
						System.out.println("Berhasil dipesan");
					} else {
						System.out.println("Jumlah uang tidak mencukupi");
					}
					break;
				case 2:
					ordersList();
					break;
				default:
					System.out.println("Input tidak valid!");
			}
			
			System.out.println();
		}
	}

}
