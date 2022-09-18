package battleship;

public enum TableSymbols {
    UNTOUCHED("~"), // ~
    HIT("X"),       // x
    MISS("M")       // m
    ;

    private final String symbol;

    TableSymbols(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
