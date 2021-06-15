package week07.dimas.id.ac.umn;

public class Credit extends Payment {
	private int installment;
	private int maxInstallmentAmount;
	
	public Credit() {}
	public Credit(Item item, int maxInstallmentAmount) {
		super(item);
		this.maxInstallmentAmount = maxInstallmentAmount;
		this.installment = 0;
	}

	@Override
	public int pay(boolean doTransaction) {
		if (doTransaction) {
			this.installment++;
		}
		
		if (this.installment == this.maxInstallmentAmount) {
			isPaidOff = true;
		}
		
		if (isPaidOff) {			
			return 0;
		}
		
		return this.item.getPrice() / maxInstallmentAmount;
	}
	
	@Override
	public int getRemainingAmount() {
		if (isPaidOff) {
			return 0;
		}
		
		return this.item.getPrice() - installment * this.item.getPrice() / maxInstallmentAmount;
	}
	
	@Override
	public String getClassName() {
		return "CREDIT";
	}
	
}
