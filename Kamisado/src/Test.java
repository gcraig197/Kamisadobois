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
	
		
//		Board board = new Board();
//		board.Setup();
//		board.printCurrentBoard();
//		Board board2 = new Board();
//		board2.setUpDeadlock();
//		board2.printCurrentBoard();
//		
//		board.setPieceGrid(board2.getPieceGrid());
//		board.printCurrentBoard();
		
//		LeaderBoard leaderboard = new LeaderBoard();
//		leaderboard.loadLeaderBoard();
//		Player p1 = new Player();
//		Player p2 = new Player();
//		p1.setName("Durr");
//		p2.setName("Hurr");
//
//		leaderboard.saveScore(p1, 20);
//		leaderboard.saveScore(p2, 5);
//		leaderboard.saveLeaderBoard();
		
		ScoreBoard sb = new ScoreBoard();
		sb.populate();
		Player p = new Player();
		p.setName("Test");
		sb.saveScore(p, 20);
		sb.saveToFile();
		sb.clearArray();
		sb.load();
		
		
		
		
		//GameMenuGUI g = new GameMenuGUI();
	}
	
	

}
