package game;

public class Player {

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

}
