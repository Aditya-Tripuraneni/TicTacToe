package com.Aditya;
// Tic Toe Game
// Author: Aditya Tripuraneni
// Date: March 23rd 2021
// Description: TicTacToe game

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AdityaTicTacToe {

    public static void main(String[] args) {
       TicTacToe frame = new TicTacToe();
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class TicTacToe extends JFrame implements ActionListener {
    Font chillerFont = new Font("Chiller", Font.BOLD, 200); //Font for game
    String player = "X"; // Starting with player X

    JButton[][] buttons = new JButton[3][3];

    public TicTacToe() {
        super("Aditya's TicTacToe Game");

        JPanel pane = (JPanel) getContentPane();
        pane.setDoubleBuffered(true);
        pane.setBackground(Color.red); // Sets pane background colour to red
        pane.setLayout(new GridLayout(3, 3, 5, 5));

        for (int row = 0; row < 3; row++) //Creates row
            for (int column = 0; column < 3; column++) { // Creates columns
                buttons[row][column] = new JButton(); // Makes buttons so they are clickable & usable
                buttons[row][column].addActionListener(this); // Action listener so button functions
                pane.add(buttons[row][column]);
            }
        setSize(600, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        for (int i = 0; i < 3; i++) // Creating rows
            for (int j = 0; j < 3; j++) { // Creating columns
                if (actionEvent.getSource() == buttons[i][j]) { //Places the X or O
                    buttons[i][j].setFont(chillerFont);
                    buttons[i][j].setText(player);
                    buttons[i][j].setBackground(Color.cyan);
                    buttons[i][j].setEnabled(false); // Makes it so player cannot clicked on used box

                    if (player.equals("X")) // Setting the background colours if X or O
                        buttons[i][j].setBackground(Color.green);
                    if (player.equals("O"))
                        buttons[i][j].setBackground(Color.cyan);

                    if (foundWinner()) // Checks for winner
                        playAgain(); // asks if user wants to play again
                    else if (checkTie()) // Checks if tie
                    {
                        System.out.println("Game is draw.");
                        drawPlayAgain(); // asks if user wants to play again
                    }
                    else if (player.equals("X")) // Switches between players
                        player = "O";
                     else if (player.equals("O"))
                        player = "X";
                }
            }
    }

    public boolean foundWinner() { // Checks for winner

        for (int x = 0; x < 3; x++) { // Checks winner through rows
            if (buttons[x][0].getText().equals(player) && buttons[x][1].getText().equals(player) && buttons[x][2].getText().equals(player)) {
                System.out.println(player + " wins");
                return true;
            }
        }

        for (int x = 0; x < 3; x++) { // Checks winner through columns
            if (buttons[0][x].getText().equals(player) && buttons[1][x].getText().equals(player) && buttons[2][x].getText().equals(player)) {
                System.out.println(player + " wins");
                return true;
            }
        }

        if (buttons[0][0].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][2].getText().equals(player)) // Checks diagonals
        {
            System.out.println(player + " wins");
            return true;
        } else if (buttons[0][2].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][0].getText().equals(player)) // Checks diagonals
        {
            System.out.println(player + " wins");
            return true;
        }
        return false;
    }

    public boolean checkTie() { // Checks if tie
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].isEnabled())
                    return false; // Not tie
            }
        return true; // Is Tie
    }

    public void drawPlayAgain() { // Tells user game is tie & asks if they want to play again, if draw
        Object[] options = {"Yes I Would Like To Play Again", "No I Would Not Like To Play Again"}; // Options for player

        int playerOption = JOptionPane.showOptionDialog(null, // Match results with 1 winner
                "Would You Like To Play Again?",
                "Game is draw", JOptionPane.YES_NO_OPTION, //Displays who wins as title

                JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
        if (playerOption == 0) { // Allows player to continue to play
            for (int i = 0; i < 3; i++) // Makes rows
                for (int j = 0; j < 3; j++)  // Makes columns
                {
                    player = "X";
                    buttons[i][j].setBackground(Color.lightGray); // Resetting colour
                    buttons[i][j].setEnabled(true); // Clickable buttons again
                    buttons[i][j].setText(" ");
                }
        } else // If player does not pick yes, frame exits and game ends
            System.exit(0);
    }

    public void playAgain(){ // Gives player option to play again or not, if someone wins
        Object [] options = {"Yes I Would Like To Play Again", "No I Would Not Like To Play Again"}; // Options for player

        int playerOption = JOptionPane.showOptionDialog(null, // Match results with 1 winner
                "Would You Like To Play Again?",
                player + " wins",JOptionPane.YES_NO_OPTION, //Displays who wins as title

                JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);

        if (playerOption==0) { // Allows player to continue to play
            for (int i = 0; i < 3; i++) // Makes rows
                for (int j = 0; j < 3; j++)  // Makes columns
                {
                    player = "X";
                    buttons[i][j].setBackground(Color.lightGray); // Resetting colour
                    buttons[i][j].setEnabled(true); // Clickable buttons again
                    buttons[i][j].setText(" ");
                }
        }
        else // If player does not pick yes, frame exits and game ends
            System.exit(0);
    }
}

