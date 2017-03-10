

public class Test {
public static void main(String[] args) {
	Board board = new Board();
	GUI gui = new GUI();
	board.Setup();
	board.printCurrentBoard();
	board.move(0, 0, 5, 5);
	System.out.println();
	System.out.println();
	board.printCurrentBoard();
	
	
	
	
//	board.Setup();
//	board.printCurrentBoard();
	
	
}
}
