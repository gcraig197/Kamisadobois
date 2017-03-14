

public class Board {
	private Colour[][] boardGrid;
	private Piece [][] pieceGrid;
	
	
	/*
	 * Creates a new kamisado board
	 */
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
	
	/*
	 * Possibly unnecessary
	 */
	public Colour[][] getBoard() {
		return boardGrid;
	}

	/*
	 * Possibly unnecessary
	 */
	public void setBoard(Colour[][] board) {
		this.boardGrid = board;
	}

	/*
	 * Returns the current layout of player towers
	 */
	public Piece[][] getPieceGrid() {
		return pieceGrid;
	}

	/*
	 * Sets the active player tower layout to the Array Passed in
	 */
	public void setPieceGrid(Piece[][] pieceGrid) {
		this.pieceGrid = pieceGrid;
	}

	/*
	 * Player move SELECT PIECE SELECT CELL TO MOVE TO VERIFY MOVE IS LEGAL MOVE
	 * PIECE X REMOVE OLD PIECE X
	 */
	public void make(String move) {

	}

	/*
	 * Returns the colour of the specified board cell
	 */
	public Colour getColour(int x, int y) {

		return boardGrid[x][y];

	}

	/*
	 * Basic Functionality for moving pieces to other cells on the board
	 */
	public void move(int a, int b, int x, int y) {
		Piece movedPiece = getPiece(a, b);
		String move = a + "" + b + "" + x + "" + y + "" ;
		pieceGrid[x][y] = movedPiece;
		pieceGrid[a][b] = null;
	}
	
//	public int moveLegalityTest(String move){
//		
//		char[] parts = move.toCharArray();
//		int currposx = Integer.parseInt(Character.toString(parts[0]));
//		int currposy = Integer.parseInt(Character.toString(parts[1]));
//		int newposx = Integer.parseInt(Character.toString(parts[2]));
//		int newposy = Integer.parseInt(Character.toString(parts[3]));
//		
//		if 
//		
//
//	
//		
//		
//		
//	}

	/*
	 * 
	 */
	public Piece getPiece(int a, int b) {
		return pieceGrid[a][b];
	}

	/*
	 * End the game if a win state is entered or player resigns
	 */
	public void gameOver() {

	}

	/*
	 * Initialize pieceGrid values to null and sets up the starting tower
	 * positions of the game
	 */
	public void Setup() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieceGrid[i][j] = new Piece('n',Colour.BLANK);

			}
		}
		pieceGrid[0][0] = new Piece('w', Colour.ORANGE);
		pieceGrid[0][1] = new Piece('w', Colour.DBLUE);
		pieceGrid[0][2] = new Piece('w', Colour.LBLUE);
		pieceGrid[0][3] = new Piece('w', Colour.PINK);
		pieceGrid[0][4] = new Piece('w', Colour.YELLOW);
		pieceGrid[0][5] = new Piece('w', Colour.RED);
		pieceGrid[0][6] = new Piece('w', Colour.GREEN);
		pieceGrid[0][7] = new Piece('w', Colour.BROWN);

		pieceGrid[7][0] = new Piece('b', Colour.BROWN);
		pieceGrid[7][1] = new Piece('b', Colour.GREEN);
		pieceGrid[7][2] = new Piece('b', Colour.RED);
		pieceGrid[7][3] = new Piece('b', Colour.YELLOW);
		pieceGrid[7][4] = new Piece('b', Colour.PINK);
		pieceGrid[7][5] = new Piece('b', Colour.LBLUE);
		pieceGrid[7][6] = new Piece('b', Colour.DBLUE);
		pieceGrid[7][7] = new Piece('b', Colour.ORANGE);

	}

	/*
	 * Prints the current layout of the board
	 */
	public void printCurrentBoard() {
		for (int i = 0; i < 8; i++) {
			System.out.println();
			for (int j = 0; j < 8; j++) {
				if(pieceGrid[i][j].getColour() == Colour.BLANK){
					System.out.println(boardGrid[i][j]);
				}
				else if(pieceGrid[i][j].getColour() != Colour.BLANK){
					System.out.println(pieceGrid[i][j].getColour() + " on " + boardGrid[i][j]);
				}

			}
		}
	}
}
