import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		
//		GameDriver game = new GameDriver();
//		game.setup();
//		game.play();
		
//		GameDriver g = new GameDriver();
//		g.setup();
//		g.play();
		State s = new State(false);
	//	s.save();
		s.load();
		s.printGame();
		
		
		
		//GameMenuGUI g = new GameMenuGUI();
	}
	
	

}
