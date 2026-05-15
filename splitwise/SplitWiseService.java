package splitwise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import splitwise.entites.Expense;
import splitwise.entites.Group;
import splitwise.entites.Split;
import splitwise.entites.User;

public class SplitWiseService {
    public static SplitWiseService splitWiseService;
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Group> groups = new HashMap<>();

    private SplitWiseService() {
    }

    public static synchronized SplitWiseService creaSplitWiseService() {
        if (splitWiseService == null) {
            splitWiseService = new SplitWiseService();
        }
        return splitWiseService;
    }

    public User addUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public Group addGroup(String name, List<User> members) {
        Group group = new Group(members, name);
        groups.put(group.getGroupId(), group);
        return group;
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public Group getGroup(String id) {
        return groups.get(id);
    }

    public synchronized void createExpense(Expense.ExpenseBuilder expenseBuilder){
        Expense expense=expenseBuilder.build();
        User paidBy=expense.getPaidBy();
        
        for(Split spllit:expense.getSplits()){
            User participant=spllit.getUser();
            if(participant.equals(paidBy))continue;
            paidBy.getBalanceSheet().adjustBalance(paidBy,spllit.getAmount());
            participant.getBalanceSheet().adjustBalance(participant, -spllit.getAmount());

        }
    }
    public synchronized void settleUp(String payerId,String payeeId,double amount){
         User payer = users.get(payerId);
        User payee = users.get(payeeId);
        System.out.println(payer.getName() + " is settling up " + amount + " with " + payee.getName());
        // Settlement is like a reverse expense. payer owes less to payee.

        payee.getBalanceSheet().adjustBalance(payer, -amount);
        payer.getBalanceSheet().adjustBalance(payee, amount);
    }
    public void showBalanceSheet(String userId) {
        User user = users.get(userId);
        user.getBalanceSheet().showBalances();
    }

}
