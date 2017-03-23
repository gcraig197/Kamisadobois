import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartMenuGUI extends JFrame{
	private JPanel panel;
	private JLabel label;
	private JLabel label2;
	private JRadioButton AI;
	private JRadioButton twoPlayer;
	private JButton submit;
	private boolean finished;
	
	public static void main(String[] args) {
		StartMenuGUI g = new StartMenuGUI();
		
	}

	
	public StartMenuGUI(){
		super("Start Menu");
		panel = new JPanel();
		label = new JLabel("AI Game");
		label2 = new JLabel("Two Player");
		AI = new JRadioButton();
		twoPlayer = new JRadioButton();
		submit = new JButton("Submit");
		finished = false;
		
		panel.setLayout(null);
		
		label.setBounds(50, 50, 100, 30);
		label2.setBounds(50, 80, 100, 30);
		
		ButtonGroup group = new ButtonGroup();
		group.add(AI);
		group.add(twoPlayer);
		AI.setBounds(130,50,100,30);
		twoPlayer.setBounds(130,80,100,30);
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				finished = true;
				dispose();
			}
		});
		
		submit.setBounds(100, 150, 100, 30);
		
		panel.add(label);
		panel.add(label2);
		panel.add(AI);
		panel.add(twoPlayer);
		panel.add(submit);
		add(panel);
		
		
		
		
		
		setSize(300,230);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public boolean isAI(){
		return AI.isSelected();
	}
	public boolean isTwoPlayer(){
		return twoPlayer.isSelected();
	}
	public boolean isFinished(){
		return finished;
	}
	
}
