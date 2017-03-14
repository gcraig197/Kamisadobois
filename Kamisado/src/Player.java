import java.util.Scanner;
public class Player {
	String name;
	Colour colour;
	Scanner sc;
	public Player(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
		sc = new Scanner(System.in);
	}

	public int[] MakeMove(){
		System.out.println("Please enter your move using the format");
		System.out.println("a b x y");
		System.out.println("where a and b are the coordinates of the the piece you want to move and x y are the coordinates of where you want to move the peice to.");
	    int[] moves = new int[4];
	    for(int i=0;i<4;i++){
	    	moves[i] = sc.nextInt();
	    }
		
	    
		return moves;
		
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public Colour getColour(){
		return colour;
	}
	public void setColour(Colour colour){
		this.colour = colour;
	}

}