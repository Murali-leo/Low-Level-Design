package boards;

import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;

public class TicTacToeBoard implements Board {
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
        if(cells[cell.getRow()][cell.getCol()].equals("-")) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else {
            throw new IllegalArgumentException("Cell is already occupied");
        }
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

    public String getSymbol(int i, int j) {
        return cells[i][j];
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard copy = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy.setCell(new Cell(i, j), this.cells[i][j]);
            }
        }
        return copy;
    }
}
