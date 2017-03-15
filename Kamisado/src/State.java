import java.util.Scanner;

public class State {
	int turnCount;
	boolean finished;
	Colour previousColour;
	Board board;
	Scanner sc;

	public State() {
		turnCount = 0;
		finished = false;

		previousColour = Colour.BLANK;
		board = new Board();
		board.Setup();
		sc = new Scanner(System.in);

	}

	public void printGame() {
		board.printCurrentBoard();
	}

	public Colour move(Player player, Colour previousColour) {

		System.out.println("\n\nPlease enter your move using the format");
		System.out.println("A B X Y");
		System.out.println("A B = Coords of Current Piece\nX Y = Coords of New Position");

		String input = null;
			input = sc.nextLine();
			if (input.toLowerCase().contains("quit")) {
				System.exit(0);
			}

			else {
				
				int[] moves = new int[4];
				for (int i = 0; i < 4; i++) {
					moves[i] = input.charAt(i);
				}
				int currposa = moves[0];
				int currposb = moves[1];
				int newposa = moves[2];
				int newposb = moves[3];

				if (movelegality(player, currposa, currposb, newposa, newposb)) {
					board.setPieceCell(newposa, newposb, board.getPieceCell(currposa, currposb));

					board.setPieceCell(currposa, currposb, board.getBlankPiece());
					return board.getColour(newposa, newposb);
				} else {
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
		}
		return false;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}

	public int getTurnCount() {
		return turnCount;
	}

	private boolean movelegality(Player player, int currposa, int currposb, int newposa, int newposb) {

		// if no piece at starting point
		if (board.getPieceCell(currposa, currposb).getPieceColour() == Colour.BLANK) {
			System.out.println("No piece at starting location");
			return false;
		}

		// Correct colour of tower
		if (turnCount != 0 && board.getPieceCell(currposa, currposb).getPieceColour() != previousColour) {
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

	public int getPlayerTurn() {
		return turnCount;
	}

	public void setPlayerTurn(int turnCount) {
		this.turnCount = turnCount;
	}

}
