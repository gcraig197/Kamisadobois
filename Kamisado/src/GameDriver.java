/**
 * 
 */

/**
 * @author Gavin
 *
 */
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class GameDriver {
	private State state;
	private boolean speedgame;
	private int turncount;
	private Colour previousColour;
	private Player player1;
	private Player player2;
	private Player currPlayer;
	private Scanner sc;
	private GameMenuGUI gm;
	
	
	public static void main(String[] args) {
		GameDriver gd = new GameDriver();
		gd.setup();
		gd.play();
	}
	public GameDriver() {
		this.player1 = new Player();
		this.player2 = new Player();
		sc = new Scanner(System.in);
		turncount = 0;
		previousColour = Colour.BLANK;
		
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
		if(wplayer.equals(player2.getName())){
			String swap = player2.getName();
			player2.setName(player1.getName());
			player1.setName(swap);
		}
		player1.setTeam(Colour.WHITE);
		player2.setTeam(Colour.BLACK);
		
		
		System.out.println("Do you want to play a speedgame? Y/N");
		String input = sc.nextLine();
		if(input.toLowerCase().equals("y")){
			speedgame = true;
			this.state = new State(speedgame);
		}
		else{
			speedgame = false;
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

	
	
	public void play() {
		
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
				previousColour = state.move(player2, previousColour,turncount);
			}
		
			turncount++;
			state.printGame();

			}
		System.out.println(currPlayer.getName() + " Wins the game!!!!!!!");
		System.exit(0);
	}
	
	
}


