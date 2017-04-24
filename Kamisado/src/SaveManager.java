import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class SaveManager {
	Stack<Board> stack;
	Stack<Board> saves;
	Stack<Colour> colStack;
	Stack<Colour> boardStack;
	File out;
	Colour colour;
	int turncount;
	int rounds;

	public SaveManager() {
		stack = new Stack<Board>();
		colStack = new Stack<Colour>();

		saves = new Stack<Board>();
		boardStack = new Stack<Colour>();
		out = new File("src/out");
		colour = Colour.BLANK;
		turncount = 0;

	}

	public void saveBoard(Board board, Colour lastColour) {
		stack.push(board);
		colStack.push(lastColour);

	}

	public void saveGame(Board board) {
		saves.push(board);
	}

	public Board undo() {
		return stack.pop();

	}

	public Board peek() {
		return stack.peek();
	}

	public Colour undoColour() {
		return colStack.pop();
	}

	public void save(Colour previousColour, int turncount, int rounds) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(out);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Piece p = saves.peek().getPieceCell(row, col); // Contents of 
																// one square of
																// board.
				if (p.getTeam() == Colour.WHITE) {
					pw.print("" + p.getTeam() + " " + p.getPieceColour() + " " + row + " " + col + " " + p.getTeeth() +  "\n");
				} else if (p.getTeam() == Colour.BLACK) {
					pw.print("" + p.getTeam() + " " + p.getPieceColour() + " " + row + " " + col + " " + p.getTeeth() + "\n");
				}
			}

		}

		
		pw.print(previousColour + " " + turncount + " " + rounds + "\n");
		System.out.println();

		// board loop
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Colour c = saves.peek().getColour(row, col);
				pw.println(c);
			}
		}

		pw.flush();
		pw.close();

	}

	public Board load() throws IOException {
		FileReader fr = new FileReader(out);
		BufferedReader br = new BufferedReader(fr);
		String s;
		String team;
		Colour t = Colour.BLANK;
		Colour c = Colour.BLANK;
		String colour;
		int x;
		int y;
		int teeth;

		Board newBoard = new Board();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				newBoard.setPieceCell(i, j, newBoard.getBlankPiece());

			}

		}

		for (int i = 0; i < 16; i++) {

			s = br.readLine();
			System.out.println(s);
			Scanner tokeniser = new Scanner(s);
			team = tokeniser.next();
			colour = tokeniser.next();

			x = tokeniser.nextInt();
			y = tokeniser.nextInt();
			teeth = tokeniser.nextInt();

			if (team.equals("WHITE")) {
				t = Colour.WHITE;
			} else {
				t = Colour.BLACK;
			}

			if (colour.equals("ORANGE")) {
				c = Colour.ORANGE;
			} else if (colour.equals("DBLUE")) {
				c = Colour.DBLUE;
			} else if (colour.equals("LBLUE")) {
				c = Colour.LBLUE;
			} else if (colour.equals("PINK")) {
				c = Colour.PINK;
			} else if (colour.equals("YELLOW")) {
				c = Colour.YELLOW;
			} else if (colour.equals("RED")) {
				c = Colour.RED;
			} else if (colour.equals("GREEN")) {
				c = Colour.GREEN;
			} else if (colour.equals("BROWN")) {
				c = Colour.BROWN;
			}

			Piece p = new Piece(c, t,teeth);
			newBoard.setPieceCell(x, y, p);

		}

		s = br.readLine();
		System.out.println(s);
		Scanner tokeniser = new Scanner(s);
		String loadedcolour = tokeniser.next();
		int loadedturncount = tokeniser.nextInt();
		int rounds = tokeniser.nextInt();

		if (loadedcolour.equals("ORANGE")) {
			this.colour = Colour.ORANGE;
		} else if (loadedcolour.equals("DBLUE")) {
			this.colour = Colour.DBLUE;
		} else if (loadedcolour.equals("LBLUE")) {
			this.colour = Colour.LBLUE;
		} else if (loadedcolour.equals("PINK")) {
			this.colour = Colour.PINK;
		} else if (loadedcolour.equals("YELLOW")) {
			this.colour = Colour.YELLOW;
		} else if (loadedcolour.equals("RED")) {
			this.colour = Colour.RED;
		} else if (loadedcolour.equals("GREEN")) {
			this.colour = Colour.GREEN;
		} else if (loadedcolour.equals("BROWN")) {
			this.colour = Colour.BROWN;
		}

		this.rounds = rounds;
		turncount = loadedturncount;
		System.out.println(this.colour);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				s = br.readLine();
				tokeniser = new Scanner(s);
				String boardcol = tokeniser.next();
				if (boardcol.equals("ORANGE")) {
					newBoard.setColour(row, col, Colour.ORANGE);
				} else if (boardcol.equals("DBLUE")) {
					newBoard.setColour(row, col, Colour.DBLUE);
				} else if (boardcol.equals("LBLUE")) {
					newBoard.setColour(row, col, Colour.LBLUE);
				} else if (boardcol.equals("PINK")) {
					newBoard.setColour(row, col, Colour.PINK);
				} else if (boardcol.equals("YELLOW")) {
					newBoard.setColour(row, col, Colour.YELLOW);
				} else if (boardcol.equals("RED")) {
					newBoard.setColour(row, col, Colour.RED);
				} else if (boardcol.equals("GREEN")) {
					newBoard.setColour(row, col, Colour.GREEN);
				} else if (boardcol.equals("BROWN")) {
					newBoard.setColour(row, col, Colour.BROWN);
				}
			}
		}

		return newBoard;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public int getTurncount() {
		return turncount;
	}

	public void setTurncount(int turncount) {
		this.turncount = turncount;
	}

}