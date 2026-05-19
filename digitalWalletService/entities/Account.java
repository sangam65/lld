package digitalWalletService.entities;

import java.util.UUID;

public class Account {
    private final String accountId;
    private double amount;
    private final User user;
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
    }
    public double getAmount() {
        return amount;
    }
    public void changeAmount(double amount) {
        this.amount=this.amount+ amount;
    }
}
