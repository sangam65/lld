package CarRentalSystem.payment;

public class UPIStratgey implements PaymentStrategy{

    @Override
    public void processPayment() {
        System.out.println("payment successful");
    }

}
