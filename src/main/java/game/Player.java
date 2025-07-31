package game;

public class Player {
    int timeUsedInMillis;
    User id;

    public String symbol;

    public Player(String symbol) {
        this.symbol =symbol;
    }

    public String symbol() {
        return symbol;
    }

    public Player flip() {
       return new Player(symbol.equals("X") ? "O" : "X");
    }

    public int getTimeUsedInMillis() {
        return timeUsedInMillis;
    }

    public void setTimeUsedInMillis(int timeUsedInMillis) {
        this.timeUsedInMillis = timeUsedInMillis;
    }

}
