/**
 * 
 */

/**
 * @author Gavin
 *
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class GameDriver {
	
	public static void main(String[] args) throws FileNotFoundException  {
		StartMenuGUI gui = new StartMenuGUI();
		while(gui.isFinished() == false){
			try {
				TimeUnit.MILLISECONDS.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(gui.isAI()){
			AIGame a = new AIGame();
			a.setup();
			a.play();
		}
		else if(gui.isTwoPlayer()){
			TwoPlayerGame t = new TwoPlayerGame();
			t.setup();
			t.play();
		}
		else{
			StartMenuGUI g = new StartMenuGUI();
		}
	}


	
	
}


