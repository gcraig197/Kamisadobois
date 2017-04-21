
public class Piece {
	Colour pieceColour;
	Colour team;
	int dragonTeeth;

	public Piece(Colour pieceColour, Colour team, int dragonTeeth) {
		this.pieceColour = pieceColour;
		this.team = team;
		this.dragonTeeth = dragonTeeth;

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
	public void setDragonTeeth(int teeth){
		dragonTeeth = teeth;
	}
	public int getTeeth(){
		return dragonTeeth;
	}

	public int getMoveLimit() {
		int moveLimit = 0;
		if(dragonTeeth == 0){
			moveLimit = 8;
		}
		else if(dragonTeeth == 1){
			moveLimit = 5;
		}
		else if(dragonTeeth == 2){
			moveLimit = 3;
		}
		else{
			moveLimit = 1;
		}
		return moveLimit;
		
	}
	public int pushLimit(){
		return dragonTeeth;
	}
	

}
