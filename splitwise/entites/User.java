package splitwise.entites;

import java.util.UUID;

public class User {
    private final String name;
    private final String email;
    private final String id;
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }
    private BalanceSheet balanceSheet;
    public BalanceSheet getBalanceSheet() {
        return balanceSheet;
    }
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = UUID.randomUUID().toString();
        this.balanceSheet = new BalanceSheet(this);
    }
}
