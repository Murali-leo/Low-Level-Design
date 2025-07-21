package api;

import boards.TicTacToeBoard;
import gamestate.Board;
import gamestate.Cell;
import gamestate.GameState;
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
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
