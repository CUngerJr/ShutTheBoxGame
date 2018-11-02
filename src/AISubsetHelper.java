
public class AISubsetHelper {
	private int one, two, three, four;

	// Constructor to save a subset to an ArrayList
	public AISubsetHelper(int one, int two, int three, int four) {
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
	}
	
	// @Override toString()
	public String toString() {
		return one + "," + two + "," + three + "," + four;
	}

} // End Class
