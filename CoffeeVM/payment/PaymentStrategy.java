package CoffeeVM.payment;

public interface PaymentStrategy {
    boolean processPayment(int cost);
}
