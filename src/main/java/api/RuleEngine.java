package api;

import java.util.HashMap;
import java.util.Map;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.GameInfo;
import game.GameInfoBuilder;
import game.GameState;
import game.Move;
import game.Player;
import game.Rule;
import game.RuleSet;

public class RuleEngine {

    Map<String, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine() {
        // Initialize rules if needed
        ruleMap.put(TicTacToeBoard.class.getSimpleName(), TicTacToeBoard.getRules());
    }

    public GameInfo getInfo(Board board) {
        if (board instanceof TicTacToeBoard) {
            GameState gameState = getState(board);
            final String[] players = new String[] { "X", "O" };
            for (int index = 0; index < players.length; index++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Board b = board.copy();
                        Player player = new Player(players[index]);
                        b.move(new Move(new Cell(i, j), player));
                        boolean canStillWin = false;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                Board tempBoard = b.copy();
                                tempBoard.move(new Move(new Cell(k, l), player.flip()));
                                if (getState(tempBoard).getWinner().equals(player.flip().symbol())) {
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if (canStillWin) {
                                break;
                            }
                        }
                        if (canStillWin) {
                            return new GameInfoBuilder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .player(player.flip())
                                    .build();
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .build();
        } else {
            throw new IllegalArgumentException("Unsupported board type: " + board.getClass().getSimpleName());
        }
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            RuleSet<TicTacToeBoard> rules = (RuleSet<TicTacToeBoard>) ruleMap.get(TicTacToeBoard.class.getSimpleName());
            for (Rule<TicTacToeBoard> rule : rules) {
                GameState state = rule.condition.apply(ticTacToeBoard);
                if (state.isOver()) {
                    return state;
                }
            }
            return new GameState(false, "-");
        } else {
            throw new IllegalArgumentException("Unsupported board type: " + board.getClass().getSimpleName());
        }
    }
}
