package boards;

import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;

public class TicTacToeBoard extends Board {
    String[][] cells;

    // Constructor for TicTacToeBoard
    public TicTacToeBoard() {
        this.cells = new String[3][3]; // Initialize the 2D array

        // Loop through the cells and set initial values to "-"
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.cells[i][j] = "-";
            }
        }
    }

    public String getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += cells[i][j];
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public void move(Move move) {
       setCell(move.getCell(), move.getPlayer().symbol);
    }
}
