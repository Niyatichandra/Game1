import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class number extends JFrame implements ActionListener {
    private final JTextField guessField;
    private final JButton guessButton, restartButton;
    private final JLabel messageLabel;
    private int numberToGuess;
    private int attempts;
    private int score;

    public number() {
        setTitle("Number Guessing Game");
        setSize(450, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        messageLabel = new JLabel("Guess a number between 1 and 100:");
        add(messageLabel);

        guessField = new JTextField(10);
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        add(guessButton);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> resetGame());
        restartButton.setEnabled(false);
        add(restartButton);

        resetGame();
        setVisible(true);
    }

    private void resetGame() {
        numberToGuess = new Random().nextInt(100) + 1;
        attempts = 0;
        score = 0;
        messageLabel.setText("Guess a number between 1 and 100:");
        guessField.setText("");
        guessField.setEditable(true);
        guessButton.setEnabled(true);
        restartButton.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess < numberToGuess)
            {
                messageLabel.setText("Too Low! Attempts: " + attempts);
            }
            else if (guess > numberToGuess)
            {
                messageLabel.setText("Too High! Attempts: " + attempts);
            }
            else
            {

                if (attempts == 1) score = 30;
                else if (attempts == 2) score = 20;
                else score = 10;

                messageLabel.setText("Correct! Score: " + score + " (Attempts: " + attempts + ")");
                guessField.setEditable(false);
                guessButton.setEnabled(false);
                restartButton.setEnabled(true);
                return;
            }

            if (attempts == 3) {
                messageLabel.setText("Out of attempts! Number was " + numberToGuess);
                guessField.setEditable(false);
                guessButton.setEnabled(false);
                restartButton.setEnabled(true);
            }

        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        number n=new number();
        n.setVisible(true);

    }
}
