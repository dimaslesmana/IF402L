package tutorial.week07.dimas.id.ac.umn;

public class Main {

	public static void main(String[] args) {
		Office office = new Office();
		
		office.registerEmployeeAddedListener(new PrintEmployeeAddedListener());
		office.registerEmployeeAddedListener(new CountingEmployeeAddedListener());
		
		office.addEmployee(new Employee("Andre", "IT Manager"));
		office.addEmployee(new Employee("Vincent", "Senior Programmer"));
		office.addEmployee(new Employee("Jonathan", "Internship Programmer"));
	}

}
