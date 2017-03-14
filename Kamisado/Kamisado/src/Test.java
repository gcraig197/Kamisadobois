

public class Test {
public static void main(String[] args) {
	Board board = new Board();
	board.Setup();
	board.printCurrentBoard();
	
	GUI gui = new GUI();
	gui.main(null);

//	board.move(0, 0, 5, 5);
//	System.out.println();
//	System.out.println();
//	board.printCurrentBoard();
//	
//	
//	board.move(0, 0, 0, 5);
	
//	board.Setup();
//	board.printCurrentBoard();
	
	
}
}
