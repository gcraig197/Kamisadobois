

public class Piece {
char team;
Colour colour;
	
	public Piece(char team, Colour colour) {
		this.colour = colour;
		this.team = team;
	}

	public char getTeam() {
		return team;
	}	

	public void setTeam(char team) {
		this.team = team;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

}
