

public class Board {
	// Variables
	 Boxes[] box;
	 private int amount, result;
	
	// Constructor to set Array in order
	public Board() {
		box = new Boxes[9];
			for (int i = 0; i < box.length; i++) {
				box[i] = new Boxes(i);
			}	
	}
	
	// User chooses which boxes to close
	public int chooseBoxesToClose(int[] userBoxes, int totalRoll) {
		result = 0;
		for (int i = 0; i < userBoxes.length; i++) {			// Counter to make sure boxes add up to roll
			amount += userBoxes[i];
		}
			
		for (int userBox: userBoxes) { 							
			if (amount > totalRoll || (amount != totalRoll)) {	// Checker to close boxes in Array
				userBoxes = new int[3];
				result = -1;
			}
			else {
				for (int i = 0; i < box.length; i ++) {		// Set boxes status to close
					if (userBox == box[i].getBoxValue() && box[i].getBoxState() == false) {
						box[i].setBoxState(true);
						result += 1;
					}
				}
			}
		}
		return result;
	}

	
	// Reset Amount for checking Boxes to Close
	public void setAmount(int amt) {
		amount = amt;
	}
	
	// Reset All Boxes to open for next Player
	public void resetBoxes() {
		for (Boxes b : box) {
			b.setBoxState(false);
		}
	}

	
	// Display Game Board
	public void displayBoard() {
		int i = 0;
		while (i < box.length) {								
			if (!(box[i].getBoxState() == true)) {			// True =  display open box, False = display closed box
				System.out.print("|");
				System.out.print(box[i].getBoxValue());
				System.out.print("|" + " ");
				i++;
			}
			
			else {						// Display a closed Box
			System.out.print("|");
			System.out.print("*");
			System.out.print("|" + " ");
			i++;
			}
		}
	}

} // End Class






