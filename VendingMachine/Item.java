package VendingMachine;

public enum Item {
    COKE(10),
    PEPSI(12),
    SODA(7),
    JUICE(15);

    public int price;
    Item(int price){
        this.price=price;
    }
    public static Item  getItem(int v){
        for(Item item:Item.values()){
            if(item.price==v){
                return item;
            }
        }
        return Item.COKE;
    }
}
