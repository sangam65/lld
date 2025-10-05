package HotelManagemnet.entities;

import java.util.UUID;

public class Customer {
    private final String customerId;

    public Customer() {
        this.customerId =UUID.randomUUID().toString();
    }

    public String getCustomerId() {
        return customerId;
    }

}
