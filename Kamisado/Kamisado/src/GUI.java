
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class GUI {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] squares = new JButton[8][8];
    private JPanel board;
    private JButton save;
    private JButton undo;
    private JButton quit;
    private Board grid;
    private final JLabel message = new JLabel(
            "Kamisado");

    GUI() {
        initializeGui();
    }

    public final void initializeGui() {
        // set up the main GUI
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.add(new JButton("Undo")); // TODO - add functionality!
        quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { System.exit(0); }
                           });

        tools.addSeparator();
        tools.add(message);


        board = new JPanel(new GridLayout(0, 8));
        board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board);
        grid = new Board();
        

        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if(grid.getColour(i, j) == Colour.ORANGE){
                	b.setBackground(Color.ORANGE);
                	if(i==0)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/White/Orange.png"));
                	else if(i==7)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/Black/DBlue.png"));
                }
                else if(grid.getColour(i, j) == Colour.DBLUE){
                	b.setBackground(Color.BLUE);
                	if(i==0)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/White/DBlue.png"));
                	else if(i==7)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/Black/DBlue.png"));
                }
                else if(grid.getColour(i, j) == Colour.LBLUE){
                	b.setBackground(Color.CYAN);
                	if(i==0)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/White/LBlue.png"));
                	else if(i==7)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/Black/LBlue.png"));
                }
                else if(grid.getColour(i, j) == Colour.PINK){
                	b.setBackground(Color.PINK);
                	if(i==0)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/White/Pink.png"));
                	else if(i==7)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/Black/Pink.png"));
                }
                else if(grid.getColour(i, j) == Colour.YELLOW){
                	b.setBackground(Color.YELLOW);
                	if(i==0)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/White/Yellow.png"));
                	else if(i==7)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/Black/Yellow.png"));
                }
                else if(grid.getColour(i, j) == Colour.RED){
                	b.setBackground(Color.RED);
                	if(i==0)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/White/Red.png"));
                	else if(i==7)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/Black/Red.png"));
                }
                else if(grid.getColour(i, j) == Colour.GREEN){
                	b.setBackground(Color.GREEN);
                	if(i==0)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/White/Green.png"));
                	else if(i==7)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/Black/Green.png"));
                }
                else{
                	b.setBackground(new Color(160,82,45));
                	if(i==0)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/White/Brown.png"));
                	else if(i==7)
                		b.setIcon(new ImageIcon("/Kamisado Pieces/Black/Brown.png"));
                }
                squares[j][i] = b;
            }
        }
     
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                switch (b) {
                    
                    default:
                        board.add(squares[b][a]);
                }
            }
        }
    }

  

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                GUI cb =
                        new GUI();

                JFrame f = new JFrame("KamisadoGame");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
    
}