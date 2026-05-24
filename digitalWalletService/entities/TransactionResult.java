package digitalWalletService.entities;

import digitalWalletService.exception.WalletException;

public class TransactionResult {
    private final String transactionId;
    private final boolean success;
    private final String errorMessage;
    private final WalletException exception;
    public String getTransactionId() {
        return transactionId;
    }
    public boolean isSuccess() {
        return success;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public WalletException getException() {
        return exception;
    }
    public TransactionResult(String transactionId, boolean success, String errorMessage, WalletException exception) {
        this.transactionId = transactionId;
        this.success = success;
        this.errorMessage = errorMessage;
        this.exception = exception;
    }
}
