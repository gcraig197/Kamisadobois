import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameMenuGUI extends JFrame {

private JPanel jp;
private JButton b;
private JTextField textField1;
private JTextField textField2;
private JTextField choice;
private JLabel l;
private JLabel l2;
private JCheckBox checkbox;
private JCheckBox box;
private boolean finished;
private JLabel randomboard;
private JComboBox round;
private JLabel numrounds;

public static void main(String[] args) {
	GameMenuGUI g = new GameMenuGUI();
	
}
	public GameMenuGUI(){
		super("Game Setup");
		finished = false;
		jp = new JPanel();
		jp.setLayout(null);
		b = new JButton("Submit");
		l = new JLabel("Player 1 Name:");
		l2 = new JLabel("Player 2 Name:");
		randomboard = new JLabel("Random Board?");
		box = new JCheckBox();
		String[] rounds = {"1","3","5"};
		round = new JComboBox<>(rounds);
		numrounds = new JLabel("No.Rounds");
		
		JLabel label = new JLabel("White player name:");
		JLabel label2 = new JLabel("Speed game?");
		choice = new JTextField();
		checkbox = new JCheckBox();

		numrounds.setBounds(170, 130, 100, 25);
		round.setBounds(250, 130, 50, 25);
		
		label.setBounds(250, 40, 250, 25);
		label2.setBounds(250,70,100,25);
		
		checkbox.setBounds(360, 67, 30, 30);
	
		randomboard.setBounds(250, 95, 100, 25);
		box.setBounds(360, 94, 30, 30);
		
		b.setBounds(190,190,100,30);
		b.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						finished = true;
						dispose();
					}
		});
		l.setBounds(20,3,200,100);
		l2.setBounds(20, 40, 200, 100);
		textField1 = new JTextField();
		textField1.setBounds(130, 40, 100, 25);
		textField2 = new JTextField();
		textField2.setBounds(130, 80, 100, 25);
		choice.setBounds(370, 40, 100, 25);
		
		setSize(500,250);
		setResizable(false);
		setLocationRelativeTo(null);
		
		jp.add(l);
		jp.add(l2);
		jp.add(textField1);
		jp.add(textField2);
		jp.add(b);
		jp.add(checkbox);
		jp.add(choice);
		jp.add(label);
		jp.add(label2);
		jp.add(randomboard);
		jp.add(box);
		jp.add(numrounds);
		jp.add(round);
		
		add(jp);
		
		setVisible(true);
		
	}
	public String getPlayer1Name(){
		return textField1.getText();
	}
	
	public String getPlayer2Name(){
		return textField2.getText();
	}
	
	public String getWhitePlayer(){
		return choice.getText();
	}
	
	public boolean isSpeedGame(){
		return checkbox.isSelected();
	}
	public boolean isFinished(){
		return finished;
	}
	public boolean isRandomBoard(){
		return box.isSelected();
	}
	public int getRounds(){
		if(round.getSelectedItem() == "1"){
			return 1;
		}
		else if(round.getSelectedItem() == "3"){
			return 3;
		}
		else{
			return 5;
		}
	}
}
