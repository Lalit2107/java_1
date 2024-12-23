import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerXTurn = true;

    public Main() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Initialize buttons for the Tic-Tac-Toe grid
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        // Check if the button is already clicked
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        // Set the text based on whose turn it is
        if (playerXTurn) {
            buttonClicked.setText("X");
        } else {
            buttonClicked.setText("O");
        }

        playerXTurn = !playerXTurn;

        // Check for a win or tie after each move
        if (checkForWin()) {
            String winner = playerXTurn ? "O" : "X";
            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
            resetBoard();
        } else if (checkForTie()) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            resetBoard();
        }
    }

    private boolean checkForWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                return true;
            }
            if (!buttons[0][i].getText().equals("") &&
                    buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText())) {
                return true;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().equals("") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if (!buttons[0][2].getText().equals("") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private boolean checkForTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3;   col++) {
                buttons[row][col].setText("");
            }
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        new Main();
    }
}
