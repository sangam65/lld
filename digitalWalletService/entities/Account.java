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

    public double getAmount() {
        try {
            boolean res=reentrantReadWriteLock.readLock().tryLock(2000, TimeUnit.MILLISECONDS);
            if(res==true){
                return amount;
            }
            throw new WalletException("Please retry later");
        }

        catch (InterruptedException e) {
            throw new WalletException("Please retry later");
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }

    public void changeAmount(double amount) {
        try {
          boolean res=  reentrantReadWriteLock.writeLock().tryLock(2000, TimeUnit.MILLISECONDS);
          if(res==true)
            this.amount = this.amount + amount;
        throw new WalletException("Please try again");
        } catch (InterruptedException e) {
            throw new WalletException("Please try again");
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    public void addTransaction(TransactionHistory transactionHistory) {

      
        try {
             boolean res= reentrantReadWriteLock.writeLock().tryLock(2000,TimeUnit.MILLISECONDS);
             if(res==true){
                  transactionHistories.add(transactionHistory);
             }
             throw new WalletException("Plase try again");
        }
        catch(Exception e){
            throw new WalletException("Plase try again");
        }
        finally {
            reentrantReadWriteLock.writeLock().unlock(); // Add this
        }

    }
}
