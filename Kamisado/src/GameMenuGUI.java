import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
private JLabel l;
private JLabel l2;
private JComboBox cb;
private boolean finished;


public static void main(String[] args) {
	GameMenuGUI g = new GameMenuGUI();
	
}
	public GameMenuGUI(){
		finished = false;
		jp = new JPanel();
		jp.setLayout(null);
		b = new JButton("Click me");
		l = new JLabel("Player 1 Name:");
		l2 = new JLabel("Player 2 Name:");
		
		b.setBounds(150,150,100,30);
		b.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						newFrame();
					}
		});
		l.setBounds(50,10,200,100);
		l2.setBounds(50, 60, 200, 100);
		textField1 = new JTextField();
		textField1.setBounds(150, 50, 100, 25);
		textField2 = new JTextField();
		textField2.setBounds(150, 100, 100, 25);
		
		setSize(500,300);
		setResizable(false);
		setLocationRelativeTo(null);
		
		jp.add(l);
		jp.add(l2);
		jp.add(textField1);
		jp.add(textField2);
		jp.add(b);
		
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
		return String.valueOf(cb.getSelectedItem());
	}
	public boolean isFinished(){
		return finished;
	}
	
	public void newFrame(){
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("Click me");
		JLabel label = new JLabel("Please select the Player who will be white");
		panel.setLayout(null);
		frame.setSize(500,300);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		dispose();
		String[] choices = {getPlayer1Name(),getPlayer2Name()};
		
		cb = new JComboBox<>(choices);
		cb.setBounds(150, 30, 100, 25);
		
		button.setBounds(150, 150, 100, 30);
		button.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
						    finished = true;
							frame.dispose();
		}
				});
		
		label.setBounds(100, 10, 250, 25);
		
		panel.add(button);
		panel.add(cb);
		panel.add(label);
		frame.add(panel);
	}
}
