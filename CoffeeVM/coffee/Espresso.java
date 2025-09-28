package CoffeeVM.coffee;

public class Espresso extends Decorator{
    public Espresso(BaseCoffee coffee){
        super(coffee);
    }

    @Override
    public int getPrice() {
        return coffee.getPrice()+13;
    }

    @Override
    public String getDescription(){
        return coffee.getDescription()+", espresso";
    }

}
