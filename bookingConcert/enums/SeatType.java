package bookingConcert.enums;

public enum SeatType {
    STANDARD(400),
    VIP(700),
    ECONOMY(250);
    private final int price;
    public int getPrice() {
        return price;
    }
    private SeatType(int price){
        this.price=price;
    }
}
