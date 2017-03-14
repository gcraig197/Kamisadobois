import java.util.Scanner;
public class Player {
	Colour colour;
	Scanner sc;
	public Player(Colour colour) {
		this.colour = colour;
		sc = new Scanner(System.in);
	}

	public String MakeMove(){
		System.out.println("Please enter your move using the format");
		System.out.println("a b x y");
		System.out.println("where a and b are the coordinates of the the piece you want to move and x y are the coordinates of where you want to move the peice to.");
	    String move  = sc.nextLine();
		return move;
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