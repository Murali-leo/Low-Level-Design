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
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getCell(i, j) == "-") {
                        return new Move(new Cell(i, j), computer);
                    }
                }
            }
            throw new IllegalArgumentException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
