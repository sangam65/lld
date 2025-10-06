package ATM.state;


import ATM.AtmSystem;
import ATM.enums.OperationType;



public interface ATMState {
 
    void insertCardState(AtmSystem atmSystem,String cardNumber);
    void enterPin(AtmSystem atmSystem,String pin);
    void selectOperation(AtmSystem atmSystem,OperationType operationType,int... args);
    void ejectCard(AtmSystem atmSystem);
    



}
