package digitalWalletService.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
    private final String accountId;
    private double amount;
    private final User user;
    private final List<TransactionHistory> transactionHistories;
    public List<TransactionHistory> getTransactionHistory() {
        return transactionHistories;
    }
    public String getAccountId() {
        return accountId;
    }
    public User getUser() {
        return user;
    }
    public Account(String accountId, double amount, User user) {
        this.accountId =UUID.randomUUID().toString();
        this.amount = amount;
        this.user = user;
        this.transactionHistories=new ArrayList<>();
    }
    public double getAmount() {
        return amount;
    }
    public void changeAmount(double amount) {
        this.amount=this.amount+ amount;
    }
    public void addTransaction(TransactionHistory transactionHistory){
        transactionHistories.add(transactionHistory);
    }
}
