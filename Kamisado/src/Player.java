

public class Player {
public Player(Colour colour) {
	if(colour == Colour.WHITE){
		Piece wOrange = new Piece('w',Colour.ORANGE);
		Piece wDBlue = new Piece('w',Colour.DBLUE);
		Piece wLBlue = new Piece('w',Colour.LBLUE);
		Piece wPink = new Piece('w',Colour.PINK);
		Piece wYellow = new Piece('w',Colour.YELLOW);
		Piece wRed = new Piece('w',Colour.RED);
		Piece wGreen = new Piece('w',Colour.GREEN);
		Piece wBrown = new Piece('w',Colour.BROWN);
	}
	else{
		Piece bOrange = new Piece('b',Colour.ORANGE);
		Piece bDBlue = new Piece('b',Colour.DBLUE);
		Piece bLBlue = new Piece('b',Colour.LBLUE);
		Piece bPink = new Piece('b',Colour.PINK);
		Piece bYellow = new Piece('b',Colour.YELLOW);
		Piece bRed = new Piece('b',Colour.RED);
		Piece bGreen = new Piece('b',Colour.GREEN);
		Piece bBrown = new Piece('b',Colour.BROWN);
	}
}

public void getMove(Board board){
	
}

public void interrupt(){
	
}

}
