package ATM.state;


import ATM.AtmSystem;

import ATM.enums.OperationType;


public class AuthenticatedState implements ATMState {

    @Override
    public void insertCardState(AtmSystem atmSystem, String cardNumber) {
        System.out.println("Card is already inside the atm");
    }

    @Override
    public void enterPin(AtmSystem atmSystem, String pin) {
        System.out.println("Pin has already been entered and You are already authenticated");

    }

    @Override
    public void selectOperation(AtmSystem atmSystem, OperationType operationType, int... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectOperation'");
    }

    @Override
    public void ejectCard(AtmSystem atmSystem) {
        System.out.println("Card has been ejected. Thank you for using our ATM.");
        atmSystem.setCurrentCard(null);
        atmSystem.changeState(new IdleState());
    }

    
}