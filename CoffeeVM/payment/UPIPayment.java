package CoffeeVM.payment;

import java.util.Scanner;

public class UPIPayment implements PaymentStrategy{

    @SuppressWarnings("resource")
    @Override
    public boolean processPayment(int cost) {
        Scanner sc= new  Scanner(System.in);
        int x=sc.nextInt();
        if(x==cost){
            return true;
        }
        return false;
    }

}
