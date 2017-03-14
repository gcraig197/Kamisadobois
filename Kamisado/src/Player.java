import java.util.Scanner;
public class Player {
	Colour colour;
	Scanner sc;
	public Player(Colour colour) {
		this.colour = colour;
		sc = new Scanner(System.in);
	}

	public int[] MakeMove(){
		System.out.println("Please enter your move using the format");
		System.out.println("a b x y");
		System.out.println("where a and b are the coordinates of the the piece you want to move and x y are the coordinates of where you want to move the peice to.");
	    int[] moves = new int[4];
		int movex = sc.nextInt();
		int movey = sc.nextInt();
		int newmovex = sc.nextInt();
		int newmovey = sc.nextInt();
		moves[0] = movex;
		moves[1] = movey;
		moves[2] = newmovex;
		moves[3] = newmovey;
		return moves;
	}

	public void interrupt(){
	
	}
	public Colour getColour(){
		return colour;
	}
	public void setColour(Colour colour){
		this.colour = colour;
	}

}