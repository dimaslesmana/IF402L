package week06.dimas.id.ac.umn;

public class Handphone extends Barang {
	public static int total;
	private String warna;
	
	public Handphone(int id, String nama, double harga, int stok, String warna) {
		super(id, nama, harga, stok);
		this.warna = warna;
	}
	
	public String getWarna() {
		return this.warna;
	}
	
}
