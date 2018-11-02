import java.util.*;


public class Player {
	//Variables
	private int  playerNumber, totalScore, turnPosition, ranking;
	private boolean hasPlayed;
	
	public Player(int num, int score, int turnPos) {
		playerNumber = num;
		totalScore = score;
		turnPosition = turnPos;
		hasPlayed = false;
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public int getPlayerScore() {
		return totalScore;
	}
	
	public void setPlayerScore(int score) {
		totalScore = score;
	}
	
	public int getTurnPosition() {
		return turnPosition;
	}
	
	public void setTurnPosition(int turn) {
		turnPosition = turn;
	}
	
	public boolean getHasPlayed() {
		return hasPlayed;
	}
	
	public void setHasPlayed(boolean play) {
		hasPlayed = play;
	}
	
	public void setPlayerRanking(int r) {
		ranking = r;
	}
	
	public int getPlayerRanking() {
		return ranking;
	}
	
		
	public String toString() {
		return "Player " + playerNumber + "\nScore: " + totalScore + "\n";
	}

} // End Class