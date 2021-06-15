package week06.dimas.id.ac.umn;

public class Barang {
	protected int id;
	protected int stok;
	protected double harga;
	protected String nama;
	
	public Barang(int id, String nama, double harga, int stok) {
		this.id = id;
		this.nama = nama;
		this.harga = harga;
		this.stok = stok;
	}
	
	public int getId() {
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
