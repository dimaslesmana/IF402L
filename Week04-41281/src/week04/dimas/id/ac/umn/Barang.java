package week04.dimas.id.ac.umn;

public class Barang {

	private int id, stock, harga;
	private String nama;
	
	public Barang() {}
	public Barang(int id, String nama, int stock, int harga) {
		this.id = id;
		this.nama = nama;
		this.stock = stock;
		this.harga = harga;
	}
	
	public int getID() {
		return id;
	}
	
	public int getStock() {
		return stock;
	}
	
	public int getHarga() {
		return harga;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void minusStock(int qty) {
		this.stock -= qty;
	}

}
