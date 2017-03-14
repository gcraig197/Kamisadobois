import java.math.*;

public class State {
	private Board board;
	private Piece[][] pieces;

	public State() {
		Board board = new Board();
		board.Setup();
		pieces = board.getPieceGrid();
	}

	public boolean isEnd() {
		for(int i = 0; i < 7;i++){
			if(pieces[0][i].getTeam() == 'b'){
				return true;
			}
		}
		for(int j = 0; j < 7; j++){
			if(pieces[7][j].getTeam() == 'w'){
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

	public boolean moveLegalityTest(int[] moves, int player, Colour previousColour) {
		
		int currposa = moves[0];
		int currposb = moves[1];
		int newposa = moves[2];
		int newposb = moves[3];

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

		
		//player 1
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
			// Occupied for diag movement left
			if (currposb > newposb){ // diag left
				for (int i = 1; i <= newposa; i++) {
					if (board.getPiece(currposa + i,currposb - i ).getColour() != Colour.BLANK){
						return false;
					}
					
				}
			}
			// Occupied for diag movement right
			if (currposb < newposb){ // diag right
				for (int i = 1; i <= newposa; i++) {
					if (board.getPiece(currposa + i,currposb + i ).getColour() != Colour.BLANK){
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
		
		// Occupied for diag movement left
		if (currposb > newposb){ // diag left
			for (int i = 1; i >= newposa; i++) {
				if (board.getPiece(currposa - i,currposb - i ).getColour() != Colour.BLANK){
					return false;
				}
				
			}
		}
		
		// Occupied for diag movement right
		if (currposb < newposb){ // diag right
			for (int i = 1; i >= newposa; i++) {
				if (board.getPiece(currposa - i,currposb + i ).getColour() != Colour.BLANK){
					return false;
				}
				
			}
		}
		
		return true;
	}
	
	public void updateMove(int[] moves){
		int currposa = moves[0];
		int currposb = moves[1];
		int newposa = moves[2];
		int newposb = moves[3];
		board.move(currposa, currposb, newposa, newposb);
		
	}
}
