

public class GameDriver {
State gameState;
int turncount;
Player player1;
Player player2;
	public GameDriver() {
	this.gameState = new State();
	player1 = new Player(Colour.WHITE);
	player2 = new Player(Colour.BLACK);
	turncount = 0;
}
	public void play(){
		gameState.getPlayerTurn(turncount);
	}
}
