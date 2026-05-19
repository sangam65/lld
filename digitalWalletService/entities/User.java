package digitalWalletService.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userId;
    private final String name;
      private List<Account>accounts;
    public void setAccounts(Account account) {
        this.accounts .add(account);
    }
    public String getUserId() {
        return userId;
    }
      public String getName() {
          return name;
      }
      public List<Account> getAccounts() {
          return accounts;
      }
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.accounts=new ArrayList<>();
    }
    
  
}
