
public class Six_Sided_Dice extends Die {
	Boxes box;
	Board brd;
	private int totalRoll;

	public Six_Sided_Dice() {
		super(6);
	}
	
	public int roll() {
			totalRoll = super.roll();
			return totalRoll;
		
	}
	
	public void setTotalRoll(int t) {
		totalRoll = t;
	}
	
	public int getTotalRoll() {
		return totalRoll;
	}
	
	public void display(int sides)
	{
		System.out.println("*******");
		System.out.println("*     *");
		System.out.println("*  " + sides + "  *");
		System.out.println("*     *");
		System.out.println("*******");
	}

} // End Class