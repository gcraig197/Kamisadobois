import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		
		//GameDriver game = new GameDriver();
		//game.setup();
		//game.play();
		
//		GameDriver g = new GameDriver();
//		State s = new State(false);
//		s.save();
//		s.load();
//		s.printGame();
		
		
//		AIState state = new AIState(false);
	
		
		Board board = new Board();
		board.Setup();
		board.printCurrentBoard();
		Board board2 = new Board();
		board2.setUpDeadlock();
		board2.printCurrentBoard();
		
		board.setPieceGrid(board2.getPieceGrid());
		board.printCurrentBoard();
		
		
		
		//GameMenuGUI g = new GameMenuGUI();
	}
	
	

}
