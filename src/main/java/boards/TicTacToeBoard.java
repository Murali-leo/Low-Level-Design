package boards;

import java.util.function.BiFunction;
import java.util.function.Function;

import game.Cell;
import game.GameState;
import game.Move;
import game.Rule;
import game.RuleSet;

public class TicTacToeBoard implements CellBoard {
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

    public static RuleSet<TicTacToeBoard> getRules() {

        RuleSet rules = new RuleSet();
       
        rules.add(new Rule(board -> outerTraversal((i, j) -> board.getSymbol(i, j))));
        rules.add(new Rule(board -> outerTraversal((i, j) -> board.getSymbol(j, i))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, i))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, 2 - i))));
        rules.add(new Rule(board -> {
            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSymbol(i, j) != "-") {
                        countOfFilledCells++;
                    }
                }
            }
            if (countOfFilledCells == 9) {
                return new GameState(true, "draw");
            }
            return new GameState(false, "-");
        }));
        return rules;
    }

    private static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState traversal = traverse(j -> next.apply(ii, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    private static GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (traversal.apply(j) == "-" || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            return new GameState(true, traversal.apply(0));
        }
        return result;
    }
}
