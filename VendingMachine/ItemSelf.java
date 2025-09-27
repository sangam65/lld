package VendingMachine;

public class ItemSelf {
    private final int prodcode;
    private final Item item;
    private boolean soldStatus;
    public boolean isSoldStatus() {
        return soldStatus;
    }
    public void setSoldStatus() throws Exception {
       if(this.soldStatus==true){
        throw new Exception("product already sold");
       }
        this.soldStatus = true;
    }
    public ItemSelf(int prodcode, Item item) {
        this.prodcode = prodcode;
        this.item = item;
        this.soldStatus=false;
    }
    public int getProdcode() {
        return prodcode;
    }
    public Item getItem() {
        return item;
    }

}
