import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AIState {
	private boolean finished;
	private Board board;
	private Boolean deadlockFlag;
	private Scanner sc;
	private BoardGUI gui;
	private SaveManager save;
	private Colour lastColour;

	public AIState(boolean speedgame) {
		finished = false;
		board = new Board();
		board.Setup();
		sc = new Scanner(System.in);
		deadlockFlag = false;
		gui = new BoardGUI(board);
		gui.refresh(board);
		save = new SaveManager();
		lastColour = Colour.BLANK;
		Board newBoard = new Board();
		newBoard.Setup();
		save.saveGame(board);
		save.saveBoard(newBoard, lastColour);

	}

	public void printGame() {
		board.printCurrentBoard();
	}

	public Colour move(Player player, Colour previousColour, int turncount) {

		save.saveGame(board);

		if (deadLockCheck(player, previousColour) == false) {
			System.out.println(player.getTeam());
			return board.getColour(board.getLastPieceX(player, previousColour),
					board.getLastPieceY(player, previousColour));
		}

		if (turncount == 0) {

			// wait for click
			while (gui.canMove() == false) {
				try {
					TimeUnit.MILLISECONDS.sleep(20);
					if (gui.canSave()) {
						System.out.println("Turn count test!! " + turncount);
						save(previousColour, turncount);
						gui.setCanSave(false);
					}
				
					if(gui.canLoad()){
						try {
							load();
							turncount = getLoadedInt();
							previousColour = getLoadedColour();
							gui.setCanLoad(false);
							gui.refresh(board);
							previousColour = move(player,previousColour, turncount);
							return previousColour;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (gui.canUndo() && turncount != 0) {
						undo();
						undoColour();
						gui.refresh(board);
						gui.setCanUndo(false);
						previousColour = move(player, lastColour, turncount - 2);
						return previousColour;

					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int currposa = gui.getX();
			int currposb = gui.getY();
			gui.setCanMove(false);
			gui.availableMoves(currposa, currposb, player);

			// wait for click

			while (gui.canMove() == false) {
				try {
					TimeUnit.MILLISECONDS.sleep(20);
					if (gui.canSave()) {
						System.out.println("Turn count test!! " + turncount);
						save(previousColour, turncount);
						gui.setCanSave(false);
					}
					if(gui.canLoad()){
						try {
							load();
							turncount = getLoadedInt();
							previousColour = getLoadedColour();
							gui.setCanLoad(false);
							gui.refresh(board);
							previousColour = move(player,previousColour, turncount);
							return previousColour;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (gui.canUndo() && turncount != 0) {
						undo();
						undoColour();
						gui.setCanUndo(false);
						previousColour = move(player, lastColour, turncount - 2);
						return previousColour;

					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int newposa = gui.getX();
			int newposb = gui.getY();
			if (movelegality(player, currposa, currposb, newposa, newposb, previousColour)) {

				save.saveBoard(cloneBoard(), previousColour);
				System.out.println("!!!SAVED BOARD!!!:");
				board.setPieceCell(newposa, newposb, board.getPieceCell(currposa, currposb));

				board.setPieceCell(currposa, currposb, board.getBlankPiece());
				deadlockFlag = false;
				gui.refresh(board);
				gui.setCanMove(false);
				System.out.println("return point");
				System.out.println(board.getColour(newposa, newposb));
				return board.getColour(newposa, newposb);
			}

			else {
				gui.refresh(board);
				gui.setCanMove(false);
				previousColour = move(player, previousColour, turncount);
			}
		} else {

			int currposa = board.getLastPieceX(player, previousColour);
			int currposb = board.getLastPieceY(player, previousColour);
			System.out.println("(" + currposa + "," + currposb + ")");
			gui.setCanMove(false);
			gui.availableMoves(currposa, currposb, player);

			// Wait for Click

			while (gui.canMove() == false) {
				try {
					TimeUnit.MILLISECONDS.sleep(20);
					if (gui.canSave()) {
						System.out.println("Turn count test!! " + turncount);
						save(previousColour, turncount);
						gui.setCanSave(false);
					}
					if(gui.canLoad()){
						try {
							load();
							turncount = getLoadedInt();
							previousColour = getLoadedColour();
							gui.setCanLoad(false);
							gui.refresh(board);
							previousColour = move(player,previousColour, turncount);
							return previousColour;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (gui.canUndo()) {
						undo();
						undoColour();
						gui.setCanUndo(false);
						gui.refresh(board);
						previousColour = move(player, lastColour, turncount - 2);
						return previousColour;

					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int newposa = gui.getX();
			int newposb = gui.getY();

			if (movelegality(player, currposa, currposb, newposa, newposb, previousColour)) {

				save.saveBoard(cloneBoard(), previousColour);
				board.setPieceCell(newposa, newposb, board.getPieceCell(currposa, currposb));
				board.setPieceCell(currposa, currposb, board.getBlankPiece());
				deadlockFlag = false;
				gui.refresh(board);
				gui.setCanMove(false);
				System.out.println("return point");
				System.out.println(board.getColour(newposa, newposb));
				return board.getColour(newposa, newposb);
			}

			else {
				gui.setCanMove(false);
				previousColour = move(player, previousColour, turncount);
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
			if (finished == true) {
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
		if (player.getTeam() == Colour.WHITE) {
			if (currposb != 0 && currposb != 7
					&& board.getPieceCell(currposa + 1, currposb).getPieceColour() != Colour.BLANK
					&& board.getPieceCell(currposa + 1, currposb - 1).getPieceColour() != Colour.BLANK
					&& board.getPieceCell(currposa + 1, currposb + 1).getPieceColour() != Colour.BLANK) {
				setFinished(deadlockFlag);
				deadlockFlag = true;
				System.out.println("Unable to move, Next players turn...");
				return false;
			} else if (currposb == 0 && board.getPieceCell(currposa + 1, currposb + 1).getPieceColour() != Colour.BLANK
					&& board.getPieceCell(currposa + 1, currposb).getPieceColour() != Colour.BLANK) {
				setFinished(deadlockFlag);
				deadlockFlag = true;
				System.out.println("Unable to move, Next players turn...");
				return false;
			} else if (currposb == 7 && board.getPieceCell(currposa + 1, currposb).getPieceColour() != Colour.BLANK
					&& board.getPieceCell(currposa + 1, currposb - 1).getPieceColour() != Colour.BLANK) {
				setFinished(deadlockFlag);
				deadlockFlag = true;
				System.out.println("Unable to move, Next players turn...");
				return false;

			}
		} else if (player.getTeam() == Colour.BLACK) {
			if (currposb != 0 && currposb != 7
					&& board.getPieceCell(currposa - 1, currposb).getPieceColour() != Colour.BLANK
					&& board.getPieceCell(currposa - 1, currposb - 1).getPieceColour() != Colour.BLANK
					&& board.getPieceCell(currposa - 1, currposb + 1).getPieceColour() != Colour.BLANK) {
				setFinished(deadlockFlag);
				deadlockFlag = true;
				System.out.println("Unable to move, Next players turn...");
				return false;
			} else if (currposb == 0 && board.getPieceCell(currposa - 1, currposb).getPieceColour() != Colour.BLANK
					&& board.getPieceCell(currposa - 1, currposb + 1).getPieceColour() != Colour.BLANK) {
				setFinished(deadlockFlag);
				System.out.println("Unable to move, Next players turn...");
				deadlockFlag = true;
				return false;
			} else if (currposb == 7 && board.getPieceCell(currposa - 1, currposb).getPieceColour() != Colour.BLANK
					&& board.getPieceCell(currposa - 1, currposb - 1).getPieceColour() != Colour.BLANK) {
				setFinished(deadlockFlag);
				deadlockFlag = true;
				System.out.println("Unable to move, Next players turn...");
				return false;
			}

		}
		return true;

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

		if (currposa == newposa && currposb == newposb) {
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
			// Occupied for diag movement left
			if (currposb > newposb) { // diag left
				for (int i = 1; i <= newposa - currposa; i++) {
					if (board.getPieceCell(currposa + i, currposb - i).getPieceColour() != Colour.BLANK) {
						System.out.println(" Path Blocked by tower");
						return false;
					}

				}
			}

			// Occupied for diag movement right
			if (currposb < newposb) { // diag right
				for (int i = 1; i <= newposa - currposa; i++) {
					if (board.getPieceCell(currposa + i, currposb + i).getPieceColour() != Colour.BLANK) { // white
						System.out.println(" Path Blocked by tower");
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
				for (int i = 1; i <= currposa - newposa; i++) {
					if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() != Colour.BLANK) {
						System.out.println(" Path Blocked by tower");
						return false;
					}

				}
			}

			// Occupied for diag movement right
			if (currposb < newposb) { // diag right
				for (int i = 1; i <= currposa - newposa; i++) {
					if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() != Colour.BLANK) { // black
						System.out.println(" Path Blocked by tower");
						return false;
					}

				}
			}

		}

		return true;

	}

	public Piece nextPieceMove(Colour prev, Player player) {
		if (player.getTeam() == Colour.WHITE) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board.getPieceCell(i, j).getPieceColour() == prev
							&& board.getPieceCell(i, j).getTeam() == Colour.WHITE) {
						return board.getPieceCell(i, j);
					} else if (board.getPieceCell(i, j).getPieceColour() == prev) {
						return board.getPieceCell(i, j);
					}
				}
			}
		}
		return null;
	}

	public Colour hardAIMove(AIPlayer player, Player opponent, Colour previousColour) {

		Random rng = new Random();
		ArrayList<AImove> array = new ArrayList<AImove>();

		int currposa = board.getLastPieceX(player, previousColour);
		int currposb = board.getLastPieceY(player, previousColour);
		Piece p = board.getPieceCell(currposa, currposb);

		outer: for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (movelegality(player, currposa, currposb, i, j, previousColour)) { // If
																						// can
																						// move
																						// legally
					Colour newpreviousColour = board.getColour(i, j);
					int oppposa = board.getLastPieceX(opponent, newpreviousColour);
					int oppposb = board.getLastPieceY(opponent, newpreviousColour);
					if (canWin(opponent, oppposa, oppposb, newpreviousColour) == false) { // can
																							// next
																							// player
																							// win
																							// from
																							// next
																							// move?
						AImove move = new AImove(i, j);
						array.add(move);
						if (i == 0) { // can win
							board.setPieceCell(i, j, p);
							break outer;
						}
					}

				}

			}
		}
		if (array.isEmpty()) {
			computeAIMove(player, opponent, previousColour);
		} else {
			AImove aimove = array.get(rng.nextInt(array.size()));
			board.setPieceCell(aimove.getI(), aimove.getJ(), p);
			board.setPieceCell(currposa, currposb, board.getBlankPiece());
			gui.refresh(board);
			previousColour = board.getColour(aimove.getI(), aimove.getJ());
			lastColour = previousColour;
		}
		// move
		// aimove.getApos();
		// aimove.getBpos();

		return previousColour;

	}

	public Colour computeAIMove(AIPlayer player, Player opponent, Colour previousColour) {
		Random rng = new Random();
		ArrayList<AImove> array = new ArrayList<AImove>();

		int currposa = board.getLastPieceX(player, previousColour);
		int currposb = board.getLastPieceY(player, previousColour);
		Piece p = board.getPieceCell(currposa, currposb);

		outer: for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (movelegality(player, currposa, currposb, i, j, previousColour)) {
					AImove move = new AImove(i, j);
					array.add(move);
					if (i == 0) { // can win
						board.setPieceCell(i, j, p);
						previousColour = board.getColour(i, j);
						board.setPieceCell(currposa, currposb, board.getBlankPiece());
						lastColour = previousColour;
						gui.refresh(board);
						break outer;
					}

				}

			}
		}

		AImove aimove = array.get(rng.nextInt(array.size()));
		board.setPieceCell(aimove.getI(), aimove.getJ(), p);
		board.setPieceCell(currposa, currposb, board.getBlankPiece());
		gui.refresh(board);
		previousColour = board.getColour(aimove.getI(), aimove.getJ());
		lastColour = previousColour;
		// move
		// aimove.getApos();
		// aimove.getBpos();

		return previousColour;

	}

	public boolean canWin(Player player, int currposa, int currposb, Colour previousColour) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (movelegality(player, currposa, currposb, i, j, previousColour)) {
					if (i == 7) { // can win
						return true;

					}

				}

			}
		}
		return false;
	}

	public void save(Colour previousColour,int turncount) throws FileNotFoundException {
		save.save(lastColour, turncount);

	}

	public void load() throws IOException {
		board = save.load();
		
	}
	
	public Colour getLoadedColour(){
		return save.getColour();
	}
	
	public int getLoadedInt(){
		return save.getTurncount();
	}

	public void undo() {
		Board temp = save.undo();
		System.out.println("!!!!!!IN UNDO!!!!!!!");
		System.out.println("temp:");
		temp.printCurrentBoard();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.setPieceCell(i, j, temp.getPieceCell(i, j));
			}
		}

		// for (int i = 0; i < 8; i++) {
		// System.out.println();
		// for (int j = 0; j < 8; j++) {
		// System.out.print(temp.getPieceCell(i, j).getPieceColour());
		// board.setPieceCell(i, j, temp.getPieceCell(i, j));
		//
		// }
		//
		// }

	}

	public Board cloneBoard() {
		Board temp = new Board();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				temp.setPieceCell(i, j, board.getPieceCell(i, j));
			}
		}
		return temp;
	}

	public void undoColour() {
		lastColour = save.undoColour();
	}

	public BoardGUI getGUI() {
		return gui;
	}

}
