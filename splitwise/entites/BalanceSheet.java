package splitwise.entites;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {
    private final User owner;
    private final Map<User,Double>balances=new ConcurrentHashMap<>();


    public Map<User, Double> getBalances() {
        return balances;
    }


    public BalanceSheet(User owner) {
        this.owner=owner;

    }
    public synchronized void adjustBalance(User user,double balance){
        if(owner.equals(user)){
            return;
        }
        balances.merge(user, balance, Double::sum);
    }
    public void showBalances(){
        if(balances.isEmpty()){
            System.out.println("All settled up!");
            return;
        }
        double owedToMe=0.0;
        double owed=0.0;
        for(Map.Entry<User,Double>entry:balances.entrySet()){
            User otherUSer=entry.getKey();
            double amt=entry.getValue();
            if(amt>0.0){
                System.out.println(otherUSer.getName()+" owes  "+owner.getName()+ " "+amt);
                owedToMe+=amt;
            }
            else if(amt<0.0){
                 System.out.println(owner.getName()+" owes  "+otherUSer.getName()+ " "+Math.abs(amt));
                owed+=amt;
            }
        }
        System.out.println("Total owed to "+owner.getName()+owedToMe);
         System.out.println(owner.getName()+" owes "+owed);

    }
}
