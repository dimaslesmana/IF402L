package model;

public class Handphone extends Barang {
	public static int total;
	private String warna;
	
	public Handphone(String id, String nama, double harga, int stok, String warna) {
		super(id, nama, harga, stok);
		this.warna = warna;
	}
	
	public String getWarna() {
		return this.warna;
	}
}
