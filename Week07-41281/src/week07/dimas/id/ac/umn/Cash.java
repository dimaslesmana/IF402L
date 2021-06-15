package week07.dimas.id.ac.umn;

public class Cash extends Payment {
	
	public Cash() {}
	public Cash(Item item) {
		super(item);
	}
	
	@Override
	public int pay(boolean doTransaction) {
		if (doTransaction) {
			isPaidOff = true;
		}
		
		if (isPaidOff) {
			return 0;
		}
		
		return this.item.getPrice();
	}
	
	@Override
	public String getClassName() {
		return "CASH";
	}
}
