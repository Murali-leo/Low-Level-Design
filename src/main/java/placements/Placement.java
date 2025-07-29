package placements;

import java.util.Optional;

import api.RuleEngine;
import boards.TicTacToeBoard;
import game.Cell;
import game.Player;

public interface Placement {

    RuleEngine ruleEngine = new RuleEngine();
    Optional<Cell> place(TicTacToeBoard board, Player player);
    Placement next();
}
