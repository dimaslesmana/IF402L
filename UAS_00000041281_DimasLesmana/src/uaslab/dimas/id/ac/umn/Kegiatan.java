package uaslab.dimas.id.ac.umn;

public abstract class Kegiatan {
	public String userID;
	public String name;
	public String date;
	public String kegiatanType;
	
	public Kegiatan() {}
	public Kegiatan(String userID, String name, String kegiatanType) {
		this.userID = userID;
		this.name = name;
		this.kegiatanType = kegiatanType;
		this.date = Helpers.getCurrentDate();
	}
	
	public Kegiatan(String userID, String name, String date, String kegiatanType) {
		this.userID = userID;
		this.name = name;
		this.date = date;
		this.kegiatanType = kegiatanType;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getKegiatanType() {
		return this.kegiatanType;
	}
	
	public String getDate() {
		return this.date;
	}
	
}
