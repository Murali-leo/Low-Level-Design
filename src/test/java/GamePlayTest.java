
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;
import gamestate.Player;

public class GamePlayTest {
    AIEngine aiEngine;
    RuleEngine ruleEngine;
    GameEngine gameEngine;

    @Before
    public void setUp() {
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
        gameEngine = new GameEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][] {{0, 0}, {0, 1}, {0, 2}};
        playMoves(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][] {{0, 1}, {1, 1}, {2, 1}};
        playMoves(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][] {{0, 0}, {1, 1}, {2, 2}};
        playMoves(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

     @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][] {{0, 2}, {1, 1}, {2, 0}};
        playMoves(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

     @Test
    public void checkForComputerWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][] {{2, 2}, {1, 1}, {2, 0}};
        playMoves(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());
    }

     private void playMoves(Board board, int[][] moves) {
        int next = 0;
        while (!ruleEngine.getState(board).isOver() && next < moves.length) {
            Player computer = new Player("O"),
                    player = new Player("X");
            System.out.println(board);
            int row = moves[next][0];
            int col = moves[next][1];
            next++;
            Move oppMove = new Move(new Cell(row, col), player);
            gameEngine.move(board, player, oppMove);
            System.out.println(board);
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computer, computerMove);
            }
        }
    }
}
