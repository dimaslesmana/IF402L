package week07.dimas.id.ac.umn;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Item> listOfItems = new ArrayList<Item>();
	static ArrayList<Payment> listOfPayments = new ArrayList<Payment>();
	static Scanner s = new Scanner(System.in);
	
	public static void seedData() {
		listOfItems.add(new Item("Kulkas", "Electronic", 4800000));
		listOfItems.add(new Item("TV", "Electronic", 1280000));
		listOfItems.add(new Item("Laptop", "Computer", 6000000));
		listOfItems.add(new Item("PC", "Computer", 12000000));
	}
	
	public static void printItem(Item item) {
		System.out.println("Nama  : " + item.getName());
		System.out.println("Tipe  : " + item.getType());
		System.out.println("Harga : " + item.getPrice());
	}
	
	public static void main(String[] args) {
		int opt = 0;
		int id = 0;
		int jumlahBayar = 0;
		
		seedData();
		
		do {
			System.out.println();
			System.out.println("----Program Toko Elektronik----");
			System.out.println("1. Pesan Barang");
			System.out.println("2. Lihat Pesanan");
			System.out.println("0. Keluar");
			System.out.print("Pilihan : ");
			opt = s.nextInt();
			
			if (opt == 1) {
				Payment payment;
				Item currentItem;
				String kodeBayar;
				int tipePembayaran = 0;
				
				System.out.println("----Daftar Barang----");
				
				for (int i = 0; i < listOfItems.size(); i++) {
					System.out.println("No    : " + (i+1));
					printItem(listOfItems.get(i));
					System.out.println("------------------------------");
				}
				
				System.out.print("Pilih : ");
				id = s.nextInt();
				currentItem = listOfItems.get(id-1);
				
				printItem(currentItem);
				
				System.out.println("----Tipe Pembayaran----");
				System.out.println("1. Cash");
				System.out.println("2. Credit");
				System.out.print("Pilih : ");
				tipePembayaran = s.nextInt();
				s.nextLine();
				
				switch (tipePembayaran) {
				case 1:
					payment = new Cash(currentItem);
					
					System.out.print("Bayar (Y/N): ");
					kodeBayar = s.nextLine();
					
					if (kodeBayar.equalsIgnoreCase("Y")) {
						System.out.println("Harga Pembayaran : " + payment.pay(false));
						System.out.print("Bayar : ");
						jumlahBayar = s.nextInt();
						
						if (jumlahBayar >= payment.pay(false)) {
							payment.pay(true);
							listOfPayments.add(payment);
							System.out.println("Transaksi telah dibayar lunas");
						}
						
					} else if (kodeBayar.equalsIgnoreCase("N")) {
						listOfPayments.add(payment);
						System.out.println("Transaksi telah disimpan");
					}
					break;
				case 2:
					int lamaCicilan = 0;
					
					while (true) {
						System.out.print("Lama Cicilan (3/6/9/12): ");
						lamaCicilan = s.nextInt();
						
						if (lamaCicilan == 3 || lamaCicilan == 6 || lamaCicilan == 9 || lamaCicilan == 12) {
							break;
						}
					}
					
					payment = new Credit(currentItem, lamaCicilan);
					listOfPayments.add(payment);
					
					System.out.println("Harga Pembayaran : " + payment.pay(false));
					System.out.print("Bayar : ");
					jumlahBayar = s.nextInt();
					
					if (jumlahBayar >= payment.pay(false)) {
						payment.pay(true);
						System.out.println("Transaksi telah dibayar");
					}
					break;
				default:
					break;
				}
			} else if (opt == 2) {
				for (int i = 0; i < listOfPayments.size(); i++) {
					Payment p = listOfPayments.get(i);
					System.out.println("No              : " + (i+1));
					System.out.println("Nama            : " + p.getItemName());
					System.out.println("Tipe            : " + p.getItem().getType());
					System.out.println("Status          : " + p.getStatus());
					System.out.println("Sisa Pembayaran : " + p.getRemainingAmount());
					System.out.println("------------------------------");
				}
				
				System.out.print("Pilih No Transaksi : ");
				id = s.nextInt();
				s.nextLine();
				Payment currentTransaction = listOfPayments.get(id-1);
				
				System.out.println("Nama             : " + currentTransaction.getItemName());
				System.out.println("Tipe             : " + currentTransaction.getItem().getType());
				System.out.println("Status           : " + currentTransaction.getStatus());
				System.out.println("Sisa Pembayaran  : " + currentTransaction.getRemainingAmount());
				System.out.println("Harga Pembayaran : " + currentTransaction.pay(false));
				
				if (currentTransaction.getPaidOff()) {
					System.out.println("Transaksi telah lunas");
					continue;
				}
				
				switch (currentTransaction.getClassName()) {
				case "CASH":
					do {
						System.out.print("Bayar : ");
						jumlahBayar = s.nextInt();
					} while (jumlahBayar < currentTransaction.getRemainingAmount());
					currentTransaction.pay(true);
					System.out.println("Transaksi telah dibayar lunas");
					break;
				case "CREDIT":
					System.out.print("Bayar : ");
					jumlahBayar = s.nextInt();
					
					if (jumlahBayar >= currentTransaction.pay(false)) {
						currentTransaction.pay(true);
						System.out.println("Transaksi telah dibayar");
					}
					break;
				}
			} else {
				System.out.println("Salah Input");
			}
		} while (opt != 0);
		
		s.close();
	}

}
