package game;
import java.util.ArrayList;

public class Player {
public Player(Colour colour) {
	if(colour == Colour.WHITE){
		Piece wOrange = new Piece('w',Colour.ORANGE);
		Piece wBrown = new Piece('w',Colour.BROWN);
		Piece wDBlue = new Piece('w',Colour.DBLUE);
		Piece wLBLue = new Piece('w',Colour.ORANGE);
		Piece wPink = new Piece('w',Colour.PINK);
		Piece wYellow = new Piece('w',Colour.YELLOW);
	}
}

public void getMove(Board board){
	
}

public void interrupt(){
	
}
public void quit(){
	System.exit(0);
}

}
