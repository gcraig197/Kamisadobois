import java.math.*;

public class State {
	private Board board;
	private Piece[][] pieces;
	private Colour[][] boardGrid;

	public State() {
	    board = new Board();
		board.Setup();
		pieces = board.getPieceGrid();
		boardGrid = board.getBoard();
	}

	public boolean isEnd() {
		for(int i = 0; i < 7;i++){
			if(board.getPiece(0, i).getTeam() == 'b'){
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

	public boolean moveLegalityTest(int[] moves, int player, Colour previousColour, int turncount) {
		
		int currposa = moves[0];
		int currposb = moves[1];
		int newposa = moves[2];
		int newposb = moves[3];

		// works for both teams:
		// if no piece at starting point
		if (pieces[currposa][currposb].getColour() == Colour.BLANK) {
			System.out.println("No piece at starting location");
			return false;
		}
		
		//Correct colour of tower
		if (turncount != 0 && board.getPiece(currposa, currposb).getColour() != previousColour) {
			System.out.println("Wrong colour of tower");
			return false;
		}

		// diagonal check or vertical
		if (Math.abs(currposa - newposa) != Math.abs(currposb - newposb) && currposb != newposb) {
			System.out.println("please move vertically or diagonally");
			return false;
		}

		
		//player 1
		if (player == 1) {
			if (pieces[currposa][currposb].getTeam()!= 'w') {
				return false;
				
			}

			// if moving wrong way
			if (currposa > newposa) {
				System.out.println("Moving wrong direction");
				return false;
			}

			// Occupied for vert movement
			if (currposb == newposb) {
				// for loop beginning at currposa + 1 ending at newposa
				for (int i = currposa + 1; i <= newposa; i++) {
					if (pieces[i][currposb].getColour() != Colour.BLANK) {
						System.out.println("Path is Blocked by tower");
						return false;
					}

				}
			}
			// Occupied for diag movement left
			if (currposb > newposb){ // diag left
				for (int i = 1; i <= newposa; i++) {
					if (pieces[currposa + i][currposb - i].getColour() != Colour.BLANK){
						System.out.println("Path is Blocked by tower");
						return false;
					}
					
				}
			}
			// Occupied for diag movement right
			if (currposb < newposb){ // diag right
				for (int i = 1; i <= newposa; i++) {
					if (board.getPiece(currposa + i,currposb + i ).getColour() != Colour.BLANK){
						System.out.println("Path is Blocked by tower");
						return false;
					}
					
				}
			}
		}
				
			
		

		
		//player 2
		else if(player == 2){
			 if (currposb < newposb) {
					System.out.println("Temp Error  115");
					return false;
					// check occupied

				}
			 if (pieces[currposa][currposb].getTeam()!= 'b') {
					System.out.println("Temp Error  121");
					return false;
					
				}
			// Occupied for vert movement
			 if (currposb == newposb) {
					// for loop beginning at currposa + 1 ending at newposa
					for (int i = currposa - 1; i >= newposa; i--) {
						if (pieces[i][currposb].getColour() != Colour.BLANK) {
							System.out.println(" Path Blocked by tower");
							return false;
						}

					}
				}
			// Occupied for diag movement left
				if (currposb > newposb){ // diag left
					for (int i = 1; i >= newposa; i++) {
						if (pieces[currposa-i][currposb-i].getColour() != Colour.BLANK){
							System.out.println(" Path Blocked by tower");
							return false;
						}
						
					}
				}
				
				// Occupied for diag movement right
				if (currposb < newposb){ // diag right
					for (int i = 1; i >= newposa; i++) {
						if (pieces[currposa-i][currposb+i].getColour() != Colour.BLANK){
							System.out.println(" Path Blocked by tower");
							return false;
						}
						
					}
				}
		}
		
		return true;
	}
	
	public Colour updateMove(int[] moves){
		int currposa = moves[0];
		int currposb = moves[1];
		int newposa = moves[2];
		int newposb = moves[3];
		pieces = board.move(currposa,currposb,newposa, newposb);
		
		Colour newColour = boardGrid[newposa][newposb];
		
		return newColour;
	}
	
	
	public void printGame(){
		board.printCurrentBoard();
	}
}
