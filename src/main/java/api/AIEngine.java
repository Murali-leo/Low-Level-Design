package api;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.GameInfo;
import game.Move;
import game.Player;

public class AIEngine {

    private RuleEngine ruleEngine;

    public AIEngine(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    public Move suggestMove(Player player, Board board) {
        int threshold = 4; // Default threshold for TicTacToe
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            Cell suggestion;
            if (isStarting(ticTacToeBoard, threshold)) {
                suggestion = getBasicMove(ticTacToeBoard);
            } else if (isStarting(ticTacToeBoard, threshold + 1)) {
                suggestion = getCellToPlay(player, ticTacToeBoard);
            } else {
                suggestion = getOptimalMove(player, ticTacToeBoard);
            }
            if (suggestion != null) {
                return new Move(suggestion, player);
            } else {
                throw new IllegalArgumentException("No valid moves available");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Cell getOptimalMove(Player player, TicTacToeBoard board) {
        // Offensive moves
        Cell best = offense(player, board);
        if (best != null) {
            return best;
        }
        // Defensive moves
        Cell defensive = defensive(player, board);
        if (defensive != null) {
            return defensive;
        }
        // Check for forks
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if(gameInfo.hasAFork()) {
            best = gameInfo.getForkCell();
            return best;
        }
        // If the center is available, play there
        if (board.getSymbol(1, 1).equals("-")) {
            return new Cell(1, 1);
        }
        // If the corners are available, play in one of them
        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for(int i = 0; i < corners.length; i++) {
            int x = corners[i][0];
            int y = corners[i][1];
            if (board.getSymbol(x, y).equals("-")) {
                return new Cell(x, y);
            }
        }
        return null;
    }

    private Cell getCellToPlay(Player player, TicTacToeBoard board) {
        // Offensive moves
        Cell best = offense(player, board);
        if (best != null) {
            return best;
        }
        // Defensive moves
        Cell defensive = defensive(player, board);
        if (defensive != null) {
            return defensive;
        }
        return getBasicMove(board);
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

    private Cell getBasicMove(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getCell(i, j).equals("-")) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

    private Cell defensive(Player player, TicTacToeBoard board) {

        // Defensive moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j).equals("-")) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

    private Cell offense(Player player, TicTacToeBoard board) {
        // attacking moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j).equals("-")) {
                    // Check if placing the player's symbol here would win the game
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }
}
