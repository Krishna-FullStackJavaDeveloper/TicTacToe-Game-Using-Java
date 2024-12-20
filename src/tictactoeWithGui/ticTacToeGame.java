package tictactoeWithGui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ticTacToeGame extends JPanel {
	
	char playerMark = 'X';
	JButton[] buttons = new JButton[9];
	
	public ticTacToeGame() {
		setLayout(new GridLayout(3,3));
		initializeButtons();
	}

		// a method used to create 9 buttons
		// set the text, add action listeners
		// and add them to the screen
	private void initializeButtons() {
		
		for(int i = 0; i <= 8; i++) {
			buttons[i] = new JButton();
			buttons[i].setText("-");
			buttons[i].setBackground(Color.WHITE);
			buttons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton buttonClicked = (JButton) e.getSource(); //get the particular button that was clicked
					buttonClicked.setText(String.valueOf(playerMark));
					
					if(playerMark == 'X') {
						playerMark = 'O';
						buttonClicked.setBackground(Color.CYAN);
					}
					else {
						playerMark = 'X';
						buttonClicked.setBackground(Color.YELLOW);
					}
					displayVictor();
				}

				
			});
			add(buttons[i]); //adds this buttons to JPanel
		}
		
	}
	
	// display the victorious player
	private void displayVictor() {
		if(checkForWinner() == true) {
			
			// reverse the player marks
			// because after we put x and we win, the game changes it to o
			// but x is the winner
			
			if(playerMark == 'X') 
				playerMark = 'O';
			else
				playerMark = 'X';
			
			JOptionPane pane = new JOptionPane();
			int dialogResult = JOptionPane.showConfirmDialog(pane, "Game Over. " + playerMark+ " wins. Would you like to play again?", "Game Over.",
					JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.YES_OPTION) {
				resetTheButtons();
			}
			else {
				System.exit(0);
			}
		}
		else if(checkDraw()) {
			JOptionPane pane = new JOptionPane();
			int dialogResult = JOptionPane.showConfirmDialog(pane, "Draw. Play again?", "Game Over.", JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION) {
				resetTheButtons();
			}else {
				System.exit(0);
			}
		}
		
	}

	
	// method used to reset the buttons
	// so you can play again
	private void resetTheButtons() {
		playerMark = 'X';
		for(int i = 0; i < 9; i++) {
			buttons[i].setText("-");
			buttons[i].setBackground(Color.WHITE);
		}
		
	}
	
//	check for Draw
	private boolean checkDraw() {
		boolean full = true;
		
		for(int i = 0; i < 9; i++) {
			if(buttons[i].getText().charAt(0) == '-') {
				full = false;
			}else {
				full = true;
			}
		}
		return full;
	}

	// checks for a winner
	private boolean checkForWinner() {
		if(checkRows() == true || checkColumns() == true || checkDiagonals() == true)
		return true;
		else return false;
	}

	// checks rows for a win
	private boolean checkRows() {
		int i = 0;
		for(int j = 0; j < 3; j++) {
			if(buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText())
					&& buttons[i].getText().charAt(0) != '-') {
				return true;
			}
			i = i+3;
		}
		return false;
	}
	

	// checks columns for a win
	private boolean checkColumns() {
		int i = 0;
		for(int j = 0; j < 3; j++) {
			if(buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText())
					&& buttons[i].getText().charAt(0) != '-') {
				return true;
			}
			i++;
		}
		return false;
	}

	// checks diagonals for a win
	private boolean checkDiagonals() {
		if(buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
				&& buttons[0].getText().charAt(0) != '-')
			return true;
		else if(buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
				&& buttons[2].getText().charAt(0) != '-')
			return true;
		else 
			return false;
		
	}
	

	public static void main(String[] args) {
		JFrame window = new JFrame("Tic Tac Toe by Krishna");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new ticTacToeGame()); // adds the data
		window.setBounds(500,500,500,500); //area
		window.setVisible(true); // show the window
		window.setLocationRelativeTo(null); //create the window.
	}
}
