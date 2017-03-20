

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
	
	
	public Colour[][] getBoard() {
		return boardGrid;
	}

	
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

	
	public Piece getPieceCell(int a, int b){
		return pieceGrid[a][b];
	}
	
	public void setPieceCell(int a, int b , Piece piece) {
		pieceGrid[a][b] = piece;
	}

	public Piece getBlankPiece(){
		Piece p = new Piece(Colour.BLANK,Colour.BLANK);
		return p;
	}
	/*
	 * Returns the colour of the specified board cell
	 */
	public Colour getColour(int x, int y) {

		return boardGrid[x][y];

	}
	
	public char getCharofColour(Colour colour){
		char c = ' ';
		
		 if(colour == Colour.ORANGE){
			c = 'O';
		}
		else if(colour == Colour.DBLUE){
			c = 'D';
		}
		else if(colour == Colour.LBLUE){
			c = 'L';
		}
		else if(colour == Colour.PINK){
			c = 'P';
		}
		else if(colour == Colour.YELLOW){
			c = 'Y';
		}
		else if(colour == Colour.RED){
			c = 'R';
		}
		else if(colour == Colour.GREEN){
			c = 'G';
		}
		else if(colour == Colour.BROWN){
			c = 'B';
		}
		else if(colour == Colour.BLANK){
			c = 'N';
		}
		
		return c;
	}


	

	/*
	 * Initialize pieceGrid values to null and sets up the starting tower
	 * positions of the game
	 */
	public void Setup() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieceGrid[i][j] = new Piece(Colour.BLANK,Colour.BLANK);

			}
		}
		pieceGrid[0][0] = new Piece(Colour.ORANGE, Colour.WHITE);
		pieceGrid[0][1] = new Piece(Colour.DBLUE, Colour.WHITE);
		pieceGrid[0][2] = new Piece(Colour.LBLUE, Colour.WHITE);
		pieceGrid[0][3] = new Piece(Colour.PINK, Colour.WHITE);
		pieceGrid[0][4] = new Piece(Colour.YELLOW, Colour.WHITE);
		pieceGrid[0][5] = new Piece(Colour.RED, Colour.WHITE);
		pieceGrid[0][6] = new Piece(Colour.GREEN, Colour.WHITE);
		pieceGrid[0][7] = new Piece(Colour.BROWN, Colour.WHITE);

		pieceGrid[7][0] = new Piece(Colour.BROWN, Colour.BLACK);
		pieceGrid[7][1] = new Piece(Colour.GREEN, Colour.BLACK);
		pieceGrid[7][2] = new Piece(Colour.RED, Colour.BLACK);
		pieceGrid[7][3] = new Piece(Colour.YELLOW, Colour.BLACK);
		pieceGrid[7][4] = new Piece(Colour.PINK, Colour.BLACK);
		pieceGrid[7][5] = new Piece(Colour.LBLUE, Colour.BLACK);
		pieceGrid[7][6] = new Piece(Colour.DBLUE, Colour.BLACK);
		pieceGrid[7][7] = new Piece(Colour.ORANGE, Colour.BLACK);

	}
	
	public void setUpDeadlock(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				pieceGrid[i][j] = new Piece(Colour.LBLUE,Colour.WHITE);

			}
		}
		
		for (int i = 4; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieceGrid[i][j] = new Piece(Colour.LBLUE,Colour.BLACK);

			}
		}
		
		pieceGrid[4][6] =  new Piece(Colour.BLANK,Colour.BLANK);
	}

	/*
	 * Prints the current layout of the board
	 */
	public void printCurrentBoard() {
		System.out.println();
		for (int i = 0; i < 8; i++) {
			System.out.println();
			for(int j = 0; j< 8; j++){
				System.out.print(getCharofColour(getPieceCell(i, j).getPieceColour()) + " on " + getCharofColour(getColour(i, j)) + "  ");
			}
		}
		System.out.println();
		System.out.println();
	}
	
	public int getLastPieceX(Player player, Colour previousColour){
		int currposa = 0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(getPieceCell(i, j).getPieceColour() == previousColour && getPieceCell(i, j).getTeam() == player.getTeam()){
					currposa = i;
				}
			}
		}
		return currposa;
	}
	
	public int getLastPieceY(Player player, Colour previousColour){
		int currposb = 0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(getPieceCell(i, j).getPieceColour() == previousColour && getPieceCell(i, j).getTeam() == player.getTeam()){
					currposb = j;
				}
			}
		}
		return currposb;
	}
	
	public int getPieceX(Piece piece){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(getPieceCell(i, j) == piece){
					return i;
				}
			}
		}
		return -1;
	}
	
	public int getPieceY(Piece piece){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(getPieceCell(i, j) == piece){
					return j;
				}
			}
		}
		return -1;
	}
}

