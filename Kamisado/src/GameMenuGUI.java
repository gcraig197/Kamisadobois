import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameMenuGUI extends JFrame {

private JPanel jp;
private JButton b;
private JTextField textField;

	public GameMenuGUI(){
		jp = new JPanel();
		b = new JButton();
		textField = new JTextField("Please enter your name",20);
		
		setSize(600,600);
		setResizable(true);
		
		jp.add(b);
		jp.add(textField);
		add(jp);
		
		setVisible(true);
	}
}
