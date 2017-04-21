import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreBoard {

	ArrayList<Player> leaderboard;
	ArrayList<Player> test;
	File scoreboard;
	
	public ScoreBoard(){
		leaderboard = new ArrayList<Player>();
		test = new ArrayList<Player>();
		scoreboard = new File("src/scoreboard");
		
	}
	
	public void saveScore(Player player, int score) {
		
		System.out.println("save score array size: " + leaderboard.size());
		for (int i = 0; i < leaderboard.size(); i++) {
			if (leaderboard.get(i).getName().equals(player.getName())) {
				leaderboard.get(i).addPoints(score);
				System.out.println("Saved " + player.getName() + "'s score");
			}

				if (i == leaderboard.size() - 1 && leaderboard.get(i).getName() != player.getName()) { // Player																		// existing
					player.addPoints(score);
					leaderboard.add(player);
					System.out.println("PLAYER ADDED!");
				}
			}

		}
	
	
	public void saveToFile() throws FileNotFoundException{
	
		PrintWriter pw = new PrintWriter(scoreboard);
		pw.println(leaderboard.size());
		
		for (int i = 0; i < leaderboard.size(); i++) {
			pw.println(leaderboard.get(i).getName() + " " + leaderboard.get(i).getPoints());
			
		}
		pw.flush();
		pw.close();
	}
	
	public void load() throws IOException {
		FileReader fr = new FileReader(scoreboard);
		BufferedReader br = new BufferedReader(fr);
		String s;
		String name;
		int points;
		int leaderboardsize;
		s = br.readLine();
		Scanner tokeniser = new Scanner(s);
		leaderboardsize = tokeniser.nextInt();
		
		for (int i = 0; i < leaderboardsize; i++) {
			s = br.readLine();
			System.out.println(s);
			 tokeniser = new Scanner(s);
			 name = tokeniser.next();
			 points = tokeniser.nextInt();
			
			System.out.println("HAS READ: " + name + " " + points);
			
			Player temp = new Player();
			temp.setName(name);
			temp.setPoints(points);
			leaderboard.add(temp);
			
		}
		for (int i = 0; i < leaderboard.size(); i++) {
			System.out.println(i + " " + leaderboard.get(i).getName() + " " + leaderboard.get(i).getPoints());
			
		}
	}
	
	public void clearArray(){
		leaderboard.removeAll(leaderboard);
			
		
		System.out.println("array size : " + leaderboard.size());
	}
		
		public void populate(){
			for (int i = 0; i < 10; i++) {
				Player temp = new Player();
				temp.setName("EMPTY");
				temp.setPoints(0);
				leaderboard.add(temp);

			}
		}
		
		
	
}

