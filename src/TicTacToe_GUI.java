import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe_GUI extends JFrame {
    private TicTacToe_Model model;
    private JLabel turnField;
    private List<JButton> buttons;

    public TicTacToe_GUI() {
        model = new TicTacToe_Model();
        buttons = new ArrayList<>();

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        JPanel gamePanel = new JPanel();
        JPanel topPanel = new JPanel();
        turnField = new JLabel("X's turn");


        add(topPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        topPanel.add(turnField);

        JButton newGameButton = new JButton("New Game");
        topPanel.add(newGameButton);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        gamePanel.setLayout(new GridLayout(3,3,5,5));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                buttons.add(button);
                gamePanel.add(button);
                int FINALI = i;
                int FINALJ = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            TicTacToe_Model.Player currentPlayer = model.currentPlayer();
                            model.makeMove(FINALI, FINALJ);
                            button.setFont(new Font("Arial", Font.BOLD, 120));

                            button.setText(currentPlayer.toString());
                            turnField.setText(model.currentPlayer() + "'s turn");


                            TicTacToe_Model.Player winner = model.getWinner();
                            if (winner != TicTacToe_Model.Player.__) {
                                turnField.setText(winner + " WINS!!!");
                                JOptionPane.showMessageDialog(null, winner + " WINS!!!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);

                            } else if (model.boardFull()) {
                                turnField.setText("It's a DRAW!!!!");
                                JOptionPane.showMessageDialog(null, "HA You both lose!!! cuz it's a DRAW!!!!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(),"You can't do that!!!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                
            }
            
        }
        setVisible(true);

    }
    private void resetGame() {
        model.resetGame();

        turnField.setText("X's turn");

        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }

    }
}


