package CoffeeVM.coffee;

abstract class Decorator implements BaseCoffee{
    public BaseCoffee coffee;
    public  Decorator (BaseCoffee coffee){
        this.coffee=coffee;
    }
    //  @Override
    // public String getDescription() {
    //     return coffee.getDescription();
    // }

    // @Override
    // public int getPrice() {
    //     return coffee.getPrice();
    // }


    
}
