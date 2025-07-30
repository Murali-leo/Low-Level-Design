package game;

import java.util.function.Function;

import boards.CellBoard;

public class Rule {
    public Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }

}

