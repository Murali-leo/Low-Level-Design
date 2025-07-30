
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import api.GameEngine;
import api.RuleEngine;
import boards.Board;
import game.Cell;
import game.Move;
import game.Player;

public class GamePlayTest {
    RuleEngine ruleEngine;
    GameEngine gameEngine;

    @Before
    public void setUp() {
        ruleEngine = new RuleEngine();
        gameEngine = new GameEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{0, 0}, {0, 1}, {0, 2}};
        int[][] secondPlayerMoves = new int[][] {{1, 0}, {1, 1}, {1, 2}};
        playMoves(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{1, 0}, {2, 0}, {0, 0}};
        int[][] secondPlayerMoves = new int[][] {{1, 1}, {2, 1}, {0, 1}};
        playMoves(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{0, 0}, {1, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][] {{0, 1}, {1, 0}, {2, 1}};
        playMoves(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

     @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{2, 0}, {1, 1}, {0, 2}};
        int[][] secondPlayerMoves = new int[][] {{2, 1}, {1, 0}, {0, 1}};
        playMoves(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

     @Test
    public void checkForSecondPlayerWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][] {{2, 2}, {1, 1}, {2, 1}};
        int[][] secondPlayerMoves = new int[][] {{0, 0}, {1, 0}, {2, 0}};
        playMoves(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());
    }

     private void playMoves(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int next = 0;
        while (!ruleEngine.getState(board).isOver()) {
            Player firstplayer = new Player("X"),
            secondPlayer = new Player("O");
                    
            int row = firstPlayerMoves[next][0];
            int col = firstPlayerMoves[next][1];
            Move firstMove = new Move(new Cell(row, col), firstplayer);
            gameEngine.move(board, firstplayer, firstMove);
            if (!ruleEngine.getState(board).isOver()) {
                int secondRow = secondPlayerMoves[next][0];
                int secondCol = secondPlayerMoves[next][1];
                Move sMove = new Move(new Cell(secondRow, secondCol), secondPlayer);
                gameEngine.move(board, secondPlayer, sMove);
            }
            next++;
        }
    }
}
