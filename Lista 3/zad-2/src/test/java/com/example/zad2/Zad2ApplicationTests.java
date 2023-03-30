package com.example.zad2;

import com.example.zad2.model.OrderHistory;
import com.example.zad2.repository.OrderHistoryRepository;
import com.example.zad2.service.OrderHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class Zad2ApplicationTests {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Test
    public void testCreateOrderHistory() {
        OrderHistory orderHistory = new OrderHistory("Tadeusz",
                "Janusz", "CREATED", "kalosze, wędka",
                new BigDecimal(1200.00));
        Long orderId = orderHistoryService.createOrderHistory(orderHistory);
        OrderHistory order1 = orderHistoryService.getById(orderId);
        Assertions.assertEquals(orderHistory.getOrderId(), order1.getOrderId());
        Assertions.assertEquals(orderHistory.getCourierName(), order1.getCourierName());
        Assertions.assertEquals(orderHistory.getCustomerName(), order1.getCustomerName());
        Assertions.assertEquals(orderHistory.getDeliveryStatus(), order1.getDeliveryStatus());
        Assertions.assertEquals(orderHistory.getProductNames(), order1.getProductNames());
    }

    @Test
    public void testUpdateOrderHistory() {
        OrderHistory orderHistory = new OrderHistory("Tadeusz",
                "Janusz", "CREATED", "kalosze, wędka",
                new BigDecimal(1200.00));
        Long orderId = orderHistoryService.createOrderHistory(orderHistory);
        orderHistoryService.updateStatus(orderId, "PICKED_UP");
        OrderHistory order1 = orderHistoryService.getById(orderId);
        Assertions.assertEquals(orderHistory.getOrderId(), order1.getOrderId());
        Assertions.assertEquals(orderHistory.getCourierName(), order1.getCourierName());
        Assertions.assertEquals(orderHistory.getCustomerName(), order1.getCustomerName());
        Assertions.assertNotEquals(orderHistory.getDeliveryStatus(), order1.getDeliveryStatus());
        Assertions.assertEquals(orderHistory.getProductNames(), order1.getProductNames());
    }

}
