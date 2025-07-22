package api;

import boards.TicTacToeBoard;
import gamestate.Board;
import gamestate.GameState;

public class RuleEngine {
    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {

            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            String firstCharacter = "-";
            boolean rowComplete = true;

            for (int i = 0; i < 3; i++) {
                firstCharacter = ticTacToeBoard.getCell(i, 0);
                rowComplete = firstCharacter != "-";
                if (firstCharacter != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(ticTacToeBoard.getCell(i, j))) {
                            rowComplete = false;
                        }
                    }
                    if (rowComplete) {
                        return new GameState(true, firstCharacter);
                    }
                }
            }

            boolean colComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = ticTacToeBoard.getCell(0, i);
                colComplete = firstCharacter != "-";
                if (firstCharacter != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(ticTacToeBoard.getCell(j, i))) {
                            colComplete = false;
                        }
                    }
                    if (colComplete) {
                        return new GameState(true, firstCharacter);
                    }
                }
            }

            firstCharacter = ticTacToeBoard.getCell(0, 0);
            boolean diagComplete = firstCharacter != "-";
            for (int i = 1; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(ticTacToeBoard.getCell(i, i))) {
                    diagComplete = false;
                }
            }

            if (diagComplete) {
                return new GameState(true, firstCharacter);
            }

            firstCharacter = ticTacToeBoard.getCell(0, 2);
            boolean revDiagComplete = firstCharacter != "-";
            for (int i = 1; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(ticTacToeBoard.getCell(i, 2 - i))) {
                    revDiagComplete = false;
                }
            }

            if (revDiagComplete) {
                return new GameState(true, firstCharacter);
            }

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

}
