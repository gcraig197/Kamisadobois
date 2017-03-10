package game;

public class Board {
	private Colour[][] boardGrid;
	
	public Board() {
		boardGrid = new Colour[][] {{Colour.ORANGE,Colour.DBLUE,Colour.LBLUE,Colour.PINK,Colour.YELLOW,Colour.RED,Colour.GREEN,Colour.BROWN},
							{Colour.RED,Colour.ORANGE,Colour.PINK,Colour.GREEN,Colour.DBLUE,Colour.YELLOW,Colour.BROWN,Colour.LBLUE},
							{Colour.GREEN,Colour.PINK,Colour.ORANGE,Colour.RED,Colour.LBLUE,Colour.BROWN,Colour.YELLOW,Colour.DBLUE},
							{Colour.PINK,Colour.LBLUE,Colour.DBLUE,Colour.ORANGE,Colour.BROWN,Colour.GREEN,Colour.RED,Colour.YELLOW},
							{Colour.YELLOW,Colour.RED,Colour.GREEN,Colour.BROWN,Colour.ORANGE,Colour.DBLUE,Colour.LBLUE,Colour.PINK},
							{Colour.DBLUE,Colour.YELLOW,Colour.BROWN,Colour.LBLUE,Colour.RED,Colour.ORANGE,Colour.PINK,Colour.GREEN},
							{Colour.LBLUE,Colour.BROWN,Colour.YELLOW,Colour.DBLUE,Colour.GREEN,Colour.PINK,Colour.ORANGE,Colour.RED},
							{Colour.BROWN,Colour.GREEN,Colour.RED,Colour.YELLOW,Colour.PINK,Colour.LBLUE,Colour.DBLUE,Colour.ORANGE}
							};
				
	}
	
	public Colour[][] getBoard() {
		return boardGrid;
	}

	public void setBoard(Colour[][] board) {
		this.boardGrid = board;
	}

	public void make(String move){
		
	}
	
	public Colour getColour(int x, int y){
		
		return boardGrid[x][y];
		
	}

	public void gameOver(){
		
	}
}
