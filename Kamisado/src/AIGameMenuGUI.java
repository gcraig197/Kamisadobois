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
private JCheckBox checkbox;
private boolean finished;


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
		
		JLabel label2 = new JLabel("Speedgame?");
		checkbox = new JCheckBox();

		
		
		
		label2.setBounds(50,70,100,25);
		
		checkbox.setBounds(130, 67, 30, 30);
	
		
		b.setBounds(90,150,100,30);
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
		
		setSize(300,250);
		setResizable(false);
		setLocationRelativeTo(null);
		
		jp.add(l);
		jp.add(textField1);
		jp.add(b);
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
	public boolean isFinished(){
		return finished;
	}
	
}
