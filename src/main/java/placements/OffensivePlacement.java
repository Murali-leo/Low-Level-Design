package placements;

import java.util.Optional;

import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;

public class OffensivePlacement implements Placement {

    private static OffensivePlacement offensivePlacement;
    private OffensivePlacement() {
        // Private constructor to prevent instantiation
    }
    public synchronized static Placement get() {
        offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement, OffensivePlacement::new);
        return offensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        // Implementation for placing an offensive piece on the board
        // This is a placeholder; actual logic will depend on game rules
        return Optional.ofNullable(offense(player, board));
    }

    @Override
    public Placement next() {
        // Logic to determine the next placement strategy
        return DefensivePlacement.get(); // Example of returning a defensive placement
    }

    private Cell offense(Player player, TicTacToeBoard board) {
        // attacking moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j).equals("-")) {
                    // Check if placing the player's symbol here would win the game
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }
    
}
