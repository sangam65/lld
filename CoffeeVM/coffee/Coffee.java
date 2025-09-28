package CoffeeVM.coffee;

public class Coffee implements BaseCoffee{
    private final int price;
    private final String description;
    public Coffee(int price, String description) {
        this.price = price;
        this.description = description;
    }
    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public String getDescription() {
        return description;
    }

}
