package stockManagementsystem.entities;

import java.math.BigDecimal;
import java.util.UUID;



public class Account {
    private final User user;
    private final String accountId;
    private final String bankName;
    public User getUser() {
        return user;
    }
    public String getAccountId() {
        return accountId;
    }
    public String getBankName() {
        return bankName;
    }
    private BigDecimal balance;
    public BigDecimal getBalance() {
        return balance;
    }
    public synchronized void addBalance(BigDecimal balance) {
        if(balance.compareTo(BigDecimal.ZERO)<0){
            throw new RuntimeException("Balance can't be zero");
        }
        this.balance=this.balance.add(balance);
    }
    public synchronized void deductBalance(BigDecimal balance){
        if(balance.compareTo(BigDecimal.ZERO)<0){
            throw new RuntimeException("Balance can't be zero");
        }
        this.balance=this.balance.subtract(balance);
    }
    public Account(User user,  String bankName, BigDecimal balance) {
        if(balance.compareTo(BigDecimal.ZERO)<0){
            throw new RuntimeException("Balance can't be zero");
        }
        this.user = user;
        this.accountId =UUID.randomUUID().toString();
        this.bankName = bankName;
        this.balance = balance;
    }
}
