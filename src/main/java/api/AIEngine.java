package api;

import boards.TicTacToeBoard;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;
import gamestate.Player;

public class AIEngine {
    public Move suggestMove(Player computer, Board board) {

        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            Move suggestion;
            if (isStarting(ticTacToeBoard, 4)) {
                suggestion = getBasicMove(computer, ticTacToeBoard);
            } else {
                suggestion = getSmartMove(computer, ticTacToeBoard);
            }
            if (suggestion != null) {
                return suggestion;
            } else {
                throw new IllegalArgumentException("No valid moves available");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Move getSmartMove(Player player, TicTacToeBoard ticTacToeBoard) {

        RuleEngine ruleEngine = new RuleEngine();

        // attacking moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(ticTacToeBoard.getSymbol(i, j).equals("-")) {
                    // Check if placing the player's symbol here would win the game
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = ticTacToeBoard.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }

        // Defensive moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(ticTacToeBoard.getSymbol(i, j).equals("-")) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = ticTacToeBoard.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }
        return getBasicMove(player, ticTacToeBoard);
    }

    private boolean isStarting(TicTacToeBoard ticTacToeBoard, int threshold) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!ticTacToeBoard.getCell(i, j).equals("-")) {
                    count++;
                }
            }
        }
        return count < threshold;
    }

    private Move getBasicMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getCell(i, j).equals("-")) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }
        return null;
    }
}
