

public class State {
	private Piece[][] pieceGrid;
	public State() {
		Board board = new Board();
		board.Setup();
		Player player1 = new Player(Colour.WHITE);
		Player player2 = new Player(Colour.BLACK);
	
}
	// check if move is allowable
	public boolean legal( String move){
		return false;
	}
	
	public void setPieceGrid(Piece piece ,int x ,int y){
		pieceGrid [x][y] = piece;
		
	}
	public void isEnd(){
		
	}
}
