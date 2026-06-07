package stockManagementsystem.entities;

import java.math.BigDecimal;

import stockManagementsystem.exception.StockManagementException;

public class User {
    private final String userId;
    private final String username;
    private final String email;

    private final Account account;
    private final Portfolio portfolio;
    private BigDecimal balance;

    public synchronized BigDecimal getBalance() {
        return balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Account getAccount() {
        return account;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public User(String userId, String username, String email, Account account) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.portfolio = new Portfolio(this);
        this.account = account;
        this.balance = BigDecimal.ZERO;
    }

    public synchronized void addBalance(BigDecimal balance) {
        if (account.getBalance().compareTo(balance) < 0) {
            throw new StockManagementException("Account does not has sufficient balance");
        }
        account.deductBalance(balance);
        this.balance.add(balance);
    }

    public synchronized void deductBalance(BigDecimal balance) {
        if (this.balance.compareTo(balance) < 0) {
            throw new StockManagementException("Stock Account does not has sufficient balance");
        }
        this.balance.subtract(balance);
    }

}
