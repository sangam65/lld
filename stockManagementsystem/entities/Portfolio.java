package stockManagementsystem.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import stockManagementsystem.enums.OrderStatus;
import stockManagementsystem.exception.StockManagementException;

public class Portfolio {
    private final ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock(true);
    private final String portfolioId;
    private final Set<Stock> stockList=new HashSet<>();
    private BigDecimal amount=BigDecimal.ZERO;
    private final User user;
    private final List<OrderHistory>orderHistories=new ArrayList<>();
    private final Map<Stock,BigDecimal>boughtPrice=new HashMap<>();
    private final Map<Stock,Integer>stockQuantity=new HashMap<>();
    public String getPortfolioId() {
        return portfolioId;
    }
    public Set<Stock> getStockList() {
        return stockList;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public User getUser() {
        return user;
    }
    public List<OrderHistory> getOrderHistories() {
        return orderHistories;
    }
    
    public Portfolio(User user) {
        this.user = user;
        this.portfolioId=UUID.randomUUID().toString();
        
    }
    public  OrderHistory buyStock(Stock stock,int quantity,BigDecimal price)throws InterruptedException{
        if(!readWriteLock.writeLock().tryLock(1000,TimeUnit.MILLISECONDS)){
            throw new StockManagementException("Try again later");
        }
        try{
         BigDecimal totalStockCost=  stock.buyStock(quantity,price,user.getBalance());
            OrderHistory orderHistory=new OrderHistory(stock, OrderStatus.COMPLETED, price);
            orderHistories.add(orderHistory);
            int stockPresent=0;
            BigDecimal newAvgPrice = price;
            if(stockList.contains(stock)){
                stockPresent = stockQuantity.getOrDefault(stock, 0);
                BigDecimal prevPrice = boughtPrice.getOrDefault(stock, BigDecimal.ZERO);
                BigDecimal totalPrev = prevPrice.multiply(BigDecimal.valueOf(stockPresent));
                BigDecimal totalNew = price.multiply(BigDecimal.valueOf(quantity));
                int combinedQty = stockPresent + quantity;
                if (combinedQty > 0) {
                    newAvgPrice = totalPrev.add(totalNew)
                            .divide(BigDecimal.valueOf(combinedQty), 8, RoundingMode.HALF_UP);
                }
            }
            stockPresent += quantity;
            stockList.add(stock);
            stockQuantity.put(stock, stockPresent);
            boughtPrice.put(stock, newAvgPrice);
            user.deductBalance(totalStockCost);

        } catch(Exception e){
            throw new StockManagementException(e.getMessage());
        }
        finally{
            readWriteLock.writeLock().unlock();
        }
        return orderHistories.get(orderHistories.size()-1);

    
    }

}
