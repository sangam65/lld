package digitalWalletService.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import digitalWalletService.exception.WalletException;

public class Account {
    private final String accountId;
    private double amount;
    private final User user;
    private final List<TransactionHistory> transactionHistories;
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);

    public ReentrantReadWriteLock getReentrantReadWriteLock() {
        return reentrantReadWriteLock;
    }

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
        this.accountId = UUID.randomUUID().toString();
        this.amount = amount;
        this.user = user;
        this.transactionHistories = new ArrayList<>();
    }

    public double getAmount() throws WalletException, InterruptedException {
        if (!reentrantReadWriteLock.readLock().tryLock(2000, TimeUnit.MILLISECONDS)) {
            throw new WalletException("Please retry later");
        }
        
        try {
            return amount;
        }
        finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }

    public void changeAmount(double amount) throws WalletException, InterruptedException {
        if (!reentrantReadWriteLock.writeLock().tryLock(2000, TimeUnit.MILLISECONDS)) {
            throw new WalletException("Could not acquire write lock");
        }

        try {
            this.amount = this.amount + amount;
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    public void addTransaction(TransactionHistory transactionHistory) throws WalletException, InterruptedException {
        if (!reentrantReadWriteLock.writeLock().tryLock(2000, TimeUnit.MILLISECONDS)) {
            throw new WalletException("Please retry later");
        }

        try {
            transactionHistories.add(transactionHistory);
        } finally {
            reentrantReadWriteLock.writeLock().unlock(); // Add this
        }

    }
}
