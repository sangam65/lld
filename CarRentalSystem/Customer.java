package CarRentalSystem;

public class Customer {
    private final String name;
    private final String licenseNumber;
    private final String phoneNumber;
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getName() {
        return name;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public Customer(String name, String licenseNumber,String phoneNumber) {
        this.name = name;
        this.phoneNumber=phoneNumber;
        this.licenseNumber = licenseNumber;
    }
}
