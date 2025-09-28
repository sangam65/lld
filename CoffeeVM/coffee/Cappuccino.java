package CoffeeVM.coffee;

public class Cappuccino extends Decorator{
    
    public Cappuccino(BaseCoffee coffee) {
        super(coffee);
        //TODO Auto-generated constructor stub
    }

    @Override
    public int getPrice() {
        // TODO Auto-generated method stub
        return coffee.getPrice()+20;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription()+", cappuccino";
    }

}
