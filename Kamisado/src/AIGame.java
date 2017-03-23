/**
 * 
 */

/**
 * @author Gavin
 *
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class AIGame {
	private State state;
	private boolean speedgame;
	private int turncount;
	private Colour previousColour;
	private Player player1;
	private AIPlayer player2;
	private Player currPlayer;
	private Scanner sc;
	private AIGameMenuGUI gm;
	private GameOverGUI gameover;
	
	
	public AIGame() {
		this.player1 = new Player();
		this.player2 = new AIPlayer();
		player2.setDifficulty("easy");
		sc = new Scanner(System.in);
		turncount = 0;
		previousColour = Colour.BLANK;
		speedgame = false;
		
	}
	
	public void setup(){
		gm = new AIGameMenuGUI();
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
		
		player2.setName("AI Player");
		
		player1.setTeam(Colour.WHITE);
		player2.setTeam(Colour.BLACK);
		
		
		speedgame = gm.isSpeedGame();
		if(speedgame == true){
			this.state = new State(speedgame);
		}
		else{
			  this.state = new State(speedgame);
		}
		
		
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
				previousColour = state.computeAIMove(player2, previousColour);
			}
		
			turncount++;
			state.printGame();

			}
		gameover = new GameOverGUI(currPlayer);
	}
	
	
}


