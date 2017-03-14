public class GameDriver {
	State gameState;
	int turncount;
	Colour previousColour;
	Player player1;
	Player player2;

	public GameDriver() {
		this.gameState = new State();
		player1 = new Player("",Colour.WHITE);
		player2 = new Player("",Colour.BLACK);
		turncount = 0;
		previousColour = Colour.BLANK;
		
	}
	
	public void setup(){
		player1.setName();
		player2.setName();
	}

	public Colour getPreviousColour() {
		return previousColour;
	}

	public void setPreviousColour(Colour previousColour) {
		this.previousColour = previousColour;
	}

	public void play() {
		gameState.printGame();
		while (gameState.isEnd() == false) {
			int[] moves = new int[4];
			int playersTurn = gameState.getPlayerTurn(turncount);
			if (playersTurn == 1) {
				System.out.println("Player " + playersTurn + " make your move!");
				if(turncount != 0){
					System.out.println("Move your " + previousColour + " piece.");
				}
				moves = player1.MakeMove();
			} else {
				System.out.println("Player " + playersTurn + " make your move!");
				System.out.println("Move your " + previousColour + " piece.");
				moves = player2.MakeMove();
			}
			if (gameState.moveLegalityTest(moves, playersTurn, previousColour,turncount)) {
				previousColour = gameState.updateMove(moves);

			}
			turncount++;
			gameState.printGame();
			
		}
		int winningPlayer = gameState.getPlayerTurn(turncount -1);
		System.out.println("Player " + winningPlayer + " Wins the game!!!!!!!");
		
	}

	public int getTurnCount() {
		return turncount;
	}
}