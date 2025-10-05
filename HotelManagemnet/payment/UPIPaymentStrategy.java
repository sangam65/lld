package HotelManagemnet.payment;

public class UPIPaymentStrategy implements PaymentStrategy{

    @Override
    public double processPayment(double rent) {
        System.out.println("Processing payment of "+rent);
        return rent;
    }

    @Override
    public double refundPayment(double refund) {
       System.out.println("Processing refund of "+refund);
        return refund;
    }

}
