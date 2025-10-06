package ATM.state;



import ATM.AtmSystem;

import ATM.enums.OperationType;

public class HasCardState implements ATMState{

    @Override
    public void insertCardState(AtmSystem atmSystem, String cardNumber) {
        System.out.println("Card is already present in the atm");
    }

    @Override
    public void enterPin(AtmSystem atmSystem, String pin) {
        boolean authenticated=atmSystem.authenticate(pin);
        if(authenticated==true){
            System.out.print("Authentication sucessful");
            atmSystem.changeState(new AuthenticatedState());
        }
        else{
            System.out.print("Incorrect Pin");
            ejectCard(atmSystem);
        }
    }

    @Override
    public void selectOperation(AtmSystem atmSystem, OperationType operationType, int... args) {
        System.out.println("Enter the pin first then you choose operation");
    }

    @Override
    public void ejectCard(AtmSystem atmSystem) {
        System.out.println("Card has been ejected. Thank you for using our ATM.");
        atmSystem.setCurrentCard(null);
        atmSystem.changeState(new IdleState());
    }

    

}
