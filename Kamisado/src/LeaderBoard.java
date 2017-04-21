import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;



public class LeaderBoard {
	ArrayList<Player> leaderboard;
	File scores;

	public LeaderBoard() {
		leaderboard = new ArrayList<Player>();
		scores = new File("src/scores");
	}

	public void saveScore(Player player, int points) {
		leaderboard.add(player);
		for (int i = 0; i < leaderboard.size(); i++) {
			if (leaderboard.get(i).getName().equals(player.getName())) {
				leaderboard.get(i).addPoints(points);
				System.out.println("Saved " + player.getName() + "'s score");

				if (i == leaderboard.size() - 1 && leaderboard.get(i).getName() != player.getName()) { // Player
																										// not
																										// already
																										// existing
					player.addPoints(points);
					leaderboard.add(player);
					System.out.println("PLAYER ADDED!");
				}
			}

		}

	}

	public void sortLeaderBoard() {
		// Sort Algorithm based on player.getPoints
		// logic to sort the elements
		// change vars

		int n = leaderboard.size();
		int k;
		for (int m = n; m >= 0; m--) {
			for (int i = 0; i < n - 1; i++) {
				k = i + 1;
				if (leaderboard.get(i).getPoints() < leaderboard.get(k).getPoints()) {
					swap(i, k);
				}

			}
		}
	}

	public void swap(int i, int j) {

		Player temp = leaderboard.get(i);

		leaderboard.set(i, leaderboard.get(j));
		leaderboard.set(j, temp);
	}

	public void saveLeaderBoard() throws FileNotFoundException {
		System.out.println("PRINTING PRINTING PRINTING ARRAY = " + leaderboard.size());
		
		PrintWriter pw = new PrintWriter(scores);
		for (int i = 0; i < leaderboard.size(); i++) {
			Player temp = leaderboard.get(i);
			pw.println(temp.getName() + " " + temp.getPoints());
			System.out.println(temp.getName());
		}
		
		pw.flush();
		pw.close();
	}

	public void loadLeaderBoard() throws IOException {
		
		FileReader fr = new FileReader(scores);
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		Scanner tokeniser;

		String name;
		int points;

		for (int i = 0; i < br.lines().count(); i++) {
			
			System.out.println(s);
			
//			s = br.readLine();
			tokeniser = new Scanner(s);
			Player temp = new Player();
			name = tokeniser.next();
			points = tokeniser.nextInt();
			temp.setName(name);
			temp.setPoints(points);
			
			leaderboard.add(temp);
			sortLeaderBoard();

		}

	}

}
