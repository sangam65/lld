package splitwise.strategy;

import java.util.List;

import splitwise.entites.Split;
import splitwise.entites.User;

public interface SplitStrategy {
    List<Split> calculateSplits(double totalAmt,User paidBy,List<User>participants,List<Double>splitValues);
}
