import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AIGameMenuGUI extends JFrame {

private JPanel jp;
private JButton b;
private JTextField textField1;
private JLabel l;
private JLabel randomboard;
private JCheckBox box;
private JCheckBox checkbox;
private boolean finished;
private JLabel d;
private JComboBox difficulty;
private JComboBox round;
private JLabel numrounds;


public static void main(String[] args) {
	AIGameMenuGUI g = new AIGameMenuGUI();
	
}
	public AIGameMenuGUI(){
		super("AI Game Setup");
		finished = false;
		jp = new JPanel();
		jp.setLayout(null);
		b = new JButton("Submit");
		l = new JLabel("Player 1 Name:");
		d = new JLabel("Difficulty");
		numrounds = new JLabel("No.Rounds");
		
		JLabel label2 = new JLabel("Speedgame?");
		randomboard = new JLabel("Random Board?");
		box = new JCheckBox();
		checkbox = new JCheckBox();
		String[] difficulties = {"Easy","Hard"};
		difficulty = new JComboBox(difficulties);
		String[] rounds = {"1","3","5"};
		round = new JComboBox(rounds);

		
		
		
		label2.setBounds(50,70,100,25);
		randomboard.setBounds(40, 100, 100, 25);
		box.setBounds(130,97,30,30);
		checkbox.setBounds(130, 67, 30, 30);
	
		
		b.setBounds(90,220,100,30);
		b.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						finished = true;
						dispose();
					}
		});
		l.setBounds(20,3,200,100);
		textField1 = new JTextField();
		textField1.setBounds(130, 40, 100, 25);
		
		setSize(300,300);
		setResizable(false);
		setLocationRelativeTo(null);
		
		d.setBounds(60, 140, 100 , 25);
		
		difficulty.setBounds(130, 140, 100, 25);
		
		round.setBounds(130, 180, 100, 25);
		numrounds.setBounds(60, 180, 100, 25);
		
		jp.add(l);
		jp.add(textField1);
		jp.add(b);
		jp.add(d);
		jp.add(difficulty);
		jp.add(round);
		jp.add(numrounds);
		jp.add(randomboard);
		jp.add(box);
		jp.add(checkbox);
		jp.add(label2);
		
		add(jp);
		
		setVisible(true);
		
	}
	public String getPlayer1Name(){
		return textField1.getText();
	}

	public boolean isSpeedGame(){
		return checkbox.isSelected();
	}
	public boolean isRandomBoard(){
		return box.isSelected();
	}
	public boolean isFinished(){
		return finished;
	}
	public String getDifficulty(){
		return String.valueOf(difficulty.getSelectedItem());
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
