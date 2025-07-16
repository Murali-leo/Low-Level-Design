package gamestate;

public class GameResult {
    
    boolean isOver;
    String winner;

    public GameResult(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean isOver) {
        this.isOver = isOver;
    }

    @Override
    public String toString() {
        return "GameResult [isOver=" + isOver + ", winner=" + winner + "]";
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
