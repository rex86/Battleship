package battleship;

public enum TableSymbols {
    UNTOUCHED("~"), // ~
    HIT("x"),       // x
    MISS("m")       // m
            ;

    private final String symbol;

    TableSymbols(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
