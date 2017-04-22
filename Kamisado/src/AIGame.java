/**
 * 
 */

/**
 * @author Gavin
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.sun.xml.internal.ws.api.Cancelable;

public class AIGame {
	private AIState state;
	private boolean speedgame;
	private int turncount;
	private Colour previousColour;
	private Player player1;
	private AIPlayer player2;
	private Player currPlayer;
	private Scanner sc;
	private AIGameMenuGUI gm;
	private GameOverGUI gameover;
	private Boolean randomboard;
	private ArrayList<Piece> sumoPieces;
	private int rounds;
	private int player1RoundsWon;
	private int player2RoundsWon;
	private LeaderBoard leaderBoard;

	public AIGame() {
		this.player1 = new Player();
		this.player2 = new AIPlayer();
		player2.setDifficulty("easy");
		sc = new Scanner(System.in);
		turncount = 0;
		previousColour = Colour.BLANK;
		speedgame = false;
		randomboard = true;
		sumoPieces = new ArrayList<Piece>();
		player1RoundsWon = 0;
		player2RoundsWon = 0;
		leaderBoard = new LeaderBoard();
		try {
			leaderBoard.loadLeaderBoard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setup() {
		gm = new AIGameMenuGUI();
		while (gm.isFinished() == false) {
			try {
				TimeUnit.MILLISECONDS.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String name = gm.getPlayer1Name();
		if (name.isEmpty()) {
			player1.setName("player1");
		} else {
			player1.setName(name);
		}

		player2.setDifficulty(gm.getDifficulty().toLowerCase());
		player2.setName("AIPlayer");

		player1.setTeam(Colour.WHITE);
		player2.setTeam(Colour.BLACK);

		speedgame = gm.isSpeedGame();
		randomboard = gm.isRandomBoard();
		rounds = gm.getRounds();
		this.state = new AIState(speedgame, randomboard);
	}

	public Player getPlayerTurn() {
		if (turncount == 0) {
			return player1;
		} else if (turncount % 2 == 0) {
			return player1;
		} else {
			return player2;
		}

	}

	public Colour getPreviousColour() {
		return previousColour;
	}

	public void setPreviousColour(Colour previousColour) {
		this.previousColour = previousColour;
	}

	public void play() throws FileNotFoundException {

		int counter = 0;
		currPlayer = new Player();
		state.printGame();
		while (counter < rounds) {
			while (state.isFinished() == false) {
				
				System.out.println("\nSUMOOOPIECESSSS\n");
				System.out.println(sumoPieces.size());
				
				
				currPlayer = getPlayerTurn();
				if (currPlayer == player1) {
					System.out.println(currPlayer.getName() + " make your move!");
					if (turncount == 0) {
						System.out.println("Move any piece");
					} else {
						System.out.println("Move your " + previousColour + " piece.");
					}

					previousColour = state.move(player1, previousColour, turncount,rounds);
					System.out.println("broke out of state");
				} else {
					currPlayer = player2;
					System.out.println(currPlayer.getName() + " make your move!");
					System.out.println("Move your " + previousColour + " piece.");
					if (player2.getDifficulty().equals("easy")) {
						previousColour = state.computeAIMove(player2, player1, previousColour);
					} else if (player2.getDifficulty().equals("hard")) {
						previousColour = state.hardAIMove(player2, player1, previousColour);
					}
				}

				turncount++;

			}
			RoundOverGUI roundover = new RoundOverGUI(currPlayer);
			while(roundover.isFinished() == false){
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(currPlayer == player1){
				player1RoundsWon++;
			}
			else{
				player2RoundsWon++;
			}
			
			calcPointsWon(currPlayer);
			
			if(earlyWin())
				break;
			turncount = 0;  
			previousColour = Colour.BLANK;
			sumoPieces.add(state.getWinningPiece());
			state.resetBoard();
			state.setSumoPieces(sumoPieces);
			counter++;
		}
		leaderBoard.saveScore(currPlayer, currPlayer.getPoints());
		leaderBoard.saveLeaderBoard();
		gameover = new GameOverGUI(currPlayer);
	}
	
	private void calcPointsWon(Player currPlayer) {
		if(state.getWinningPiece().getTeeth() == 1){
			currPlayer.addPoints(3);
		}
		else if(state.getWinningPiece().getTeeth() == 2){
			currPlayer.addPoints(7);
		}
		else if(state.getWinningPiece().getTeeth() == 3){
			currPlayer.addPoints(15);
		}
		else{
			currPlayer.addPoints(1);
		}
		
	}

	public boolean earlyWin(){
		if(player1RoundsWon > rounds/2){
			return true;
		}
		else if(player2RoundsWon > rounds/2){
			return true;
		}
		else{
			return false;
		}
	}

}
