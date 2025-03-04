import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton guessButton, resetButton;
    private JLabel messageLabel;
    private int randomNumber, attempts;

    public NumberGuessingGame() {
        // Set up the frame
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        // Initialize components
        messageLabel = new JLabel("Guess a number between 1 and 100:", JLabel.CENTER);
        inputField = new JTextField();
        guessButton = new JButton("Guess");
        resetButton = new JButton("Reset");
        
        // Disable reset button initially
        resetButton.setEnabled(false);

        // Add components to the frame
        add(messageLabel);
        add(inputField);
        add(guessButton);
        add(resetButton);

        // Add event listeners
        guessButton.addActionListener(this);
        resetButton.addActionListener(this);

        // Start a new game
        resetGame();
    }

    private void resetGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1; // Generate random number between 1 and 100
    //    System.out.println(randomNumber);
        attempts = 0;
        messageLabel.setText("Guess a number between 1 and 100:");
        inputField.setText("");
        resetButton.setEnabled(false);
        guessButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            String inputText = inputField.getText();
            try {
                int guess = Integer.parseInt(inputText);
                attempts++;

                if (guess < randomNumber) {
                    messageLabel.setText("Too low! Try again.");
                } else if (guess > randomNumber) {
                    messageLabel.setText("Too high! Try again.");
                } else {
                    messageLabel.setText("Correct! It took you " + attempts + " attempts.");
                    guessButton.setEnabled(false);
                    resetButton.setEnabled(true);
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
        }

        if (e.getSource() == resetButton) {
            resetGame();
        }
    }

    public static void main(String[] args) {
        // Create and show the game window
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
}