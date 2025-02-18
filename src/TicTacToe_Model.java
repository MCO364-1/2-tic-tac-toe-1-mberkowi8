
public class TicTacToe_Model {
    private boolean gameOver;

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                board[i][j] = Player.__;

            }
        }
        currentPlayer = Player.X;
        gameOver = false;
    }

    public boolean boardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Player.__) {
                    return false;
                }
            }

        }
        gameOver = true;
        return true;
    }

    public enum Player {X, O, __}

    private Player currentPlayer;

    //3x3 Board
    private Player board[][] = new Player[3][3];
    /*
        |   |
    -------------
        |   |
    -------------
        |   |
     */

    public TicTacToe_Model() {
        resetGame();
    }

    public Player getPosition(int x, int y) {
        boundsCheck(x, y);
        // bounds check and vacancy check
        return board[x][y];
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver || getWinner() != Player.__ || boardFull();
    }

    /**
     * The currentPlayer's move
     *
     * @param x
     * @param y
     *
     */
    public void makeMove(int x, int y) {
        //winner check
        if (getWinner() != Player.__) {
            gameOver = true;
            throw new IllegalArgumentException("There is already a winner.");
        }

        if (getPosition(x, y) != Player.__) {
            throw new IllegalArgumentException("That spot is already taken!");
        }

        //actual mechanism for making the move
        board[x][y] = currentPlayer;

        if (hasWinner(currentPlayer)) {
            gameOver = true;
        } else if (boardFull()) {
            //this already sets game over to true
        } else {
            //if all checks pass and we get to here, record the move and switch the turn to the other player
            currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
        }
    }

    private void boundsCheck(int x, int y) {
        if ((x < 0 || x > 2) || (y < 0 || y > 2)) {
            throw new IllegalArgumentException("Out of bounds! That isn't a spot on the board.");
        }
    }

    public Player getWinner() {
        for (Player player : new Player[]{Player.X, Player.O}) {
            if (hasWinner(player)) {
                return player;
            }
        }
        return Player.__;
    }

    private boolean hasWinner(Player player) {
        for (int i = 0; i < 3; i++) {
            //check rows
            if (((board[i][0] == player) && (board[i][1] == player) && (board[i][2] == player)) ||
                    // check columns
                    ((board[0][i] == player) && (board[1][i] == player) && (board[2][i] == player))) {
                return true;
            }
        }
        //check top left to bottom right diagonal \
        return (((board[0][0] == player) && (board[1][1] == player) && (board[2][2] == player)) ||
                //check top right to bottom left diagonal /
                ((board[0][2] == player) && (board[1][1] == player) && (board[2][0] == player)));
    }
}


