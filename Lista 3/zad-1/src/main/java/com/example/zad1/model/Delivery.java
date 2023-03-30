package com.example.zad1.model;

import com.example.zad1.DeliveryStatus;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id
    @SequenceGenerator(name = "delivery_sequence", sequenceName = "delivery_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_sequence")
    private long id;
    private String courierName;
    private DeliveryStatus deliveryStatus;

    public Delivery(String courierName, DeliveryStatus deliveryStatus) {
        this.courierName = courierName;
        this.deliveryStatus = deliveryStatus;
    }

    public Delivery() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
