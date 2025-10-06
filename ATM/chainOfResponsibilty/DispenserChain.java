package ATM.chainOfResponsibilty;

public interface DispenserChain {
    void setNextChain(DispenserChain dispenserChain);
    void dispense(int amount);
    boolean canDispense(int amount);
    
}
