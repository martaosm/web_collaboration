package com.example.zad1.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "OrdersHistory")
public class OrderHistory {
    @Id
    @SequenceGenerator(name = "order_history_sequence", sequenceName = "order_history_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_history_sequence")
    private Long orderId;
    private String customerName;
    private String courierName;
    private String deliveryStatus;
    private String productNames;
    private BigDecimal totalPrice;

    public OrderHistory(String customerName, String courierName,
                        String deliveryStatus, String productNames,
                        BigDecimal totalPrice) {
        this.customerName = customerName;
        this.courierName = courierName;
        this.deliveryStatus = deliveryStatus;
        this.productNames = productNames;
        this.totalPrice = totalPrice;
    }

    public OrderHistory() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


}
