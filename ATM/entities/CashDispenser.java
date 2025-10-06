package ATM.entities;

import ATM.chainOfResponsibilty.DispenserChain;

public class CashDispenser {
    private final DispenserChain dispenserChain;
    public CashDispenser(DispenserChain dispenserChain){
        this.dispenserChain=dispenserChain;
    }
    public synchronized void dispense(int amount){
        dispenserChain.dispense(amount);
    }
    public synchronized boolean canDispense(int amt){
        return dispenserChain.canDispense(amt);
    }
}
