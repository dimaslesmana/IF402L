package uaslab.dimas.id.ac.umn;

public class Magang extends Kegiatan {
	private String companyName;
	
	public Magang() {}
	public Magang(String userID, String name, String kegiatanType, String companyName) {
		super(userID, name, kegiatanType);
		this.companyName = companyName;
	}
	
	public Magang(String userID, String name, String date, String kegiatanType, String companyName) {
		super(userID, name, date, kegiatanType);
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return this.companyName;
	}
	
}
