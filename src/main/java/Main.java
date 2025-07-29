import java.util.Scanner;

import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class Main {
    public static void main(String[] args) {
        AIEngine aiEngine = new AIEngine(new RuleEngine());
        RuleEngine ruleEngine = new RuleEngine();
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");
        int row, col;
        Scanner sc = new Scanner(System.in);
        // make moves
        while (!ruleEngine.getState(board).isOver()) {
            Player computer = new Player("O"),
                    player = new Player("X");
            System.out.println("Make your move");
            System.out.println(board);
            row = sc.nextInt();
            col = sc.nextInt();
            Move oppMove = new Move(new Cell(row, col), player);
            gameEngine.move(board, player, oppMove);
            System.out.println(board);
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computer, computerMove);
            }
        }
        System.out.println("Game Result >> "+ruleEngine.getState(board));
        System.out.println(board);
        sc.close();
    }
}
