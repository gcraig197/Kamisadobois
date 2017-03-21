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
	File out;

	public SaveManager() {
		stack = new Stack();
		out = new File("src/out");
	}

	public void saveBoard(Board board) {
		stack.push(board);

	}

	public Board undo() {
		return stack.pop();
	}

	public void save() throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(out);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Piece p = stack.peek().getPieceCell(row, col); // Contents of //
																// one square of
																// board.
				if (p.getTeam() == Colour.WHITE) {
					pw.print("" + p.getTeam() + " " + p.getPieceColour() + " " + row + " " + col + "\n");
				} else if (p.getTeam() == Colour.BLACK) {
					pw.print("" + p.getTeam() + " " + p.getPieceColour() + " " + row + " " + col + "\n");
				}
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
		
		Board newBoard = new Board();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j <8; j++) {
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
			
				if (team.equals("WHITE")) {
					t = Colour.WHITE;
				}
				else {
					t = Colour.BLACK;
				}
				
				if (colour.equals("ORANGE")) {
					c = Colour.ORANGE;
				}
				else if (colour.equals("DBLUE")) {
					c = Colour.DBLUE;
				}
				else if (colour.equals("LBLUE")) {
					c = Colour.LBLUE;
				}
				else if (colour.equals("PINK")) {
					c = Colour.PINK;
				}
				else if (colour.equals("YELLOW")) {
					c = Colour.YELLOW;
				}
				else if (colour.equals("RED")) {
					c = Colour.RED;
				}
				else if (colour.equals("GREEN")) {
					c = Colour.GREEN;
				}
				else if (colour.equals("BROWN")) {
					c = Colour.BROWN;
				}
				
				Piece p = new Piece(c,t);
				newBoard.setPieceCell(x, y, p);
			
			}

		
		return newBoard;
	}
}
