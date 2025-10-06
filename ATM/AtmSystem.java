package ATM;

import java.util.concurrent.atomic.AtomicLong;

import ATM.chainOfResponsibilty.DispenserChain;
import ATM.chainOfResponsibilty.NoteDispenser100;
import ATM.chainOfResponsibilty.NoteDispenser1000;
import ATM.chainOfResponsibilty.NoteDispenser500;
import ATM.entities.BankService;
import ATM.entities.Card;
import ATM.entities.CashDispenser;
import ATM.enums.OperationType;
import ATM.state.ATMState;
import ATM.state.IdleState;

public class AtmSystem {
    private static AtmSystem atmInstance;
    private final BankService bankService;
    private final CashDispenser cashDispenser;
    
    private ATMState atmState;
    private Card currentCard;
   

    @SuppressWarnings("unused")
    private static final AtomicLong transactionLong= new AtomicLong(0);
    private AtmSystem(){
        this.atmState=new IdleState();
        this.currentCard=null;
        this.bankService=new BankService();

        DispenserChain dispenser1000=new NoteDispenser1000(100);
        DispenserChain dispenser500=new NoteDispenser500(50);
        DispenserChain dispenser100=new NoteDispenser100(200);
        dispenser1000.setNextChain(dispenser500);
        dispenser500.setNextChain(dispenser100);
        this.cashDispenser=new CashDispenser(dispenser1000);
    }
    public static AtmSystem getInstance(){
        if(atmInstance==null){
            synchronized(AtmSystem.class){
                if(atmInstance==null){
                    atmInstance=new AtmSystem();
                }
            }
        }
        return atmInstance;
    }
    public void changeState(ATMState atmState){
        this.atmState=atmState;
        
    }
    public void insertCard(String cardId){
        atmState.insertCardState(this, cardId);
    }
    public void enterPin(String pin){
        atmState.enterPin(atmInstance, pin);
    }
    public Card getCard(String cardId){
        return bankService.getCard(cardId);
    }
    public boolean authenticate(String pin){
        return bankService.authenticate(currentCard, pin);
    }

    public void checkBalance(){
        int balance=bankService.getBalance(currentCard.getCardId());
        System.out.println("Balance of your account is "+balance);

    }
    public Card getCurrentCard(){
        return currentCard;
    }
    public BankService getBankService(){
        return bankService;
    }
    public void depositBalance(int amount){
        bankService.depositMoney(currentCard, amount);
    }
    public void selectOperation(OperationType operationType,int... args){
        atmState.selectOperation(this, operationType, args);
    }
    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public void withDrawMoney(int amt){
        if(!cashDispenser.canDispense(amt)){
            throw new RuntimeException("Bank does not have required notes to fulfil this withdrawal");
        }
        bankService.withDrawMoney(currentCard, amt);
        try{
            cashDispenser.dispense(amt);
        }
        catch(Exception e){
            bankService.depositMoney(currentCard, amt);
        }
    }
     public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }
}
