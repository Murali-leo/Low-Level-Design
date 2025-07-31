package placements;

import java.util.Optional;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;

public class CenterPlacement implements Placement {

    private static CenterPlacement centerPlacement;

    private CenterPlacement() {
        // Private constructor to prevent instantiation
    }

    public synchronized static Placement get() {
        centerPlacement = (CenterPlacement) Utils.getIfNull(centerPlacement, CenterPlacement::new);
        return centerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        // Implementation for placing a piece in the center of the board
        // This is a placeholder; actual logic will depend on game rules
        Cell centerCell = null;
        if (board.getSymbol(1, 1).equals("-")) {
            centerCell = new Cell(1, 1);
        }
        return Optional.ofNullable(centerCell);
    }

    @Override
    public Placement next() {
        // Logic to determine the next placement strategy
        return CornerPlacement.get(); // Example of returning a center placement
    }
    
}
