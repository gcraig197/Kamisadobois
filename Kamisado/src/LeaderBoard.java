
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
//		populate();
	}

	public void saveScore(Player player, int points) {
		System.out.println("save score array size: " + leaderboard.size());
		
		if (leaderboard.size() == 0) {
			player.addPoints(points);
			leaderboard.add(player);
			System.out.println("First To Play!");
			
		}
		
		else {
		for (int i = 0; i < leaderboard.size(); i++) {
			if (leaderboard.get(i).getName().equals(player.getName())) {
				leaderboard.get(i).addPoints(points);
				System.out.println("Saved " + player.getName() + "'s score");
				break;
			}

			if (i == leaderboard.size() - 1 && leaderboard.get(i).getName() != player.getName()) { // Player
																									// //
																									// existing
				player.addPoints(points);
				leaderboard.add(player);
				System.out.println("PLAYER ADDED!");
				break;
			}
		}
		}
		
		sortLeaderBoard();

	}

	public void sortLeaderBoard() {
		// Sort Algorithm based on player.getPoints
		// logic to sort the elements
		// change vars

		int x = leaderboard.size();
		int y;
		for (int z = leaderboard.size(); z >= 0; z--) {
			for (int i = 0; i < x - 1; i++) {
				y = i + 1;
				if (leaderboard.get(i).getPoints() < leaderboard.get(y).getPoints()) {
					swap(i, y);
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
		pw.println(leaderboard.size());

		for (int i = 0; i < leaderboard.size(); i++) {
			Player temp = leaderboard.get(i);
			pw.println(temp.getName() + " " + temp.getPoints());
			System.out.println(temp.getName());
		}
		sortLeaderBoard();
		pw.flush();
		pw.close();
	}

	public void loadLeaderBoard() throws IOException {

		clearArray();

		FileReader fr = new FileReader(scores);
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();

		int leaderboardsize;
		Scanner tokeniser = new Scanner(s);
		leaderboardsize = tokeniser.nextInt();

		String name;
		int points;

		for (int i = 0; i < leaderboardsize; i++) {

			System.out.println(s);

			s = br.readLine();
			tokeniser = new Scanner(s);
			Player temp = new Player();
			name = tokeniser.next();
			points = tokeniser.nextInt();
			temp.setName(name);
			temp.setPoints(points);

			leaderboard.add(temp);

		}
		sortLeaderBoard();
		br.close();
		tokeniser.close();

	}

	public void clearArray() {
		leaderboard.removeAll(leaderboard);

		System.out.println("array size : " + leaderboard.size());
	}

	public void populate() {
		for (int i = 0; i < 10; i++) {
			Player temp = new Player();
			temp.setName("EMPTY");
			temp.setPoints(0);
			leaderboard.add(temp);

		}
	}

}