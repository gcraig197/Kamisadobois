import java.math.*;

public class State {
	private Piece[][] pieceGrid;
	private Board board;

	public State() {
		Board board = new Board();
		board.Setup();

	}
	// public boolean legal(){
	// check if move is allowable return false;
	// }

	public void setPieceGrid(Piece piece, int a, int b) {
		pieceGrid[a][b] = piece;

	}

	public boolean isEnd() {
		for(int i = 0; i < 8;i++){
			if(pieceGrid[0][i].getTeam() == 'b'){
				return true;
			}
		}
		for(int j = 0; j < 8; j++){
			if(pieceGrid[7][j].getTeam() == 'w'){
				return true;
			}
		}
		return false;
	}

	public int getPlayerTurn(int turncount) {
		if (turncount % 2 == 0)
			return 1;
		return 2;
	}

	public boolean moveLegalityTest(String move, int player, Colour previousColour) {

		char[] parts = move.toCharArray();
		int currposa = Integer.parseInt(Character.toString(parts[0]));
		int currposb = Integer.parseInt(Character.toString(parts[1]));
		int newposa = Integer.parseInt(Character.toString(parts[2]));
		int newposb = Integer.parseInt(Character.toString(parts[3]));

		// works for both teams:
		// if no piece at starting point
		if (board.getPiece(currposa, currposb).getColour() == Colour.BLANK) {
			return false;
		}
		
		//Correct colour of tower
		if (board.getPiece(currposa, currposb).getColour() != previousColour || previousColour != Colour.BLANK) {
			return false;
		}

		// diagonal check or vertical
		if (Math.abs(currposa - newposa) != Math.abs(currposb - newposb) && currposb != newposb) {
			return false;
		}

		if (player == 1) {
			if (board.getPiece(currposa, currposb).getTeam()!= 'w') {
				return false;
				
			}

			// if moving wrong way
			if (currposa > newposa) {
				return false;
			}

			// Occupied for vert movement
			if (currposb == newposb) {
				// for loop beginning at currposa + 1 ending at newposa
				for (int i = currposa + 1; i <= newposa; i++) {
					if (board.getPiece(i, currposb).getColour() != Colour.BLANK) {
						return false;
					}

				}
			}
		}

		
		//player 2
	
		else if (currposb < newposb) {
			return false;
			// check occupied

		}
		if (board.getPiece(currposa, currposb).getTeam()!= 'b') {
			return false;
			
		}
		
		// Occupied for vert movement
		if (currposb == newposb) {
			// for loop beginning at currposa + 1 ending at newposa
			for (int i = currposa - 1; i >= newposa; i--) {
				if (board.getPiece(i, currposb).getColour() != Colour.BLANK) {
					return false;
				}

			}
		}
		return true;
	}
	
	public void updateMove(String move){
		char[] parts = move.toCharArray();
		int currposa = Integer.parseInt(Character.toString(parts[0]));
		int currposb = Integer.parseInt(Character.toString(parts[1]));
		int newposa = Integer.parseInt(Character.toString(parts[2]));
		int newposb = Integer.parseInt(Character.toString(parts[3]));
		board.move(currposa, currposb, newposa, newposb);
		
	}
}
