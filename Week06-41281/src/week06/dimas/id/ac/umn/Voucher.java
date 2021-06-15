package week06.dimas.id.ac.umn;

public class Voucher extends Barang {
	public static int total;
	private double pajak;
	
	public Voucher(int id, String nama, double harga, int stok, double pajak) {
		super(id, nama, harga, stok);
		this.pajak = pajak;
	}
	
	public double getPajak() {
		return this.pajak;
	}
	
	public double gethargaJual() {
		return this.getHarga() + (this.getHarga() * this.getPajak());
	}
	
}
