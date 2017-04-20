import java.util.Scanner;
public class Player {
	String name;
	Colour team;
	Scanner sc;
	int points;
	public Player() {
		this.name = "default";
		sc = new Scanner(System.in);
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public Colour getTeam(){
		return team;
	}
	public void setTeam(Colour team){
		this.team = team;
	}

}