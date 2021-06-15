package tutorial.week07.dimas.id.ac.umn;

public class CountingEmployeeAddedListener implements EmployeeAddedListener {

	private static int employeeAddedCount = 0;
	
	@Override
	public void onEmployeeAdded(Employee employee) {
		// Increment jumlah karyawan
		employeeAddedCount++;
		
		// Print jumlah karyawan
		System.out.println("Total karyawan : " + employeeAddedCount);
	}
	
}
