package com.example.zad1.service;

import com.example.zad1.DeliveryStatus;
import com.example.zad1.model.Order;
import com.example.zad1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderHistoryService orderHistoryService) {
        this.orderRepository = orderRepository;
        this.orderHistoryService = orderHistoryService;
    }

    public void createOrder(Order order) {
        orderRepository.save(order);
        orderHistoryService.createOrderHistoryWithOrder(order);
    }

    @Transactional
    public void updateDeliveryStatus(Long orderId, DeliveryStatus status) {
        Order order = orderRepository.getById(orderId);
        order.getDelivery().setDeliveryStatus(status);
        orderHistoryService.updateStatus(orderId, status.toString());
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }


}
