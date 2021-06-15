package utslab.dimas.id.ac.umn;

public class MainApplication {

	public static void main(String[] args) {		
		MLMSimulator mlmSim = new MLMSimulator(3, 5, 4);
		Member m1 = new Member("Dimas Lesmana");
		
		System.out.println("Total Cash Flow : " + mlmSim.GetTotalCashflow());
		System.out.println("Member Info: " + m1.GetMemberInfo());
		
		for (Member l : mlmSim.leaders) {
			System.out.println(l.GetMemberInfo());
			// System.out.println(l.GetOverallProfit());
		}
	}

}
