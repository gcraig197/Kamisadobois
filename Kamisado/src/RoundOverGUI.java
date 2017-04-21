import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoundOverGUI extends JFrame {
	private JPanel jp;
	private JLabel label;
	private JButton button;
	private Boolean finished;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public RoundOverGUI(Player winner){
		jp = new JPanel();
		finished = false;
		jp.setLayout(null);
		label = new JLabel("The winner of the round is " + winner.getName() + "!!");
		label.setBounds(60, 10, 300, 30);
		button = new JButton("End game");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				finished = true;
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
	
	public boolean isFinished(){
		return finished;
	}

}
