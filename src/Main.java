import java.util.Scanner;

import api.GameEngine;
import boards.TicTacToeBoard;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;
import gamestate.Player;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");
        int row, col;
        Scanner sc = new Scanner(System.in);
        // make moves
        while (!gameEngine.isComplete(board).isOver()) {
            Player computer = new Player("O"),
                    player = new Player("X");
            System.out.println("Make your move");
            System.out.println(board);
            row = sc.nextInt();
            col = sc.nextInt();
            Move oppMove = new Move(new Cell(row, col));
            gameEngine.move(board, player, oppMove);
            System.out.println(board);
            if (!gameEngine.isComplete(board).isOver()) {
                Move computerMove = gameEngine.suggestMove(computer, board);
                gameEngine.move(board, computer, computerMove);
            }
        }
        System.out.println("Game Result >> "+gameEngine.isComplete(board));
        System.out.println(board);
        sc.close();
    }
}
