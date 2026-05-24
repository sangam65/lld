package digitalWalletService.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import digitalWalletService.enums.TransactionStatus;
import digitalWalletService.enums.TransactionType;

public class TransactionHistory {
    private final String transcationId;
    private final Account from;
    private final Account to;
    private final double amount;
    private final LocalDateTime dateTime;
    private final TransactionType transactionType;
    public TransactionType getTransactionType() {
        return transactionType;
    }
    private final TransactionStatus transactionStatus;
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }
    public String getTranscationId() {
        return transcationId;
    }
    public Account getFrom() {
        return from;
    }
    public Account getTo() {
        return to;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public TransactionHistory(Account from, Account to, double amount, TransactionStatus transactionStatus,TransactionType transactionType) {
        this.transcationId =UUID.randomUUID().toString();
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.dateTime =LocalDateTime.now();
        this.transactionType=transactionType;
        this.transactionStatus =transactionStatus ;
    }
}
