package tutorial.week07.dimas.id.ac.umn;

public class PrintEmployeeAddedListener implements EmployeeAddedListener {

	@Override
	public void onEmployeeAdded(Employee employee) {
		// Print nama karyawan yang baru ditambahkan
		System.out.println("Ditambahkan karyawan baru dengan posisi '" + employee.getPosition() + "' bernama '" + employee.getName() + "'");
	}

}
