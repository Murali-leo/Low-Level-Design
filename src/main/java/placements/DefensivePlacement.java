package placements;

import java.util.Optional;

import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;

public class DefensivePlacement implements Placement {

    private static DefensivePlacement defensivePlacement;

    private DefensivePlacement() {
        // Private constructor to prevent instantiation
    }
    public synchronized static Placement get() {
        defensivePlacement = (DefensivePlacement) Utils.getIfNull(defensivePlacement, DefensivePlacement::new);
        return defensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        // Implementation for placing an offensive piece on the board
        // This is a placeholder; actual logic will depend on game rules
        return Optional.ofNullable(defensive(board, player));
    }

    @Override
    public Placement next() {
        // Logic to determine the next placement strategy
        return ForkPlacement.get(); // Example of returning a defensive placement
    }

    private Cell defensive(TicTacToeBoard board, Player player) {

        // Defensive moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j).equals("-")) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

}
