package VendingMachine;

public class Inventory {
    ItemSelf []itemSelfs;
    public Inventory(int sz){
        this.itemSelfs=new ItemSelf[sz];
        initializeItems();
    }
    private void initializeItems(){
        int startCode=103,length=itemSelfs.length;
        for(int i=0;i<length;++i){
            int x=startCode%4;
            x=x+1;
            ItemSelf itemSelf=new ItemSelf(startCode, Item.getItem(x));
            itemSelfs[i]=itemSelf;
        }

    }
    
}
