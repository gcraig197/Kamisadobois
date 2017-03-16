import java.util.Scanner;

public class State {
	private boolean finished;
	private Board board;
	private Boolean deadlockFlag;
	private Scanner sc;

	

	public State(boolean speedgame) {
		finished = false;
		board = new Board();
		board.Setup();
		sc = new Scanner(System.in);
		deadlockFlag = false;

	}

	public void printGame() {
		board.printCurrentBoard();
	}

	public Colour move(Player player, Colour previousColour) {

		if (deadLockCheck(player, previousColour) == false) {
			return board.getColour(board.getLastPieceX(player, previousColour),
					board.getLastPieceY(player, previousColour));
		}

		System.out.println("\n\nPlease enter your move using the format");
		System.out.println("ABXY");
		System.out.println("A B = Coords of Current Piece\nX Y = Coords of New Position");
		System.out.println("Type quit at any time to quit");

		
		int[] moves;
		String input = null;
		input = sc.nextLine();
		if (input.toLowerCase().contains("quit")) {
			System.out.println("Quitting Game...");
			System.exit(0);
		} else if (input.toLowerCase().equals("")) {
			System.out.println("Please enter a valid move or Quit...");
			move(player, previousColour);
		}

		else {

			moves = new int[input.length()];

			for (int i = 0; i < input.length(); i++) {
				moves[i] = input.charAt(i) - '0';
			}


			int currposa = moves[0];
			int currposb = moves[1];
			int newposa = moves[2];
			int newposb = moves[3];

			if (movelegality(player, currposa, currposb, newposa, newposb, previousColour)) {
				board.setPieceCell(newposa, newposb, board.getPieceCell(currposa, currposb));

				board.setPieceCell(currposa, currposb, board.getBlankPiece());
				deadlockFlag = false;
				return board.getColour(newposa, newposb);
			}

			else {
				move(player, previousColour);
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

			if (board.getPieceCell(currposa, currposb).getTeam() != Colour.WHITE) {
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

			if (currposb < newposb) {
				System.out.println("Temp Error  115");
				return false;
				// check occupied

			}
			if (board.getPieceCell(currposa, currposb).getTeam() != Colour.BLACK) {
				System.out.println("Temp Error  121");
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

}
