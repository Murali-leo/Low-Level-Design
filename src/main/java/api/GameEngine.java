package api;

import boards.Board;
import boards.TicTacToeBoard;
import game.Move;
import game.Player;

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
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
