import java.util.ArrayList;

/* Having trouble implementing AIPlayer Class into game without needing a complete restructure of the Shut The Box Game Class*/


public class AIPlayer extends Player {
		private int[] arrayNums = {0,1,2,3,4,5,6,7,8,9};
		private static ArrayList<AISubsetHelper> subset = new ArrayList<>();
		static Boxes box;
		static Board brd;
		
	
	// Super Constructor with Player Class
	public AIPlayer(int n, int s, int t) {
		super(n,s,t);
	}
		

	
	// Combinations to sum of Roll
		 public static void getRollNumSums(int[] arrayNums, int roll) { 
	    		for (int i = 0; i < arrayNums.length; i++) { 
	    				int first = arrayNums[i]; 
	    					for (int j = i; j < arrayNums.length; j++) {
	    						int second = arrayNums[j];
	    							for (int k = i; k < arrayNums.length; k++) {
	    								int third = arrayNums[k]; 
	    									for (int m = i; m < arrayNums.length; m++) {
	    										int fourth = arrayNums[m]; 
	    							if ((first + second + third + fourth) == roll) { 
	    									subset.add(new AISubsetHelper(first, second, third, fourth));		// Save subsets in new ArrayList
	    							} 
	    						} 
	    					}
	    				}
	    			} 
		 }
		 
		 // ArrayList subset has highest values first, close boxes with highest value
		 public static void AIRulesBoxStatus(ArrayList<AISubsetHelper> sub) {
			 ArrayList<AISubsetHelper> temp = new ArrayList<>(sub.size());
			 for (int s = 0; s < sub.size(); s++)	{
				 temp.add(sub.get(s));
				 for (int a = s; a < temp.size();) {
					 if ((temp.get(s).equals(brd.box[a].getBoxValue()) && (brd.box[a].getBoxState() == false))) {
						 brd.box[a].setBoxState(true);
						 a++;
					 }
					 else {
						 a++;
					 }
				 }
			 }
			 
		 }


} // End Class
