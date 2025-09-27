package VendingMachine;

import java.util.ArrayList;
import java.util.List;

import VendingMachine.states.State;

public class VendingMachine {
    private List<Coin>coins;
    private State state;
    private Inventory inventory;
    public List<Coin> getCoins() {
        return coins;
    }
    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public VendingMachine(){
         coins= new ArrayList<>();
         inventory=new Inventory(10);
        //  state=
    }

}
