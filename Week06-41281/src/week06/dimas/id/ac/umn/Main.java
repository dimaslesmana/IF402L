package week06.dimas.id.ac.umn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private enum TipeBarang {
		HANDPHONE,
		VOUCHER
	}
	
	private static String namaToko = "Toko Voucher & HP";
	private static List<Handphone> listHandphone = new ArrayList<Handphone>();
	private static List<Voucher> listVoucher = new ArrayList<Voucher>();
	private static List<Order> listOrder = new ArrayList<Order>();
	
	private static void seedBarang() {
		listHandphone.add(new Handphone(1, "Samsung S9+", 14499000, 10, "Hitam"));
		listHandphone.add(new Handphone(2, "iPhone X", 17999000, 10, "Hitam"));
		listVoucher.add(new Voucher(1, "Google Play", 20000, 100, 0.1));
	}
	
	private static boolean showListHandphone() {
		if (!listHandphone.isEmpty()) {
			for (Handphone handphone : listHandphone) {
				System.out.println("ID    : " + handphone.getId());
				System.out.println("Nama  : " + handphone.getNama() + " " + handphone.getWarna());
				System.out.println("Stock : " + handphone.getStok());
				System.out.println("Harga : " + (long) handphone.getHarga());
				System.out.println("--------------------------------------------");
			}
			
			return true;
		} else {
			System.out.println("Barang tidak tersedia");
			return false;
		}
	}
	
	private static boolean showListVoucher() {
		if (!listVoucher.isEmpty()) {
			for (Voucher voucher : listVoucher) {
				System.out.println("ID      : " + voucher.getId());
				System.out.println("Nama    : " + voucher.getNama());
				System.out.println("Nominal : " + (long) voucher.getHarga());
				System.out.println("Stock   : " + voucher.getStok());
				System.out.println("Harga   : " + (long) voucher.gethargaJual());
				System.out.println("--------------------------------------------");
			}
			
			return true;
		} else {
			System.out.println("Barang tidak tersedia");
			return false;
		}
	}
	
	private static boolean isIdValid(int id, TipeBarang tipeBarang) {
		switch (tipeBarang) {
		case HANDPHONE:
			for (Handphone handphone : listHandphone) {
				if (id == handphone.getId()) {
					return true;
				}
			}
			break;
		case VOUCHER:
			for (Voucher voucher : listVoucher) {
				if (id == voucher.getId()) {
					return true;
				}
			}
			break;
		}
		
		return false;
	}
	
	private static boolean hasEnoughStock(int id, int qty, TipeBarang tipeBarang) {
		switch (tipeBarang) {
		case HANDPHONE:
			for (Handphone handphone : listHandphone) {
				if ((id == handphone.getId())
						&& (qty > 0)
						&& (qty < handphone.getStok())) {
					return true;
				}
			}
			break;
		case VOUCHER:
			for (Voucher voucher : listVoucher) {
				if ((id == voucher.getId())
						&& (qty > 0)
						&& (qty < voucher.getStok())) {
					return true;
				}
			}
			break;
		}
		
		return false;
	}
	
	private static void placeOrder(int id, int qty, TipeBarang tipeBarang, Scanner s) {
		long jumlahUang = 0;
		double totalHarga = 0.0;
		String message = new String();
		
		switch (tipeBarang) {
		case HANDPHONE:
			for (Handphone handphone : listHandphone) {
				if (id == handphone.getId())  {
					totalHarga = qty * handphone.getHarga();
					
					message = String.format("%d @ %s dengan total harga %d", qty, handphone.getNama(), (long) totalHarga);
					System.out.println(message);
					
					System.out.print("Masukkan jumlah uang : ");
					jumlahUang = s.nextLong();
					
					if (jumlahUang >= (long) totalHarga) {
						handphone.minusStok(qty);
						listOrder.add(new Order("OH" + Order.total++, handphone, qty));
						System.out.println("Berhasil dipesan");
					} else {
						System.out.println("Jumlah uang tidak mencukupi");
					}
				}
			}
			break;
		case VOUCHER:
			for (Voucher voucher : listVoucher) {
				if (id == voucher.getId())  {
					totalHarga = qty * voucher.gethargaJual();
					
					message = String.format("%d @ %s %d dengan harga %d", qty, voucher.getNama(), (long) voucher.getHarga(), (long) totalHarga);
					System.out.println(message);
					
					System.out.print("Masukkan jumlah uang : ");
					jumlahUang = s.nextLong();
					
					if (jumlahUang >= (long) totalHarga) {
						voucher.minusStok(qty);
						listOrder.add(new Order("OV" + Order.total++, voucher, qty));
						System.out.println("Berhasil dipesan");
					} else {
						System.out.println("Jumlah uang tidak mencukupi");
					}
				}
			}
			break;
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
		int choice = 0;
		int id = 0;
		int qty = 0;
		
		System.out.println("Daftar Barang " + namaToko);
		System.out.println("1. Handphone");
		System.out.println("2. Voucher");
		System.out.print("Pilihan : ");
		choice = s.nextInt();
		s.nextLine();
		
		switch (choice) {
		case 1:
			if (!showListHandphone())  {
				break;
			}
			
			System.out.println("Ketik 0 untuk batal");
			System.out.print("Pesan barang (ID) : ");
			id = s.nextInt();
				
			if (!isIdValid(id, TipeBarang.HANDPHONE)) {
				break;
			}
			
			System.out.print("Masukkan Jumlah : ");
			qty = s.nextInt();
			
			if (!hasEnoughStock(id, qty, TipeBarang.HANDPHONE)) {
				System.out.println("Stok tidak mencukupi");
				break;
			}
			
			placeOrder(id, qty, TipeBarang.HANDPHONE, s);
			break;
		case 2:
			if (!showListVoucher()) {
				break;
			}
			
			System.out.println("Ketik 0 untuk batal");
			System.out.print("Pesan barang (ID) : ");
			id = s.nextInt();
				
			if (!isIdValid(id, TipeBarang.VOUCHER)) {
				break;
			}
			
			System.out.print("Masukkan Jumlah : ");
			qty = s.nextInt();
				
			if (!hasEnoughStock(id, qty, TipeBarang.VOUCHER)) {
				System.out.println("Stok tidak mencukupi");
				break;
			}
			
			placeOrder(id, qty, TipeBarang.VOUCHER, s);
			break;
		default:
			System.out.println("Input tidak valid!");
			break;
		}
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
					totalHarga = order.getJumlah() * order.getVoucher().gethargaJual();
					
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
		
		if (tipe.equalsIgnoreCase("V")) {
			System.out.print("PPN : ");
			pajak = s.nextDouble();
			
			if (pajak < 0) {
				System.out.println("Input tidak valid!");
				return;
			}
			
			listVoucher.add(new Voucher(++Voucher.total, nama, harga, stok, pajak));
			System.out.println("Voucher telah berhasil diinput");
		} else if (tipe.equalsIgnoreCase("H")) {
			System.out.print("Warna : ");
			warna = s.nextLine();
			
			if (warna.isEmpty()) {
				System.out.println("Input tidak valid!");
				return;
			}
			
			listHandphone.add(new Handphone(++Handphone.total, nama, harga, stok, warna));
			System.out.println("Handphone telah berhasil diinput");
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		// seedBarang();
		
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
