package model;

public class Barang {
	protected String id;
	protected int stok;
	protected double harga;
	protected String nama;
	
	public Barang(String id, String nama, double harga, int stok) {
		this.id = id;
		this.nama = nama;
		this.harga = harga;
		this.stok = stok;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getNama() {
		return this.nama;
	}
	
	public double getHarga() {
		return this.harga;
	}
	
	public int getStok() {
		return this.stok;
	}
	
	public void minusStok(int jml) {
		this.stok -= jml;
	}
}
