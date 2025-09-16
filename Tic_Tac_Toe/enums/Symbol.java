package enums;

public enum Symbol {

    X('X'),
    Y('Y'),
    EMPTY('_');


    private final char symbol;



    Symbol(char symbol) {
        this.symbol = symbol;
    }
    public char getSymbol(){
        return symbol;
    }


    

    
}
