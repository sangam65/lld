package digitalWalletService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import digitalWalletService.entities.Account;
import digitalWalletService.entities.TransactionHistory;
import digitalWalletService.entities.TransactionResult;
import digitalWalletService.entities.User;
import digitalWalletService.enums.TransactionStatus;
import digitalWalletService.enums.TransactionType;
import digitalWalletService.exception.WalletException;

public class DigitalWallet {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Account> accounts = new HashMap<>();
    private static DigitalWallet digitalWallet;

    private DigitalWallet() {

    }

    public synchronized static DigitalWallet getDigitalWalletInstance() {
        if (digitalWallet == null) {
            digitalWallet = new DigitalWallet();
        }
        return digitalWallet;
    }

    public synchronized boolean registerUser(String username) throws WalletException {
        if (users.containsKey(username)) {
            throw new WalletException("user already added in system");

        }
        users.put(username, new User(username));
        return true;
    }

    public synchronized boolean addAccount(User user, Account account) throws RuntimeException {
        if (account.getUser() != user) {
            throw new RuntimeException("Account not found");
        }
        if (!users.containsKey(user.getName())) {
            throw new RuntimeException("user not present in system");
        }
        if (accounts.containsKey(account.getAccountId())) {
            throw new WalletException("Account already added in system");

        }
        user.addAccount(account);
        accounts.put(account.getAccountId(), account);
        return true;

    }

    public synchronized TransactionResult transferMoney(User user, Account from, Account to, double balance)
            throws WalletException, InterruptedException {
        try {
            if (balance < 0) {
                throw new WalletException("balance can't be negative");
            }
            Account first = from.getAccountId().compareTo(to.getAccountId()) < 0 ? from : to;
            Account second = (first == from) ? to : from;

            try {
                boolean lockFirst = first.getReentrantReadWriteLock().writeLock().tryLock(2000, TimeUnit.MILLISECONDS);
                if (lockFirst == false)
                    throw new WalletException("lock failed to acquire");
                boolean lockSecond = second.getReentrantReadWriteLock().writeLock().tryLock(2000,
                        TimeUnit.MILLISECONDS);
                if (!lockSecond)
                    throw new WalletException("lock failed to acquire");
                try {
                    if (from.getAmount() >= balance) {

                        from.changeAmount(-balance);
                        to.changeAmount(balance);
                        TransactionHistory transactionHistory = new TransactionHistory(from, to, balance,
                                TransactionStatus.COMPLETED, TransactionType.SENT);
                        from.addTransaction(transactionHistory);
                        to.addTransaction(new TransactionHistory(from, to, balance, TransactionStatus.COMPLETED,
                                TransactionType.RECEIVED));

                        return new TransactionResult(transactionHistory.getTranscationId(), true, null, null);
                    } else {
                        // from.addTransaction(new TransactionHistory( from, to, balance, null, null));
                        throw new WalletException("Money not sufficient in account");
                    }
                } finally {
                    second.getReentrantReadWriteLock().writeLock().unlock();
                }

            } finally {
                first.getReentrantReadWriteLock().writeLock().unlock();
            }

        } catch (WalletException e) {
            TransactionHistory transactionHistory = new TransactionHistory(from, to, balance, TransactionStatus.FAILED,
                    TransactionType.SENT);
            from.addTransaction(transactionHistory);
            TransactionResult transactionResult = new TransactionResult(transactionHistory.getTranscationId(), false,
                    e.getMessage(), e);
            return transactionResult;
        } catch (InterruptedException e) {
            throw e;
        }

    }

    public List<TransactionHistory> getTransactionHistoryForUserAccount(User user, Account account) {
        if (account.getUser() != user) {
            throw new RuntimeException("can't see other user's account history");
        }
        if (!users.containsKey(user.getUserId()) || !accounts.containsKey(account.getAccountId())) {
            throw new RuntimeException("Invalid user or account");
        }
        return account.getTransactionHistory();
    }

}
