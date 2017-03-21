import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverGUI extends JFrame {
	private JPanel jp;
	private JLabel label;
	private JButton button;

	public static void main(String[] args) {
		Player winner = new Player();
		winner.setName("Jed");
		GameOverGUI g = new GameOverGUI(winner);

	}

	public GameOverGUI(Player winner) {
		jp = new JPanel();
		jp.setLayout(null);
		label = new JLabel("The winner is " + winner.getName() + "!!");
		label.setBounds(80, 10, 300, 30);
		button = new JButton("End game");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		button.setBounds(90, 50, 100, 30);

		jp.add(label);
		jp.add(button);
		add(jp);
		setSize(300, 130);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
