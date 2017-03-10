

public class Board {
	private Colour[][] boardGrid;
	private Piece [][] pieceGrid;
	
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
	                            pieceGrid = new Piece [8][8];
			
				
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
	public void move(int a,int b ,int x,int y){
		Piece movedPiece = getPiece(a,b);
		pieceGrid[x][y] = movedPiece;
		pieceGrid[a][b] = null;
	}

	public Piece getPiece(int a,int b){
		return pieceGrid[a][b];
	}

	public void gameOver(){
		
	}
	public void Setup(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) {
				pieceGrid[i][j] = null;
				
			}
		}
	pieceGrid[0][0] = new Piece('w',Colour.ORANGE);
	pieceGrid[0][1] = new Piece('w',Colour.DBLUE);
	pieceGrid[0][2] = new Piece('w',Colour.LBLUE);
	pieceGrid[0][3] = new Piece('w',Colour.PINK);
	pieceGrid[0][4] = new Piece('w',Colour.YELLOW);
	pieceGrid[0][5] = new Piece('w',Colour.RED);
	pieceGrid[0][6] = new Piece('w',Colour.GREEN);
	pieceGrid[0][7] = new Piece('w',Colour.BROWN);
	
	pieceGrid[7][0] = new Piece('b',Colour.BROWN);
	pieceGrid[7][1] = new Piece('b',Colour.GREEN);
	pieceGrid[7][2] = new Piece('b',Colour.RED);
	pieceGrid[7][3] = new Piece('b',Colour.YELLOW);
	pieceGrid[7][4] = new Piece('b',Colour.PINK);
	pieceGrid[7][5] = new Piece('b',Colour.LBLUE);
	pieceGrid[7][6] = new Piece('b',Colour.DBLUE);
	pieceGrid[7][7] = new Piece('b',Colour.ORANGE);

	
	}
	public void printCurrentBoard(){
		for (int i = 0; i < 8; i++){
			System.out.println();
			for (int j = 0; j < 8; j++) {
				if(pieceGrid[i][j] == null){
					System.out.print("null");
				}
				else{
					System.out.print(pieceGrid[i][j].getTeam());
				}
				
			}
		}
	}
}
