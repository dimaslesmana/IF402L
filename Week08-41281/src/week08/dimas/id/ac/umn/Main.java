package week08.dimas.id.ac.umn;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static String namaToko = "Toko Voucher & HP";
	private static List<Barang> listBarang = new ArrayList<Barang>();
	private static List<Order> listOrder = new ArrayList<Order>();
	
	private static void seedBarang() {
		listBarang.add((Barang)new Handphone("H01", "Samsung Note 9", 13000000, 10, "Hitam"));
		listBarang.add((Barang)new Handphone("H02", "Samsung S9+", 14499000, 10, "Hitam"));
		listBarang.add((Barang)new Handphone("H03", "iPhone X", 17999000, 10, "Hitam"));
		listBarang.add((Barang)new Voucher("V01", "Google Play", 20000, 100, 0.1));
		
		for (Barang barang : listBarang) {
			if (barang.getId().contains("H")) {
				Handphone.total++;
			} else {
				Voucher.total++;
			}
		}
	}
	
	private static boolean showListBarang() {
		if (!listBarang.isEmpty()) {
			for (Barang barang : listBarang) {
				if (barang.getId().contains("H")) {
					Handphone handphone = (Handphone)barang;
					System.out.println("ID      : " + handphone.getId());
					System.out.println("Nama    : " + handphone.getNama() + " " + handphone.getWarna());
					System.out.println("Stock   : " + handphone.getStok());
					System.out.println("Harga   : " + (long) handphone.getHarga());
					System.out.println("--------------------------------------------");
				} else {
					Voucher voucher = (Voucher)barang;
					System.out.println("ID      : " + voucher.getId());
					System.out.println("Nama    : " + voucher.getNama());
					System.out.println("Nominal : " + (long) voucher.getHarga());
					System.out.println("Stock   : " + voucher.getStok());
					System.out.println("Harga   : " + (long) voucher.getHargaJual());
					System.out.println("--------------------------------------------");
				}
			}
			
			return true;
		} else {
			System.out.println("Barang tidak tersedia");
			return false;
		}
	}
	
	private static Barang isBarangValid(String id) {
		for (Barang barang : listBarang) {
			if (barang.getId().equalsIgnoreCase(id)) {
				return barang;
			}
		}
		
		return null;
	}
	
	private static boolean hasEnoughStock(Barang barang, int qty) {
		if (qty > 0 && qty <= barang.getStok()) {
			return true;
		}
		
		return false;
	}
	
	private static void placeOrder(Barang barang, int qty, Scanner s) {
		long jumlahUang = 0;
		double totalHarga = 0.0;
		
		if (barang.getId().contains("H")) {
			Handphone handphone = (Handphone)barang;
			totalHarga = qty * handphone.getHarga();			
		} else {
			Voucher voucher = (Voucher)barang;
			totalHarga = qty * voucher.getHargaJual();
		}
		
		
		System.out.printf("%d @ %s dengan total harga %d%n", qty, barang.getNama(), (long)totalHarga);
		System.out.print("Masukkan jumlah uang : ");
		jumlahUang = s.nextLong();
		
		if (jumlahUang >= (long)totalHarga) {
			barang.minusStok(qty);
			String orderId = String.format("O%s-%d", barang.getId(), Order.total++);
			
			if (barang.getId().contains("H")) {
				listOrder.add(new Order(orderId, (Handphone)barang, qty));
			} else {
				listOrder.add(new Order(orderId, (Voucher)barang, qty));
			}
			System.out.println("Berhasil dipesan");
		} else {
			System.out.println("Jumlah uang tidak mencukupi");
		}
	}
	
	private static int mainMenu(Scanner s) {
		System.out.println("------------Menu " + namaToko + "------------");
		System.out.println("1. Pesan Barang");
		System.out.println("2. Lihat Pesanan");
		System.out.println("3. Barang Baru");
		System.out.print("Pilihan : ");
		
		return s.nextInt();
	}
	
	private static void menuPesanBarang(Scanner s) {
		String id;
		int qty = 0;
		Barang barang;
		
		System.out.println("Daftar Barang " + namaToko);
		
		if (!showListBarang()) {
			return;
		}
		
		System.out.println("Ketik 0 untuk batal");
		System.out.print("Pesan barang (ID) : ");
		id = s.nextLine();
		
		barang = isBarangValid(id);
		if (barang == null) {
			System.out.println("Barang tidak ditemukan");
			return;
		}

		System.out.print("Masukkan Jumlah : ");
		qty = s.nextInt();
		
		if (!hasEnoughStock(barang, qty)) {
			System.out.println("Stok tidak mencukupi");
			return;
		}
		
		placeOrder(barang, qty, s);
	}
	
	private static void menuLihatPesanan() {
		System.out.println("Daftar Pesanan " + namaToko);
		double totalHarga = 0.0;
		
		if (!listOrder.isEmpty()) {
			for (Order order : listOrder) {
				if (order.getHandphone() != null) {
					totalHarga = order.getJumlah() * order.getHandphone().getHarga();
					
					System.out.println("ID     : " + order.getId());
					System.out.println("Nama   : " + order.getHandphone().getNama() + " " + order.getHandphone().getWarna());
					System.out.println("Jumlah : " + order.getJumlah());
					System.out.println("Total  : " + (long) totalHarga);
				} else {
					totalHarga = order.getJumlah() * order.getVoucher().getHargaJual();
					
					System.out.println("ID     : " + order.getId());
					System.out.println("Nama   : " + order.getVoucher().getNama() + " " + (long) order.getVoucher().getHarga());
					System.out.println("Jumlah : " + order.getJumlah());
					System.out.println("Total  : " + (long) totalHarga);
				}
				System.out.println("--------------------------------------------");
			}
		} else {
			System.out.println("Belum ada pesanan yang dilakukan");
		}
	}
	
	private static void menuBarangBaru(Scanner s) {
		int stok = 0;
		double harga = 0.0;
		double pajak = 0.0;
		String tipe = new String();
		String nama = new String();
		String warna = new String();
		String id = new String();
		
		System.out.print("Voucher / Handphone (V/H) : ");
		tipe = s.nextLine();
		
		if (!tipe.equalsIgnoreCase("V") && !tipe.equalsIgnoreCase("H")) {
			System.out.println("Input tidak valid!");
			return;
		}
		
		System.out.print("Nama : ");
		nama = s.nextLine();
		
		if (nama.isEmpty()) {
			System.out.println("Input tidak valid!");
			return;
		}
		
		System.out.print("Harga : ");
		harga = s.nextDouble();
		
		if (harga <= 0) {
			System.out.println("Input tidak valid!");
			return;
		}
		
		System.out.print("Stok : ");
		stok = s.nextInt();
		
		if (stok < 0) {
			System.out.println("Input tidak valid!");
			return;
		}
		s.nextLine();
		
		if (tipe.equalsIgnoreCase("H")) {
			System.out.print("Warna : ");
			warna = s.nextLine();
			
			if (warna.isEmpty()) {
				System.out.println("Input tidak valid!");
				return;
			}
			
			id = String.format("H%02d", ++Handphone.total);
			listBarang.add((Barang)new Handphone(id, nama, harga, stok, warna));

			System.out.println("Handphone telah berhasil diinput");
		} else if (tipe.equalsIgnoreCase("V")) {
			System.out.print("PPN : ");
			pajak = s.nextDouble();
			
			if (pajak < 0) {
				System.out.println("Input tidak valid!");
				return;
			}
			
			id = String.format("V%02d", ++Voucher.total);
			listBarang.add((Barang)new Voucher(id, nama, harga, stok, pajak));
			
			System.out.println("Voucher telah berhasil diinput");
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		//seedBarang();
		
		while (true) {
			int choice = mainMenu(s);
			s.nextLine();
			
			switch (choice) {
			case 1:
				menuPesanBarang(s);
				break;
			case 2:
				menuLihatPesanan();
				break;
			case 3:
				menuBarangBaru(s);
				break;
			default:
				System.out.println("Input tidak valid!");
				break;
			}
			
			System.out.println();
		}

	}

}
