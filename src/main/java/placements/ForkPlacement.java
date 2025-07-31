package placements;

import java.util.Optional;

import boards.TicTacToeBoard;
import game.Cell;
import game.GameInfo;
import game.Player;

public class ForkPlacement implements Placement {

    private static ForkPlacement forkPlacement;
    
    private ForkPlacement() {
        // Private constructor to prevent instantiation
    }
    public synchronized static Placement get() {
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement, ForkPlacement::new);
        return forkPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        // Implementation for placing a fork piece on the board
        // This is a placeholder; actual logic will depend on game rules
        GameInfo gameInfo = ruleEngine.getInfo(board);
        Cell best = null;
        if(gameInfo.hasAFork()) {
            best = gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        // Logic to determine the next placement strategy
        return CenterPlacement.get(); // Example of returning a fork placement
    }

}
