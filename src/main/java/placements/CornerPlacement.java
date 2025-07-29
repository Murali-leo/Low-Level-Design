package placements;

import java.util.Optional;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;

public class CornerPlacement implements Placement {

    public static CornerPlacement cornerPlacement;

    private CornerPlacement() {
        // Private constructor to prevent instantiation
    }

    public static CornerPlacement get() {
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement, CornerPlacement::new);
        return cornerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        // Placeholder for actual corner placement logic
        // This is a placeholder; actual logic will depend on game rules
        return Optional.ofNullable(placeInCorner(board)); 
    }

    @Override
    public Placement next() {
        return null; 
    }

    private Cell placeInCorner(TicTacToeBoard board) {
        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        Cell cornerCell = null;
        for(int i = 0; i < corners.length; i++) {
            int x = corners[i][0];
            int y = corners[i][1];
            if (board.getSymbol(x, y).equals("-")) {
                cornerCell = new Cell(x, y);
            }
        }
        return cornerCell;
    }
}
