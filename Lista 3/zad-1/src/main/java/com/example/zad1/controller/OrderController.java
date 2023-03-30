package com.example.zad1.controller;

import com.example.zad1.DeliveryStatus;
import com.example.zad1.model.Order;
import com.example.zad1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public void createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
    }

    @PutMapping("/updateStatus")
    public void updateStatus(@RequestParam("orderId") Long orderId,
                             @RequestParam("status") DeliveryStatus status) {
        orderService.updateDeliveryStatus(orderId, status);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllOrders(){
        orderService.deleteAllOrders();
    }
}
