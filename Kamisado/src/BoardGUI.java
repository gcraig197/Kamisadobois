import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BoardGUI extends JFrame {
	private Container contents;
	private JButton[][] buttons = new JButton[8][8];
	private Board board;
	private int x = 0;
	private int y = 0;
	private boolean canMove = false;
	private ButtonHandler bh;

	public static void main(String[] args) {
		Board board = new Board();
		board.Setup();
		BoardGUI b = new BoardGUI(board);
		b.refresh(board);
		b.storeX(-1);
		b.storeY(-1);
		System.out.println("(" + b.getX() + "," + b.getY() + ")");

	}

	public BoardGUI(Board board) {
		this.board = board;
		contents = getContentPane();
		contents.setLayout(new GridLayout(8, 8));
		bh = new ButtonHandler();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(bh);
				ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				buttons[i][j].setIcon(icon);
				contents.add(buttons[i][j]);
				if (board.getColour(i, j) == Colour.ORANGE) {
					buttons[i][j].setBackground(Color.ORANGE);
				} else if (board.getColour(i, j) == Colour.LBLUE) {
					buttons[i][j].setBackground(Color.CYAN);
				} else if (board.getColour(i, j) == Colour.DBLUE) {
					buttons[i][j].setBackground(Color.BLUE);
				} else if (board.getColour(i, j) == Colour.PINK) {
					buttons[i][j].setBackground(Color.PINK);
				} else if (board.getColour(i, j) == Colour.YELLOW) {
					buttons[i][j].setBackground(Color.YELLOW);
				} else if (board.getColour(i, j) == Colour.RED) {
					buttons[i][j].setBackground(Color.RED);
				} else if (board.getColour(i, j) == Colour.GREEN) {
					buttons[i][j].setBackground(Color.GREEN);
				} else {
					buttons[i][j].setBackground(new Color(160, 82, 45));

				}
			}
		}
		refresh(board);
	}

	public void refresh(Board board) {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.getColour(i, j) == Colour.ORANGE) {
					buttons[i][j].setBackground(Color.ORANGE);
				} else if (board.getColour(i, j) == Colour.LBLUE) {
					buttons[i][j].setBackground(Color.CYAN);
				} else if (board.getColour(i, j) == Colour.DBLUE) {
					buttons[i][j].setBackground(Color.BLUE);
				} else if (board.getColour(i, j) == Colour.PINK) {
					buttons[i][j].setBackground(Color.PINK);
				} else if (board.getColour(i, j) == Colour.YELLOW) {
					buttons[i][j].setBackground(Color.YELLOW);
				} else if (board.getColour(i, j) == Colour.RED) {
					buttons[i][j].setBackground(Color.RED);
				} else if (board.getColour(i, j) == Colour.GREEN) {
					buttons[i][j].setBackground(Color.GREEN);
				} else {
					buttons[i][j].setBackground(new Color(160, 82, 45));

				}
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.getPieceCell(i, j).getPieceColour() == Colour.ORANGE
						&& board.getPieceCell(i, j).getTeam() == Colour.WHITE) {
					buttons[i][j].setIcon(new ImageIcon("W_Orange.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.LBLUE
						&& board.getPieceCell(i, j).getTeam() == Colour.WHITE) {
					buttons[i][j].setIcon(new ImageIcon("W_LBLUE.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.DBLUE
						&& board.getPieceCell(i, j).getTeam() == Colour.WHITE) {
					buttons[i][j].setIcon(new ImageIcon("W_DBLUE.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.PINK
						&& board.getPieceCell(i, j).getTeam() == Colour.WHITE) {
					buttons[i][j].setIcon(new ImageIcon("W_Pink.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.YELLOW
						&& board.getPieceCell(i, j).getTeam() == Colour.WHITE) {
					buttons[i][j].setIcon(new ImageIcon("W_Yellow.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.RED
						&& board.getPieceCell(i, j).getTeam() == Colour.WHITE) {
					buttons[i][j].setIcon(new ImageIcon("W_Red.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.GREEN
						&& board.getPieceCell(i, j).getTeam() == Colour.WHITE) {
					buttons[i][j].setIcon(new ImageIcon("W_Green.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.BROWN
						&& board.getPieceCell(i, j).getTeam() == Colour.WHITE) {
					buttons[i][j].setIcon(new ImageIcon("W_Brown.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.BLANK) {
					buttons[i][j].setIcon(null);
				}

				// Black team

				else if (board.getPieceCell(i, j).getPieceColour() == Colour.ORANGE
						&& board.getPieceCell(i, j).getTeam() == Colour.BLACK) {
					buttons[i][j].setIcon(new ImageIcon("Orange.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.LBLUE
						&& board.getPieceCell(i, j).getTeam() == Colour.BLACK) {
					buttons[i][j].setIcon(new ImageIcon("LBlue.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.DBLUE
						&& board.getPieceCell(i, j).getTeam() == Colour.BLACK) {
					buttons[i][j].setIcon(new ImageIcon("DBLue.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.PINK
						&& board.getPieceCell(i, j).getTeam() == Colour.BLACK) {
					buttons[i][j].setIcon(new ImageIcon("Pink.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.YELLOW
						&& board.getPieceCell(i, j).getTeam() == Colour.BLACK) {
					buttons[i][j].setIcon(new ImageIcon("Yellow.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.RED
						&& board.getPieceCell(i, j).getTeam() == Colour.BLACK) {
					buttons[i][j].setIcon(new ImageIcon("Red.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.GREEN
						&& board.getPieceCell(i, j).getTeam() == Colour.BLACK) {
					buttons[i][j].setIcon(new ImageIcon("Green.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.BROWN
						&& board.getPieceCell(i, j).getTeam() == Colour.BLACK) {
					buttons[i][j].setIcon(new ImageIcon("Brown.png"));
				} else if (board.getPieceCell(i, j).getPieceColour() == Colour.BLANK) {
					buttons[i][j].setIcon(null);
				}

			}
		}

		setSize(800, 800);
		setResizable(false);
		setVisible(true);
		pack();

	}

	public void storeX(int x) {
		this.x = x;
	}

	public void storeY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public boolean canMove() {
		return canMove;
	}

	public void availableMoves(int currposa, int currposb, Player player) {
		if (board.getPieceCell(currposa, currposb).getPieceColour() != Colour.BLANK) {

			if (player.getTeam() == Colour.WHITE) {

				// White Vertical

				for (int i = currposa + 1; i < 8; i++) {
					if (board.getColour(i, currposb) == Colour.ORANGE) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.ORANGE.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.LBLUE) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.CYAN.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.DBLUE) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.BLUE.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.PINK) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.PINK.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.YELLOW) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.YELLOW.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.RED) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.RED.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.GREEN) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.GREEN.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.BROWN) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.black.darker().darker().darker());
						} else {
							break;
						}
					}

				}

				// White Left Diagonal

				for (int i = 1; i < 8; i++) {
					if ((currposb - i) >= 0 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb - i) == Colour.ORANGE) {
						if (board.getPieceCell(currposa + i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb - i].setBackground(Color.ORANGE.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb - i) == Colour.LBLUE) {
						if (board.getPieceCell(currposa + i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb - i].setBackground(Color.CYAN.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb - i) == Colour.DBLUE) {
						if (board.getPieceCell(currposa + i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb - i].setBackground(Color.BLUE.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb - i) == Colour.PINK) {
						if (board.getPieceCell(currposa + i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb - i].setBackground(Color.PINK.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb - i) == Colour.YELLOW) {
						if (board.getPieceCell(currposa + i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb - i].setBackground(Color.YELLOW.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb - i) == Colour.RED) {
						if (board.getPieceCell(currposa + i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb - i].setBackground(Color.RED.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb - i) == Colour.GREEN) {
						if (board.getPieceCell(currposa + i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb - i].setBackground(Color.GREEN.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb - i) == Colour.BROWN) {
						if (board.getPieceCell(currposa + i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb - i].setBackground(Color.black.darker().darker().darker());
						} else {
							break;
						}
					}

				}

				// White Right Diagonal

				for (int i = 1; i < 8; i++) {
					if ((currposb + i) <= 7 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb + i) == Colour.ORANGE) {
						if (board.getPieceCell(currposa + i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb + i].setBackground(Color.ORANGE.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb + i) == Colour.LBLUE) {
						if (board.getPieceCell(currposa + i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb + i].setBackground(Color.CYAN.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb + i) == Colour.DBLUE) {
						if (board.getPieceCell(currposa + i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb + i].setBackground(Color.BLUE.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb + i) == Colour.PINK) {
						if (board.getPieceCell(currposa + i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb + i].setBackground(Color.PINK.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb + i) == Colour.YELLOW) {
						if (board.getPieceCell(currposa + i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb + i].setBackground(Color.YELLOW.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb + i) == Colour.RED) {
						if (board.getPieceCell(currposa + i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb + i].setBackground(Color.RED.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa + i) <= 7
							&& board.getColour(currposa + i, currposb + i) == Colour.GREEN) {
						if (board.getPieceCell(currposa + i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb + i].setBackground(Color.GREEN.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa + i) <= 7 && board.getColour(i, currposb) == Colour.BROWN) {
						if (board.getPieceCell(currposa + i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa + i][currposb + i].setBackground(Color.black.darker().darker().darker());
						} else {
							break;
						}
					}

				}

			} else if (player.getTeam() == Colour.BLACK) {

				// Black Vertical

				for (int i = currposa - 1; i > 0; i--) {
					if (board.getColour(i, currposb) == Colour.ORANGE) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.ORANGE.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.LBLUE) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.CYAN.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.DBLUE) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.BLUE.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.PINK) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.PINK.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.YELLOW) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.YELLOW.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.RED) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.RED.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.GREEN) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.GREEN.darker().darker().darker());
						} else {
							break;
						}
					}
					if (board.getColour(i, currposb) == Colour.BROWN) {
						if (board.getPieceCell(i, currposb).getPieceColour() == Colour.BLANK) {
							buttons[i][currposb].setBackground(Color.BLACK.darker().darker().darker());
						} else {
							break;
						}
					}

				}

				// Black Left Diagonal

				for (int i = 1; i < 8; i++) {
					if ((currposb - i) >= 0 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb - i) == Colour.ORANGE) {
						if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb - i].setBackground(Color.ORANGE.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb - i) == Colour.LBLUE) {
						if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb - i].setBackground(Color.CYAN.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb - i) == Colour.DBLUE) {
						if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb - i].setBackground(Color.BLUE.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb - i) == Colour.PINK) {
						if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb - i].setBackground(Color.PINK.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb - i) == Colour.YELLOW) {
						if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb - i].setBackground(Color.YELLOW.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb - i) == Colour.RED) {
						if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb - i].setBackground(Color.RED.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb - i) == Colour.GREEN) {
						if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb - i].setBackground(Color.GREEN.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb - i) >= 0 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb - i) == Colour.BROWN) {
						if (board.getPieceCell(currposa - i, currposb - i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb - i].setBackground(Color.black.darker().darker().darker());
						} else {
							break;
						}
					}

				}

				// Black Right Diagonal

				for (int i = 1; i < 8; i++) {
					if ((currposb + i) <= 7 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb + i) == Colour.ORANGE) {
						if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb + i].setBackground(Color.ORANGE.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb + i) == Colour.LBLUE) {
						if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb + i].setBackground(Color.CYAN.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb + i) == Colour.DBLUE) {
						if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb + i].setBackground(Color.BLUE.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb + i) == Colour.PINK) {
						if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb + i].setBackground(Color.PINK.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb + i) == Colour.YELLOW) {
						if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb + i].setBackground(Color.YELLOW.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb + i) == Colour.RED) {
						if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb + i].setBackground(Color.RED.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb + i) == Colour.GREEN) {
						if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb + i].setBackground(Color.GREEN.darker().darker().darker());
						} else {
							break;
						}
					}
					if ((currposb + i) <= 7 && (currposa - i) >= 0
							&& board.getColour(currposa - i, currposb + i) == Colour.BROWN) {
						if (board.getPieceCell(currposa - i, currposb + i).getPieceColour() == Colour.BLANK) {
							buttons[currposa - i][currposb + i].setBackground(Color.black.darker().darker().darker());
						} else {
							break;
						}
					}

				}

			}
		}

	}

	private class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Object x = e.getSource();
			outerloop: for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (x == buttons[i][j]) {
						System.out.println("(" + i + "," + j + ")");
						storeX(i);
						storeY(j);
						setCanMove(true);
						break outerloop;
					}

				}
			}
		}

	}

}