package digitalWalletService;


import java.util.HashMap;
import java.util.Map;

import digitalWalletService.entities.Account;
import digitalWalletService.entities.TransactionHistory;
import digitalWalletService.entities.User;
import digitalWalletService.enums.TransactionStatus;
import digitalWalletService.enums.TransactionType;
import digitalWalletService.exception.AlreadyPresentException;

public class DigitalWallet {
    private final Map<String,User>users=new HashMap<>();
    private final Map<String,Account>accounts=new HashMap<>();
    private static DigitalWallet digitalWallet;

    private DigitalWallet(){

    }
    public  synchronized static DigitalWallet getDigitalWalletInstance(){
        if(digitalWallet==null){
            digitalWallet=new DigitalWallet();
        }
        return digitalWallet;
    }
    
    public  synchronized boolean registerUser(String username) throws AlreadyPresentException{
        if(users.containsKey(username)){
            throw new AlreadyPresentException("user already added in system");

        }
        users.put(username,new User( username));    
        return true;
    }
    public synchronized void addAccount(User user,Account account) throws RuntimeException{
        if(account.getUser()!=user){
            throw new RuntimeException("Account not found");
        }
        if(!users.containsKey(user.getName())){
            throw new RuntimeException("user not present in system");
        }
        if(accounts.containsKey(account.getAccountId())){
             throw new AlreadyPresentException("Account already added in system");

        }
        user.addAccount(account);
        accounts.put(account.getAccountId(),account);

    }
    public synchronized void transferMoney(User user,Account from,Account to,double balance){
        try{
                if(from.getAmount()>=balance){
                        from.changeAmount(-balance);
                        to.changeAmount(balance);
                        from.addTransaction(new TransactionHistory(from, to, balance,TransactionStatus.COMPLETED,TransactionType.SENT));
                        to.addTransaction(new TransactionHistory(from, to, balance,TransactionStatus.COMPLETED,TransactionType.RECEIVED));
                }
                else{
                    // from.addTransaction(new TransactionHistory( from, to, balance, null, null));
                    throw new Exception("Money not sufficient in account");
                }

        }
        catch(Exception e){
            from.addTransaction(new TransactionHistory( from, to, balance,TransactionStatus.FAILED,TransactionType.SENT));
        }
        

    }




}
