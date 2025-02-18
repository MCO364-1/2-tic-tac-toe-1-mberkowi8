import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToe_ModelTests {
    TicTacToe_Model model = new TicTacToe_Model();

    @Test
    void testInitialBoard() {
        assertEquals(TicTacToe_Model.Player.X, model.currentPlayer());
        assertFalse(model.isGameOver());
        assertEquals(TicTacToe_Model.Player.__, model.getWinner());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(TicTacToe_Model.Player.__, model.getPosition(i, j));
            }
        }
    }

    @Test
    void testMakeMove() {
        model.makeMove(0,0);
        assertEquals(TicTacToe_Model.Player.X, model.getPosition(0, 0));
        assertEquals(TicTacToe_Model.Player.O, model.currentPlayer());
    }

    @Test
    void testIllegalMoveSpotAlreadyTaken() {
        model.makeMove(0,0);
        assertThrows(IllegalArgumentException.class, () -> model.makeMove(0,0)); // throws exception
    }

    @Test
    void testIllegalMoveOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> model.makeMove(3,3)); //throws exception
    }

    @Test
    void testWinHorizontal() {
        model.makeMove(0, 0); // X
        model.makeMove(1, 0); // O
        model.makeMove(0, 1); // X
        model.makeMove(1, 1); // O
        model.makeMove(0, 2); // X

        assertTrue(model.isGameOver());
        assertEquals(TicTacToe_Model.Player.X, model.getWinner());
    }

    @Test
    void testWinVertical() {

        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(1, 0); // X
        model.makeMove(0, 2); // O
        model.makeMove(2, 0); // X

        assertTrue(model.isGameOver());
        assertEquals(TicTacToe_Model.Player.X, model.getWinner());
    }

    @Test
    void testWinDiagonal() {
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(1, 1); // X
        model.makeMove(0, 2); // O
        model.makeMove(2, 2); // X

        assertTrue(model.isGameOver());
        assertEquals(TicTacToe_Model.Player.X, model.getWinner());
    }

    @Test
    void testFullBoardDraw() {
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(0, 2); // X
        model.makeMove(1, 0); // O
        model.makeMove(1, 2); // X
        model.makeMove(1, 1); // O
        model.makeMove(2, 0); // X
        model.makeMove(2, 2); // O
        model.makeMove(2, 1); // X

        assertTrue(model.isGameOver());
        assertTrue(model.boardFull());
        assertEquals(TicTacToe_Model.Player.__, model.getWinner());
    }

    @Test
    void testResetGame() {
        model.makeMove(0, 0);
        model.resetGame();

        assertEquals(TicTacToe_Model.Player.X, model.currentPlayer());
        assertFalse(model.isGameOver());
        assertEquals(TicTacToe_Model.Player.__, model.getPosition(0, 0));
    }

}
