package api;

import java.util.function.BiFunction;
import java.util.function.Function;

import boards.TicTacToeBoard;
import gamestate.Board;
import gamestate.GameState;

public class RuleEngine {
    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {

            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;

            GameState rowWin = outerTraversal((i, j) -> ticTacToeBoard.getSymbol(i, j));
            if (rowWin.isOver())
                return rowWin;

            GameState colWin = outerTraversal((i, j) -> ticTacToeBoard.getSymbol(j, i));
            if (colWin.isOver())
                return colWin;

            GameState diagWin = traverse(i -> ticTacToeBoard.getSymbol(i, i));
            if (diagWin.isOver())
                return diagWin;

            GameState revDiagWin = traverse(i -> ticTacToeBoard.getSymbol(i, 2 - i));
            if (revDiagWin.isOver())
                return revDiagWin;

            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getCell(i, j) != "-") {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) {
                return new GameState(true, "draw");
            } else {
                return new GameState(false, "-");
            }
        } else {
            return new GameState(false, "-");
        }
    }

    private GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState traversal = traverse(j -> next.apply(ii, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    private GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (traversal.apply(j) == "-" || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            return new GameState(true, traversal.apply(0));
        }
        return result;
    }
}
