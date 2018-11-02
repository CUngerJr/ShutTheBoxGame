import java.util.*;

/*Shut the Box Game Management Class to control game flow*/

public class ShutTheBox {
		private int[] userBoxes;												// Holds boxes selected by User
		private ArrayList<Player> playersList;								// Holds list of Players
		private ArrayList<Player> playersRandomTurns, playerTurnOrder;		// Holds List of Players turns chosen at Random, Holds official order of turns
		private int roll, whoseTurn = 0, numPlayers, turn;					// Holds roll, track of players turn, player number holder, turn holder
		private int dice1, dice2, totalRoll;									// Holds dices roll(s), Holds total roll by dice1 and dice2
		private int choice, totalScore;										// User's number of players choice, total score
		private boolean gameOver = false;									// Keep game moving on
		private Boxes box = new Boxes(9);									// Creates box objects, in this case 9
		private Board brd = new Board();										// Creates board object to simulate actions
		private Six_Sided_Dice die = new Six_Sided_Dice();					// Dice class for roll
		private Random turnGenerator = new Random();							// Random generator for player's turns
		private String errorMessage = "ERROR: Please check your entry.";		// Try/Catch statement Blocks.

		// Start method activated through driver
		public void start() {
			// Show The Board
			System.out.println("-----------------------------------------\n");
			System.out.println("	WELCOME TO SHUT-THE-BOX			   \n");
			System.out.println("-----------------------------------------\n");
			
			getPlayers();			// Get Number of Players
			sortPlayersByTurn();		// Generate Random Turn Order
			
			// Game Loop for every Player
			do {
				
				System.out.println("It is Player " + playerTurnOrder.get(whoseTurn).getPlayerNumber() + "'s Turn\n");	// Display Current Player		
				
				// Show The Board
				System.out.println("-----------SHUT THE BOX--------------\n");
				brd.displayBoard();
				System.out.println("\n\n-------------------------------------");
				
						
				roll(); // Roll The Dice
				
				// Check to see if player can close boxes according to roll
				if (!(loser() == true)) {		// loser method holds game rules
					chooseNumberBoxes();			// user chooses which boxes to close
				}
				else {	// Player rolled a number and cannot close anymore boxes
					System.out.println("You Rolled A: " + totalRoll + " and You Cannot Close Anymore Boxes.\n");
						calcFinalScore(whoseTurn);		// adds up open boxes to get score of player's turn
						whoseTurn++;
						if (whoseTurn < playerTurnOrder.size()) {	// Reset the game for the next player
							displayCurrentScores();
							brd.resetBoxes();
							totalScore = 0;
						}
						else {						// No more players
							playersRankingList();	// Display winner and total scores
							gameOver = true;
						}
					}					
			} while (!(gameOver == true));
	
		} // End Start
		
		
		// Try to implement AIPlayer Class into getPlayers Method...
		// Get Number of Players from User and store playerNumber and Score in ArrayList. Then Randomly chose order of players.
		public void getPlayers() {
			boolean error = false;
					do {
						Scanner input = new Scanner(System.in);
							try {
								System.out.println("How Many Players?: ");
								numPlayers = input.nextInt();
								error = true;
							}
							catch (InputMismatchException e){
								System.out.println(errorMessage);
							}
						} while (!(error));

						playersList = new ArrayList<Player>(numPlayers - 1);  // Add Players & Scores as Original Player ArrayList
							for (int i = 0; i < numPlayers; i++) {
								playersList.add(new Player(i + 1, 0, turn));
							}
							
							playersRandomTurns = new ArrayList<Player>(playersList);	  // Copy Player ArrayList and get Random Turns of Players				
							for (int t = 0; t < playersRandomTurns.size(); t++) {
								while (t < playersRandomTurns.size()) {
									int playerTurn = (turnGenerator.nextInt(numPlayers) + 1);
									if (playersRandomTurns.get(playerTurn - 1).getTurnPosition() == 0) {
										playersRandomTurns.get(playerTurn - 1).setTurnPosition(turn + 1);
										t++;
										turn++;
									}
									continue;
								}
							}
			}
				
			// Sort Players By Turn and store in a new Players Random Turn ArrayList
			public void sortPlayersByTurn() {
					playerTurnOrder = new ArrayList<>(playersRandomTurns);
					int turn = 1;
						Collections.sort(playerTurnOrder, Comparator.comparing(Player::getTurnPosition));
						playerTurnOrder.get(0).setTurnPosition(turn);
						for (int t = 1; t < playerTurnOrder.size(); t++) {
							if (!(playerTurnOrder.get(t).getTurnPosition() == playerTurnOrder.get(t -1).getTurnPosition())) { // Set Players Turns
								turn++;
								playerTurnOrder.get(t).setTurnPosition(turn);
							}
						}
						
						System.out.println("---HERE IS THE ORDER OF PLAY--------\n");			// Display Player Turn Order
						for (Player pt : playerTurnOrder) {
							System.out.println("           Player " + pt.getPlayerNumber());
						}
						 System.out.println("\n---------------------------------\n\n");
			}

		// Roll 2 Dice per roll
		public void roll() {	
			boolean err = false;
			do {
				Scanner scan = new Scanner(System.in);
				
				try {
						System.out.println("Press 1 to Roll: ");
						roll = scan.nextInt();
						if (!(roll == 1)) {
							System.out.println("ERROR: Please Press 1 to Roll.");
						}
						else {
							dice1 = die.roll();
							dice2 = die.roll();
							totalRoll = dice1 + dice2;
							err = true;
						}
				}
				catch (InputMismatchException mess) {
					System.out.println(errorMessage);
				}
		} while (!(err));
		
	}
	
