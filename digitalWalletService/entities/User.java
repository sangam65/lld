package digitalWalletService.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class User {
    private final String userId;
    private final String name;
    private HashMap<String,Account> accounts;
    

    public void addAccount(Account account) {
        if(account.getUser()!=this)return;
        // if(accounts.containsKey(account.getAccountId()))return;
        accounts.putIfAbsent(account.getAccountId(),account);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public User( String name) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.accounts = new HashMap<>();
    }

}
