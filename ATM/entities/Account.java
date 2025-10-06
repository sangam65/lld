package ATM.entities;

public class Account {
    private final String accountNumber;
    private int balance;
   
    private final Card card;
    public String getAccountNumber() {
        return accountNumber;
    }
    public int getBalanace() {
        return balance;
    }
    public Card getCard() {
        return card;
    }
    public Account(String accountNumber, int balance, Card card) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.card = card;
    }
    public synchronized void deposit(int amount){
        balance+=amount;

    }
    public synchronized boolean withdraw(int amount){
        if(balance>=amount){
            balance-=amount;
            return true;
        }
        return false;
    }
}
