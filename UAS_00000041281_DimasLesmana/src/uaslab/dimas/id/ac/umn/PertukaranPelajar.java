package uaslab.dimas.id.ac.umn;

public class PertukaranPelajar extends Kegiatan {
	private String kampus;
	private String matkul;
	
	public PertukaranPelajar() {}
	public PertukaranPelajar(String userID, String name, String kegiatanType, String kampus, String matkul) {
		super(userID, name, kegiatanType);
		this.kampus = kampus;
		this.matkul = matkul;
	}
	
	public PertukaranPelajar(String userID, String name, String date, String kegiatanType, String kampus, String matkul) {
		super(userID, name, date, kegiatanType);
		this.kampus = kampus;
		this.matkul = matkul;
	}
	
	public String getKampus() {
		return this.kampus;
	}
	
	public String getMatkul() {
		return this.matkul;
	}

}
