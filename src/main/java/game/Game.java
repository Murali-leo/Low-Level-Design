package game;

public class Game {

    private GameConfig gameConfig;
    private Board board;
    Player winner;
    private int lastMoveTimeStampMillis;
    private int maxTimePerMove;
    private int maxTimePerPlayer;

    public void move(Move move, int timeStampMillis) {
        int timeTakenSinceLastMove = timeStampMillis - lastMoveTimeStampMillis;
        move.getPlayer().setTimeUsedInMillis(timeTakenSinceLastMove);
        if (gameConfig.timed) {
            moveForTimedGame(move, timeTakenSinceLastMove);
        } else {
            board.move(move);
        }
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        if (gameConfig.timePerMove != null) {
            if (moveMadeinTime(timeTakenSinceLastMove)) {
                board.move(move);
            } else {
                winner = move.getPlayer().flip();
            }
        } else {
            if (moveMadeinTime(move.getPlayer())) {
                board.move(move);
            } else {
                winner = move.getPlayer().flip();
            }
        }
    }

    private boolean moveMadeinTime(int timeTakenSinceLastMove) {
        return timeTakenSinceLastMove <= maxTimePerMove;
    }

    private boolean moveMadeinTime(Player player) {
        return player.getTimeUsedInMillis() <= maxTimePerPlayer;
    }
}
