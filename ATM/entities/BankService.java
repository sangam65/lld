package ATM.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ATM.exception.AccountException;

public class BankService {
    private final Map<String,Account> accounts=new ConcurrentHashMap<>();
    private final Map<String,Card>cards=new ConcurrentHashMap<>();
    private final Map<Card,Account>cardAccounts=new ConcurrentHashMap<>();
    public BankService(){
        
        Card card1=createCard("q26739011", "9231");
        createAccount("53678926567890", 18390, card1);

        Card card2=createCard("q26739012", "9221");
        createAccount("5363292e472849", 28390, card2);
    

    }
    public synchronized Card createCard(String cardId,String pin){
        Card card=new Card(cardId, pin);
        cards.put(cardId, card);
        return card;
    }
    public synchronized Account createAccount(String accountId,int balance,Card card){
        Account account=new Account(accountId, balance, card);
        accounts.put(accountId, account);
        linkCardToAccount(card, account);
        return account;
    }
    public synchronized void  linkCardToAccount(Card card,Account account){
        cardAccounts.put(card, account);
    }
    public Card getCard(String cardNumber) {
        return cards.getOrDefault(cardNumber, null);
    }
    public int getBalance(String cardNumber){
        Card card=getCard(cardNumber);
        Account account=cardAccounts.getOrDefault(card,null);
        return account.getBalanace();

    }
    public boolean authenticate(Card card,String pin){
        return card.getPin().equals(pin);
    } 
    public int withDrawMoney(Card card,int amount)throws AccountException{
        Account account=cardAccounts.get(card);
        boolean success=account.withdraw(amount);
        if(success==false){
            throw new AccountException("You don't have sufficient balance");
        }
        return amount;
    }
    public void depositMoney(Card card,int amount){
        Account account=cardAccounts.get(card);
        account.deposit(amount);
    }
}
