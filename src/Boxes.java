
public class Boxes {
	private int boxNum;
	private boolean state;
	
	// Construct number of Boxes
	public Boxes(int numBoxes) {
		boxNum = numBoxes;
		state = false;
	}
	
	// Get index of a Box
	public int getBoxIndex() {
		return boxNum;
	}
	
	// Set box open/closed status
	public void setBoxState(boolean s) {
		state = s;
	}
	
	// Get box open/closed status
	public boolean getBoxState() {
		return state;
	}
	
	// Get value inside of box;
	public int getBoxValue() {
		return boxNum + 1;
	}

} // End Class
