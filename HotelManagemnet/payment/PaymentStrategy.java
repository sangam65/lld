package HotelManagemnet.payment;

public interface PaymentStrategy {
    double processPayment(double rent);
    double refundPayment(double rent);
}
