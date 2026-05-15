package splitwise.entites;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import splitwise.strategy.SplitStrategy;

public class Expense {
    private final String id;
    private final String description;
    private final double amount;
    private final User paidBy;
    private final List<Split> splits;
    private final LocalDateTime timestamp;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    private Expense(ExpenseBuilder expenseBuilder){
        this.id=expenseBuilder.id;
        this.description=expenseBuilder.description;
        this.amount=expenseBuilder.amount;
       
        this.paidBy=expenseBuilder.paidBy;
        this.timestamp=LocalDateTime.now();
         this.splits=expenseBuilder.splitStrategy.calculateSplits(amount, paidBy, expenseBuilder.participants, expenseBuilder.splitValues);
    }

    public static class ExpenseBuilder {
        private String id;
        private String description;
        private double amount;
        private User paidBy;
        private SplitStrategy splitStrategy;

        private List<User> participants;
        private List<Double>splitValues;
        public ExpenseBuilder setSplitValues(List<Double>splitValues){
            this.splitValues=new ArrayList<>(splitValues);
            return this;
        }

        public ExpenseBuilder setParticipants(List<User> participants) {
            this.participants = participants;
            return this;
        }

        public ExpenseBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ExpenseBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ExpenseBuilder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public ExpenseBuilder setPaidBy(User paidBy) {
            this.paidBy = paidBy;
            return this;
        }

        

        public Expense build() {
            return new Expense(this);
        }
    }
}
