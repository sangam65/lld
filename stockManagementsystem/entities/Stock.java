package stockManagementsystem.entities;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import stockManagementsystem.exception.StockManagementException;

public class Stock {
    private final ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private final String stockId;
    private final int quantity;
    public String getStockId() {
        return stockId;
    }
    public int getQuantity() {
        return quantity;
    }
    private int bought;
    private BigDecimal price;
    public Stock(int quantity, BigDecimal price) {
        this.stockId =UUID.randomUUID().toString();
        this.quantity = quantity;
        this.bought = 0;
        this.price = price;
    }
    public int getBought() {
        return bought;
    }
    public BigDecimal buyStock(int bought,BigDecimal price,BigDecimal balance) throws InterruptedException{
        if(!reentrantReadWriteLock.writeLock().tryLock(1000,TimeUnit.MILLISECONDS)){
            throw new StockManagementException("Try again later");
        }
        int available=this.quantity-this.bought;
        if(available<bought){
            throw new StockManagementException(available+" number of stocks are left to bought");
        }
        if(this.price.compareTo(price)<0){
            // add to notify user when price will go down
            throw new StockManagementException("Stock price yet to go won");
        }
        BigDecimal totalCost=this.price.multiply(BigDecimal.valueOf(bought));
        if(totalCost.compareTo(balance)>0){
            throw new StockManagementException("Balance not sufficient");
        }
        this.bought+=bought;
        return totalCost;

        
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
}
