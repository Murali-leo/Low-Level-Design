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
            
            GameState rowWin = findStreak((i, j) -> ticTacToeBoard.getSymbol(i, j));
            if (rowWin != null) return rowWin;

            GameState colWin = findStreak((i, j) -> ticTacToeBoard.getSymbol(j, i));    
            if (colWin != null) return colWin;

            GameState diagWin = findDiagStreak(i -> ticTacToeBoard.getSymbol(i, i));
            if (diagWin != null) return diagWin;

            GameState revDiagWin = findDiagStreak(i -> ticTacToeBoard.getSymbol(i, 2-i));
            if (revDiagWin != null) return revDiagWin;

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

    private GameState findDiagStreak(Function<Integer, String> diag) {
        boolean possibleStreak = true;
        for(int i = 0; i < 3; i++) {
            if(diag.apply(i) == "-" || !diag.apply(0).equals(diag.apply(i))) {
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak) {
            return new GameState(true, diag.apply(0));
        }
        return null;
    }

    private GameState findStreak(BiFunction<Integer, Integer, String> next) {
        for(int i = 0; i < 3; i++) {
            Boolean possibleStreak = true;
            for (int j = 0; j < 3; j++) {
                if(next.apply(i, j) == "-" || !next.apply(i, 0).equals(next.apply(i, j))) {
                    possibleStreak = false;
                    break;
                }
            }
            if(possibleStreak) {
                return new GameState(true, next.apply(i, 0));
            }
        }
        return null;
    }

}
