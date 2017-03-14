public class GameDriver {
State gameState;
int turncount;
Colour previousColour;
Player player1;
Player player2;
	public GameDriver() {
	this.gameState = new State();
	player1 = new Player(Colour.WHITE);
	player2 = new Player(Colour.BLACK);
	turncount = 0;
}
	public Colour getPreviousColour() {
		return previousColour;
	}
	public void setPreviousColour(Colour previousColour) {
		this.previousColour = previousColour;
	}
	public void play(){
		while(gameState.isEnd() == false){
			String move;
			int playersTurn = gameState.getPlayerTurn(turncount);
			if(playersTurn == 1){
				 move = player1.MakeMove();
			}
			else{
				move = player2.MakeMove();
			}
		if	(gameState.moveLegalityTest(move,playersTurn,previousColour)){
			gameState.updateMove(move);
		}
			turncount++;
		}
	}
	
	public int getTurnCount(){
		return turncount;
	}
}