package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.GameInfo;
import game.GameInfoBuilder;
import game.GameState;
import game.Move;
import game.Player;

public class RuleEngine {
    Map<String, List<Rule>> ruleMap = new HashMap<>();

    public RuleEngine() {
        // Initialize rules if needed
        ruleMap.put(TicTacToeBoard.class.getSimpleName(), new ArrayList<>());
        ruleMap.get(TicTacToeBoard.class.getSimpleName())
                .add(new Rule<TicTacToeBoard>(board -> outerTraversal((i, j) -> board.getSymbol(i, j))));
        ruleMap.get(TicTacToeBoard.class.getSimpleName())
                .add(new Rule<TicTacToeBoard>(board -> outerTraversal((i, j) -> board.getSymbol(j, i))));
        ruleMap.get(TicTacToeBoard.class.getSimpleName())
                .add(new Rule<TicTacToeBoard>(board -> traverse(i -> board.getSymbol(i, i))));
        ruleMap.get(TicTacToeBoard.class.getSimpleName())
                .add(new Rule<TicTacToeBoard>(board -> traverse(i -> board.getSymbol(i, 2 - i))));
        ruleMap.get(TicTacToeBoard.class.getSimpleName()).add(new Rule<TicTacToeBoard>(board -> {
            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getCell(i, j) != "-") {
                        countOfFilledCells++;
                    }
                }
            }
            if (countOfFilledCells == 9) {
                return new GameState(true, "draw");
            }
            return new GameState(false, "-");
        }));
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

    @SuppressWarnings("unchecked")
    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {

            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for (Rule<TicTacToeBoard> rule : ruleMap.get(TicTacToeBoard.class.getSimpleName())) {
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

    private GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
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

    private GameState traverse(Function<Integer, String> traversal) {
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

class Rule<T extends Board> {
    Function<T, GameState> condition;

    public Rule(Function<T, GameState> condition) {
        this.condition = condition;
    }

}
