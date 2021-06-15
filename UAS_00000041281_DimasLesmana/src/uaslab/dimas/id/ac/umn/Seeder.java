package uaslab.dimas.id.ac.umn;

public final class Seeder {
	private static int seedAmount;
	
	private Seeder() {}
	
	public static void seed(int amount) {
		seedAmount = amount;
		mahasiswa();
		dosen();
		kegiatan();
	}

	private static void mahasiswa() {
		for (int i = 1; i <= seedAmount; i++) {
			User.setUsers(new Mahasiswa("Mahasiswa " + i, "mhs-" + i, "mhs-" + i, 'M'));
		}
	}
	
	private static void dosen() {
		for (int i = 1; i <= seedAmount; i++) {
			User.setUsers(new Dosen("Dosen " + i, "dosen-" + i, "dosen-" + i, 'D'));
		}
	}
	
	private static void kegiatan() {
		for (int i = 1; i <= seedAmount; i++) {
			for (int j = 0; j < User.getUsers().size(); j++) {
				User user = User.getUsers().get(j);
				
				if (user instanceof Mahasiswa) {
					Mahasiswa mhs = (Mahasiswa) user;
					mhs.insertIntoMagangLogs(new Magang(mhs.getUserID(), "Magang " + i, "Kegiatan " + i, "Perusahaan " + i));
					mhs.insertIntoPPLogs(new PertukaranPelajar(mhs.getUserID(), "Pertukaran Pelajar " + i, "Kegiatan " + i, "Kampus " + i, "Matkul " + i));
				}
			}
		}
	}
	
}
