package VendingMachine;

public enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(10);
    public  int value;
    
    private Coin(int value){
        this.value=value;
    }
    

}
