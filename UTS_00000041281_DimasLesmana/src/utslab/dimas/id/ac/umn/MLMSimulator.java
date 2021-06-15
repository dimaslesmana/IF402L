package utslab.dimas.id.ac.umn;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class MLMSimulator {
	public List<Member> leaders = new ArrayList<Member>();
	private List<Member> members = new ArrayList<>();
	private int minDownline;
	private int maxDownline;
	private int schemeDepth;
	private List<String> listOfFirstNames;
	private List<String> listOfMiddleNames;
	private List<String> listOfLastNames;
	
	public MLMSimulator() {}
	public MLMSimulator(int minDownline, int maxDownline, int schemeDepth) {
		this.minDownline = minDownline;
		this.maxDownline = maxDownline;
		this.schemeDepth = schemeDepth;
		
		this.listOfFirstNames = Arrays.asList(new String[] {"Ani", "Budi", "Cici", "Didi", "Era", "Fanni", "Gemma", "Heri", "Intan", "Jumilang", "Karmin"});
		this.listOfMiddleNames = Arrays.asList(new String[] {"Arim", "Belle", "Caprice", "Linn", "Maee", "Rie", "Joe", "Thea", "Cia", "Isla", "Rann"});
		this.listOfLastNames = Arrays.asList(new String[] {"Finley", "Carson", "Davis", "Anderson", "Carter", "Smith", "Thompson", "Walker", "Kenzo"});
		
		Random rand = new Random();
		
		int n = rand.nextInt(this.maxDownline - this.minDownline) + this.minDownline;
		for (int i = 0; i < n; i++) {
			Member member = new Member(GetRandomName(), GetRandomType());
			this.leaders.add(member);
			// this.members.add(member);
		}

		List<Member> currentMembers = this.leaders;
		
		for (Member m : currentMembers) {
			this.schemeDepth -= 1;
			
			Member[] downline = new Member[n];
			for (int i = 0; i < n; i++) {
				downline[i] = new Member(GetRandomName(), GetRandomType());
			}
			
			m.SetDownline(downline);
			currentMembers = Arrays.asList(downline);
			
			if (this.schemeDepth <= 0) {
				break;
			}
		}
		
	}
	
	public long GetTotalCashflow() {
		long total = 0;
		
		for (Member m : members) {
			long overallProfit = m.GetOverallProfit();
			if (overallProfit >= 0) {				
				total += overallProfit;
			}
		}
		
		return total;
	}
	
	public long GetMemberProfitByName(String name) {
		long result = 0;
		
		for (Member m : members) {
			if (m.name.equalsIgnoreCase(name)) {
				result += m.GetOverallProfit();
			}
		}
		
		return result;
	}
	
	public int GetTotalMember() {	
		return members.size();
	}
	
	public String GetRandomName() {
		Random rand = new Random();
		
		int randFidx = rand.nextInt(listOfFirstNames.size());
		String f = listOfFirstNames.get(randFidx);
		
		int randMidx = rand.nextInt(listOfMiddleNames.size());
		String m = listOfMiddleNames.get(randMidx);
		
		int randLidx = rand.nextInt(listOfLastNames.size());
		String l = listOfLastNames.get(randLidx);
		
		return f + " " + m + " " + l;
	}
	
	public String GetRandomType() {
		int type;
		String typeStr = "";
		
		while (true) {
			type = new Random().nextInt(3);
			
			if (type >= 0 && type <= 2) {
				break;
			}
		}
		
		switch (type) {
		case 0:
			typeStr = "silver";
			break;
		case 1:
			typeStr = "gold";
			break;
		case 2:
			typeStr = "platinum";
			break;
		}
		
		return typeStr;
	}
	
}
