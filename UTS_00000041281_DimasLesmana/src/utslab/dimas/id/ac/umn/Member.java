package utslab.dimas.id.ac.umn;

public class Member {
	public String name;
	public String type;
	private Member[] downline;
	
	public Member() {}
	public Member(String name) {
		this.name = name;
		this.type = "platinum";
	}
	
	public Member(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String GetMemberInfo() {
		String memberInfo = String.format("%s (%s)", this.name, this.type);
		
		return memberInfo;
	}
	
	public long GetDirectProfit() {
		int smc = countDownlineType("silver");
		int gmc = countDownlineType("gold");
		int pmc = countDownlineType("platinum");
		
		long mr = calcRegistrationFee();
		
		return ((smc * 10000) + (gmc * 50000) + (pmc * 80000)) - mr;
	}
	
	public long GetOverallProfit() {		
		long tdp = calcTdp();
		double temp = calcMti() * tdp;
		
		return this.GetDirectProfit() + (long) temp;
	}
	
	public void SetDownline(Member[] downline) {
		this.downline = downline;
	}
	
	private long calcTdp() {
		long tdp = 0;
		
		for (Member m : downline) {
			tdp += m.GetOverallProfit();
		}
		
		return tdp;
	}
	
	private double calcMti() {
		double mti = 0.0;
		
		if (this.type.equalsIgnoreCase("silver")) {
			mti = 0.1;
		} else if (this.type.equalsIgnoreCase("gold")) {
			mti = 0.25;
		} else if (this.type.equalsIgnoreCase("platinum")) {
			mti = 0.6;
		}
		
		return mti;
	}
	
	private long calcRegistrationFee() {
		long fee = 0;
		
		if (this.type.equalsIgnoreCase("silver")) {
			fee = 20000;
		} else if (this.type.equalsIgnoreCase("gold")) {
			fee = 90000;
		} else if (this.type.equalsIgnoreCase("platinum")) {
			fee = 140000;
		}
		
		return fee;
	}
	
	private int countDownlineType(String downType) {
		int count = 0;
		
		for (Member m : downline) {
			if (m.type.equalsIgnoreCase(downType)) {
				count++;
			} else if (m.type.equalsIgnoreCase(downType)) {
				count++;
			} else if (m.type.equalsIgnoreCase(downType)) {
				count++;
			}
		}
		
		return count;
	}
	
}
