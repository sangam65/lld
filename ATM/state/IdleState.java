package ATM.state;


import ATM.AtmSystem;
import ATM.entities.Card;
import ATM.enums.OperationType;

public class IdleState implements ATMState {

    @Override
    public void insertCardState(AtmSystem atmSystem, String cardNumber) {
        Card card=atmSystem.getCard(cardNumber);
        if(card==null){
            ejectCard(atmSystem);
        }
        else{
            atmSystem.setCurrentCard(card);
            atmSystem.changeState(new HasCardState());
        }
    }

    @Override
    public void enterPin(AtmSystem atmSystem, String pin) {
        System.out.println("Error: Please insert a card first");
    }

    @Override
    public void selectOperation(AtmSystem atmSystem, OperationType operationType, int... args) {
       System.out.println("Error: Please insert a card first");
    }

    @Override
    public void ejectCard(AtmSystem atmSystem) {
        if(atmSystem.getCurrentCard()==null)
        System.out.println("Error: Card not found");
        atmSystem.setCurrentCard(null);
        
    }

    

    
    
}