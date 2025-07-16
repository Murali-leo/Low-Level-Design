package api;

import boards.TicTacToeBoard;
import gamestate.Board;
import gamestate.Cell;
import gamestate.GameResult;
import gamestate.Move;
import gamestate.Player;

public class GameEngine {

    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Player player, Move move) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            ticTacToeBoard.setCell(move.getCell(), player.symbol());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GameResult isComplete(Board board) {
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
                        return new GameResult(true, firstCharacter);
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
                        return new GameResult(true, firstCharacter);
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
                return new GameResult(true, firstCharacter);
            }

            firstCharacter = ticTacToeBoard.getCell(0, 2);
            boolean revDiagComplete = firstCharacter != "-";
            for (int i = 1; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(ticTacToeBoard.getCell(i, 2 - i))) {
                    revDiagComplete = false;
                }
            }

            if (revDiagComplete) {
                return new GameResult(true, firstCharacter);
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
                return new GameResult(true, "draw");
            } else {
                return new GameResult(false, "-");
            }
        } else {
            return new GameResult(false, "-");
        }
    }

    public Move suggestMove(Player computer, Board board) {

        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getCell(i, j) == "-") {
                        return new Move(new Cell(i, j));
                    }
                }
            }
            throw new IllegalArgumentException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
