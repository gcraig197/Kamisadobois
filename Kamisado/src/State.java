import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class State {
	private boolean finished;
	private Board board;
	private Boolean deadlockFlag;
	private Scanner sc;
	private BoardGUI gui;

	

	public State(boolean speedgame) {
		finished = false;
		board = new Board();
		board.Setup();
		sc = new Scanner(System.in);
		deadlockFlag = false;
		gui = new BoardGUI(board);
		gui.refresh(board);

	}

	public void printGame() {
		board.printCurrentBoard();
	}

	public Colour move(Player player, Colour previousColour, int turncount) {

		if (deadLockCheck(player, previousColour) == false) {
			return board.getColour(board.getLastPieceX(player, previousColour),
					board.getLastPieceY(player, previousColour));
		}
		if (turncount == 0) {
			
			// wait for click
			
			while(gui.canMove() == false){
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int currposa = gui.getX();
			int currposb = gui.getY();
			gui.setCanMove(false);
			
			// wait for click
			
			while(gui.canMove() == false){
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int newposa = gui.getX();
			int newposb = gui.getY();
			if (movelegality(player, currposa, currposb, newposa, newposb, previousColour)) {
				board.setPieceCell(newposa, newposb, board.getPieceCell(currposa, currposb));

				board.setPieceCell(currposa, currposb, board.getBlankPiece());
				deadlockFlag = false;
				gui.refresh(board);
				gui.setCanMove(false);
				return board.getColour(newposa, newposb);
			}

			else {
				gui.setCanMove(false);
				move(player, previousColour, turncount);
			}
		} else {

			int currposa = board.getLastPieceX(player, previousColour);
			int currposb = board.getLastPieceY(player, previousColour);
			System.out.println("(" + currposa + "," + currposb + ")");
			gui.setCanMove(false);
			while(gui.canMove() == false){
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int newposa = gui.getX();
			int newposb = gui.getY();
			

			if (movelegality(player, currposa, currposb, newposa, newposb, previousColour)) {
				board.setPieceCell(newposa, newposb, board.getPieceCell(currposa, currposb));

				board.setPieceCell(currposa, currposb, board.getBlankPiece());
				deadlockFlag = false;
				gui.refresh(board);
				gui.setCanMove(false);
				return board.getColour(newposa, newposb);
			}

			else {
				gui.setCanMove(false);
				move(player, previousColour, turncount);
			}
		}

		return previousColour;
	}
	public boolean isFinished() {
		for (int i = 0; i < 8; i++) {
			if (board.getPieceCell(0, i).getTeam() == Colour.BLACK) {
				return true;
			}
		}
		for (int j = 0; j < 8; j++) {
			if (board.getPieceCell(7, j).getTeam() == Colour.WHITE) {
				return true;
			}
			if(finished == true){
				return true;
			}
		}
		return false;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	private boolean deadLockCheck(Player player, Colour previousColour) {
		int currposa = 0;
		int currposb = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.getPieceCell(i, j).getPieceColour() == previousColour
						&& board.getPieceCell(i, j).getTeam() == player.getTeam()) {
					currposa = i;
					currposb = j;
				}
			}
		}

		if (player.getTeam() == Colour.WHITE && currposb != 0 && currposb != 7
				&& board.getPieceCell(currposa + 1, currposb).getPieceColour() != Colour.BLANK
				&& board.getPieceCell(currposa + 1, currposb - 1).getPieceColour() != Colour.BLANK
				&& board.getPieceCell(currposa + 1, currposb + 1).getPieceColour() != Colour.BLANK) {
			setFinished(deadlockFlag);
			deadlockFlag = true;
			System.out.println("Unable to move, Next players turn...");
			return false;
		} else if (player.getTeam() == Colour.WHITE && currposb == 0
				&& board.getPieceCell(currposa + 1, currposb + 1).getPieceColour() != Colour.BLANK
				&& board.getPieceCell(currposa + 1, currposb).getPieceColour() != Colour.BLANK) {
			setFinished(deadlockFlag);
			deadlockFlag = true;
			System.out.println("Unable to move, Next players turn...");
			return false;
		} else if (player.getTeam() == Colour.WHITE && currposb == 7
				&& board.getPieceCell(currposa + 1, currposb).getPieceColour() != Colour.BLANK
				&& board.getPieceCell(currposa + 1, currposb - 1).getPieceColour() != Colour.BLANK) {
			setFinished(deadlockFlag);
			deadlockFlag = true;
			System.out.println("Unable to move, Next players turn...");
			return false;
		} else if (player.getTeam() == Colour.BLACK && currposb != 0 && currposb != 7
				&& board.getPieceCell(currposa - 1, currposb).getPieceColour() != Colour.BLANK
				&& board.getPieceCell(currposa - 1, currposb - 1).getPieceColour() != Colour.BLANK
				&& board.getPieceCell(currposa - 1, currposb + 1).getPieceColour() != Colour.BLANK) {
			setFinished(deadlockFlag);
			deadlockFlag = true;
			System.out.println("Unable to move, Next players turn...");
			return false;
		} else if (player.getTeam() == Colour.BLACK && currposb == 0
				&& board.getPieceCell(currposa - 1, currposb).getPieceColour() != Colour.BLANK
				&& board.getPieceCell(currposa - 1, currposb + 1).getPieceColour() != Colour.BLANK) {
			setFinished(deadlockFlag);
			System.out.println("Unable to move, Next players turn...");
			deadlockFlag = true;
			return false;
		} else if (player.getTeam() == Colour.BLACK && currposb == 0
				&& board.getPieceCell(currposa - 1, currposb).getPieceColour() != Colour.BLANK
				&& board.getPieceCell(currposa - 1, currposb - 1).getPieceColour() != Colour.BLANK) {
			setFinished(deadlockFlag);
			deadlockFlag = true;
			System.out.println("Unable to move, Next players turn...");
			return false;
		} else {
			return true;
		}

	}

	// if (player.getTeam() == Colour.WHITE
	// && board.getPieceCell(currposa + 1, currposb).getPieceColour() !=
	// Colour.BLANK
	// && board.getPieceCell(currposa + 1, currposb - 1).getPieceColour() !=
	// Colour.BLANK
	// && board.getPieceCell(currposa + 1, currposb + 1).getPieceColour() !=
	// Colour.BLANK) {
	// return false;
	// } else if (player.getTeam() == Colour.BLACK
	// && board.getPieceCell(currposa - 1, currposb).getPieceColour() !=
	// Colour.BLANK
	// && board.getPieceCell(currposa - 1, currposb - 1).getPieceColour() !=
	// Colour.BLANK
	// && board.getPieceCell(currposa - 1, currposb + 1).getPieceColour() !=
	// Colour.BLANK) {
	// return false;
	// } else {
	// return true;
	// }
	//
	// }

	private boolean movelegality(Player player, int currposa, int currposb, int newposa, int newposb,
			Colour previousColour) {
		
		if(currposa == newposa && currposb == newposb){
			System.out.println("Cant move nowhere");
			return false;
		}

		// if no piece at starting point
		if (board.getPieceCell(currposa, currposb).getPieceColour() == Colour.BLANK) {
			System.out.println("No piece at starting location");
			return false;
		}

		// Correct colour of tower
		if (previousColour != Colour.BLANK
				&& board.getPieceCell(currposa, currposb).getPieceColour() != previousColour) {
			System.out.println("Wrong colour of tower");
			return false;
		}

		// diagonal check or vertical
		if (Math.abs(currposa - newposa) != Math.abs(currposb - newposb) && currposb != newposb) {
			System.out.println("please move vertically or diagonally");
			return false;
		}

		if (player.getTeam() == Colour.WHITE) {
			// Cannot move other players pieces
			if (board.getPieceCell(currposa, currposb).getTeam() != Colour.WHITE) {
				System.out.println("Cannot move other players pieces");
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
					if (board.getPieceCell(i, currposb).getPieceColour() != Colour.BLANK) {
						System.out.println("Path is Blocked by tower");
						return false;
					}

				}
			}

		}

		else if (player.getTeam() == Colour.BLACK) {

			if (currposa < newposa) {
				System.out.println("Moving wrong way");
				return false;
				// check occupied

			}
			if (board.getPieceCell(currposa, currposb).getTeam() != Colour.BLACK) {
				System.out.println("Cannot move other players pieces");
				return false;

			}
			// Occupied for vert movement
			if (currposb == newposb) {
				// for loop beginning at currposa + 1 ending at newposa
				for (int i = currposa - 1; i >= newposa; i--) {
					if (board.getPieceCell(i, currposb).getPieceColour() != Colour.BLANK) {
						System.out.println(" Path Blocked by tower");
						return false;
					}

				}
			}
			// Occupied for diag movement left
			if (currposb > newposb) { // diag left
				for (int i = 1; i >= newposa; i++) {
					if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() != Colour.BLANK) {
						System.out.println(" Path Blocked by tower");
						return false;
					}

				}
			}

			// Occupied for diag movement right
			if (currposb < newposb) { // diag right
				for (int i = 1; i >= newposa; i++) {
					if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() != Colour.BLANK) {
						System.out.println(" Path Blocked by tower");
						return false;
					}

				}
			}

		}

		return true;

	}
	
	public Piece nextPieceMove(Colour prev,Player player){
		if(player.getTeam() == Colour.WHITE){
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
				if(board.getPieceCell(i, j).getPieceColour() == prev && board.getPieceCell(i, j).getTeam() == Colour.WHITE){
					return board.getPieceCell(i, j);
				}
				else if(board.getPieceCell(i, j).getPieceColour() == prev){
					return board.getPieceCell(i, j);
				}
				}
			}
		}
		return null;
	}

}
