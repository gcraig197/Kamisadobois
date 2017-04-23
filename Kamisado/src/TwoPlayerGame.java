/**
 * 
 */

/**
 * @author Gavin
 *
 */
import java.awt.Container;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class TwoPlayerGame {
	private State state;
	private boolean speedgame;
	private boolean randomboard;
	private int turncount;
	private Colour previousColour;
	private Player player1;
	private Player player2;
	private Player currPlayer;
	private Scanner sc;
	private GameMenuGUI gm;
	private GameOverGUI gameover;
	private int player1RoundsWon;
	private int player2RoundsWon;
	private int rounds;
	private ArrayList<Piece> sumoPieces;
	private LeaderBoard leaderBoard;
	
	public TwoPlayerGame() {
		this.player1 = new Player();
		this.player2 = new Player();
		sc = new Scanner(System.in);
		turncount = 0;
		previousColour = Colour.BLANK;
		speedgame = false;
		randomboard = true;
		sumoPieces = new ArrayList<Piece>();
		leaderBoard = new LeaderBoard();
		try {
			leaderBoard.loadLeaderBoard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setup(){
		gm = new GameMenuGUI();
		while(gm.isFinished() == false){
			try {
				TimeUnit.MILLISECONDS.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String name = gm.getPlayer1Name();
		if(name.isEmpty()){
			player1.setName("player1");
		}
		else{
			player1.setName(name);
		}
		
		
	    name = gm.getPlayer2Name();
		if(name.isEmpty()){
			player2.setName("player2");
		}
		else{
			player2.setName(name);
		}
		
		System.out.println("please enter the name of the player that will be white");
		String wplayer = gm.getWhitePlayer();
		if(wplayer.trim().toLowerCase().equals(player2.getName().trim().toLowerCase())){
			String swap = player2.getName();
			player2.setName(player1.getName());
			player1.setName(swap);
		}
		player1.setTeam(Colour.WHITE);
		player2.setTeam(Colour.BLACK);
		
		
		speedgame = gm.isSpeedGame();
		randomboard = gm.isRandomBoard();
		rounds = gm.getRounds();
		state = new State(speedgame,randomboard);
	
		
		
	}
	
	public Player getPlayerTurn(){
		if(turncount == 0){
			return player1;
		}
		else if(turncount % 2 == 0){
			return player1;
		}
		else{
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
		
		currPlayer = new Player();
		state.printGame();
		int counter = 0;
		while(counter < rounds){
			
		
		while (state.isFinished() == false) {
		
			currPlayer = getPlayerTurn();
			if (currPlayer == player1) {
				System.out.println(currPlayer.getName() + " make your move!");
				if(turncount == 0){
					System.out.println("Move any piece");
				}
				else{
					System.out.println("Move your " + previousColour + " piece.");
				}
				previousColour = state.move(player1, previousColour,turncount);
			}
			else {
				currPlayer = player2;
				System.out.println(currPlayer.getName() + " make your move!");
				System.out.println("Move your " + previousColour + " piece.");
				previousColour = state.move(player2, previousColour,turncount);
			}
		
			turncount++;
			state.printGame();

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
		
		leaderBoard.saveScore(currPlayer, calcPointsWon(currPlayer));
		
		if(earlyWin())
			break;
		turncount = 0;  
		previousColour = Colour.BLANK;
		sumoPieces.add(state.getWinningPiece());
		state.resetBoard();
		state.setSumoPieces(sumoPieces);
		counter++;
		}
		
		leaderBoard.saveLeaderBoard();
		gameover = new GameOverGUI(currPlayer);
	}
	
	
	private int calcPointsWon(Player currPlayer) {
		if(state.getWinningPiece().getTeeth() - 1 == 1){
			return 3;
		}
		else if(state.getWinningPiece().getTeeth() - 1 == 2){
			return 7;
		}
		else if(state.getWinningPiece().getTeeth() - 1 == 3){
			return 15;
		}
		else{
			return 1;
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


