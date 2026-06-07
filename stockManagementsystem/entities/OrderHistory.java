package stockManagementsystem.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import stockManagementsystem.enums.OrderStatus;

public class OrderHistory {
    private final Stock stock;
    private final  OrderStatus orderStatus;
    private final LocalDateTime time;
    
    public LocalDateTime getTime() {
        return time;
    }
    private final String orderStatusId;
    private final BigDecimal price;
    public Stock getStock() {
        return stock;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public String getOrderStatusId() {
        return orderStatusId;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public OrderHistory(Stock stock, OrderStatus orderStatus,  BigDecimal price) {
        this.stock = stock;
        this.orderStatus = orderStatus;
        this.orderStatusId = UUID.randomUUID().toString();
        this.price = price;
        this.time=LocalDateTime.now();
    }

}