		// User chooses how many and which boxes to close
		public void chooseNumberBoxes() {
			boolean error = false;
			int valid, check = 0;
			
			do {
				do {	
					System.out.println("\nYou Rolled A: " + totalRoll);
					System.out.println("");
					
					try {
							Scanner in = new Scanner(System.in);
							System.out.println("How many boxes would you like to close?");
							check = choice = in.nextInt();
							userBoxes = new int[4];
							error = true;
					
						if (choice < 1 || choice >=5) {
							System.out.println("That is not a valid move! You must pick at least 1 box, but not more than 4 boxes!");
						}
					}
					catch (InputMismatchException m) {
						System.out.println(errorMessage);
					}
				
				} while ((choice < 1 || choice >= 5) && (!(error)));
		
				do {
					Scanner user = new Scanner(System.in);
					try {
						System.out.println("\nWhat Number Box To Close?: ");		// Collects Box numbers of user's choice to close up to 4 boxes
							if (choice == 4) {
								userBoxes[3] = user.nextInt();
								choice--;
							}
							else if (choice == 3) {
								userBoxes[2] = user.nextInt();
								choice--;
							}
							else if (choice == 2) {
								userBoxes[1] = user.nextInt();
								choice--;
							}
							else if (choice == 1) {
								userBoxes[0] = user.nextInt();
								choice--;
							}
					}
					catch (InputMismatchException e) {
								System.out.println(errorMessage);
							
						}
				} while (!(choice == 0));
		
			
				valid = brd.chooseBoxesToClose(userBoxes, totalRoll);		// Method in Board Class to close boxes accordingly to roll
				brd.setAmount(0);	// Reset counting total that adds up user's choice of boxes to the roll
				
				if (!(valid == check)) {
					System.out.println("Error: Invalid Input or Your Box is Already Closed. Try Again!");
				}
				
		} while (!(valid == check));
		
	}
		
		// Strategic checker for Rules of the game and that player can go again or not.
		public boolean loser() {
			for (int i = 0; i < brd.box.length; i++) {
				if (brd.box[i].getBoxState() == true) {
					continue;
				}
			
				if (brd.box[i].getBoxValue() == totalRoll) {
					return false;
				}
			
				if (brd.box[i].getBoxValue() > totalRoll) {
					break;
				}
			
					for (int k = i + 1; k < brd.box.length; k++){
						if (brd.box[k].getBoxState() == true) {
							continue;
						}
					
						if (brd.box[i].getBoxValue() + brd.box[k].getBoxValue() == totalRoll) {
							return false;
						}
					
						if (brd.box[k].getBoxValue() > totalRoll) {
							break;
						}
					}
			}
			return true;
		}
	
		// Check if Player Shut All The Boxes or Get Players total score based on open boxes.
		public void calcFinalScore(int wt) {					// Calculate totalScore by adding up open boxes
			for (int i = 0; i < brd.box.length; i++) {
				if (brd.box[i].getBoxState() == false) {
					totalScore += brd.box[i].getBoxValue();
				}
			}
			
			for (int j = 0; j < playersList.size();) {			// If winner shut all the boxes display winner
				if (playersList.get(j).getPlayerNumber() == playerTurnOrder.get(wt).getPlayerNumber()) {
					playersList.get(j).setPlayerScore(totalScore);
					playersList.get(j).setHasPlayed(true);
					if (playersList.get(j).getPlayerScore() == 0) {
						displayAutoWinner(j);
					}
					break;
				}
				else {
					j++;
				}
			}
		}
		
		// Simple method to add nice display of current scores in the game.  Displayed at the end of each players turn
		public void displayCurrentScores() {
			System.out.println("\n####### CURRENT SCORES ########\n");
			for (Player s : playersList) {
				System.out.println("	  Player " + s.getPlayerNumber());
				System.out.println("	  Score: " + s.getPlayerScore() + "\n");
			}
			System.out.println("\n####### CURRENT SCORES ########\n");
		}
	
		// Create a new list to rank players based on lowest score
		public void playersRankingList() {
				List<Player> scoreOrder = new ArrayList<>(playersList);
				int rank = 1;
					Collections.sort(scoreOrder, Comparator.comparing(Player::getPlayerScore));
						scoreOrder.get(0).setPlayerRanking(rank);
						for (int player = 1; player < scoreOrder.size(); player++) {
							if (!(scoreOrder.get(player).getPlayerScore() == scoreOrder.get(player -1).getPlayerScore())) {
								rank++;
								scoreOrder.get(player).setPlayerRanking(rank);
							}
						}
						
						for (int w = 0; w < scoreOrder.size(); w++) {
							if (scoreOrder.get(w).equals(scoreOrder.get(0))) {
								System.out.println("\n\n**************** WINNER ****************\n");
								System.out.println("		Player " + scoreOrder.get(w).getPlayerNumber());
								System.out.println("		Score: " + scoreOrder.get(w).getPlayerScore());
								System.out.println("\n\n**************** WINNER ****************\n");
							}
							else {
								System.out.println("\nHonorable Mention:\n");
								System.out.println("		Player " + scoreOrder.get(w).getPlayerNumber());
								System.out.println("		Score: " + scoreOrder.get(w).getPlayerScore());
							}
						}
		}
		
		// Display the Winner if they have Shut all the Boxes
		public void displayAutoWinner(int win) {
			System.out.println("\n\n**************** WINNER ****************\n");
			System.out.println("		Player " + playersList.get(win).getPlayerNumber());
			System.out.println("		Score: " + playersList.get(win).getPlayerScore());
			System.out.println("\n\n**************** WINNER ****************\n");
			gameOver = true;
		}

	

} // End Class

	
