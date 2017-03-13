

public class State {
	private Piece[][] pieceGrid;
	public State() {
		Board board = new Board();
		board.Setup();
	
}
	//public boolean legal(){
		// check if move is allowablereturn false;
	//}
	
	public void setPieceGrid(Piece piece ,int x ,int y){
		pieceGrid [x][y] = piece;
		
	}
	public void isEnd(){
		
	}
	public int getPlayerTurn(int turncount){
		if(turncount % 2 == 0)
			return 1;
		return 2;
	}
}
