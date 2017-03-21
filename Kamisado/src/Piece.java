public class Piece {
	Colour pieceColour;
	Colour team;

	public Piece(Colour pieceColour, Colour team) {
		this.pieceColour = pieceColour;
		this.team = team;

	}

	public Colour getPieceColour() {
		return pieceColour;
	}

	public void setPieceColour(Colour pieceColour) {
		this.pieceColour = pieceColour;
	}

	public Colour getTeam() {
		return team;
	}

	public void setTeam(Colour team) {
		this.team = team;
	}
	
	public String stringRep(){
	
		return (""+this.pieceColour+" "+this.team);
		
	}
	
	

}
