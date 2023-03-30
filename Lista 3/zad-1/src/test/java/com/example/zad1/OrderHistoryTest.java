package com.example.zad1;

import com.example.zad1.model.OrderHistory;
import com.example.zad1.repository.OrderHistoryRepository;
import com.example.zad1.service.OrderHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderHistoryTest {

    @Mock
    OrderHistoryRepository orderHistoryRepository;
    private OrderHistoryService orderHistoryService;

    @BeforeEach
    void init() {
        orderHistoryService = new OrderHistoryService(orderHistoryRepository);
    }

    @Test
    public void testCreateOrderHistory() {
        OrderHistory orderHistory = new OrderHistory("Tadeusz",
                "Janusz", "CREATED", "kalosze, wędka",
                new BigDecimal(1200.00));
        orderHistoryService.createOrderHistory(orderHistory);
        ArgumentCaptor<OrderHistory> argumentCaptor =
                ArgumentCaptor.forClass(OrderHistory.class);
        Mockito.verify(orderHistoryRepository)
                .save(argumentCaptor.capture());
        OrderHistory order1 = argumentCaptor.getValue();
        Assertions.assertEquals(orderHistory, order1);
    }

    @Test
    public void testUpdateOrderHistory() {
        Long id = 1L;
        String status = "DELIVERED";
        OrderHistory mockOrderHistory = Mockito.mock(OrderHistory.class);
        Mockito.when(orderHistoryRepository.findById(id)).thenReturn(Optional.of(mockOrderHistory));
        orderHistoryService.updateStatus(id, status);
        Mockito.verify(orderHistoryRepository).findById(id);
        Mockito.verify(mockOrderHistory).setDeliveryStatus(status);
    }

    @Test
    public void testGetAllOrderHistory(){
        orderHistoryService.getAll();
        Mockito.verify(orderHistoryRepository).findAll();
    }

    @Test
    public void testGetOrderHistoryById(){
        OrderHistory orderHistoryMock = Mockito.mock(OrderHistory.class);
        Mockito.when(orderHistoryRepository.findById(1L)).thenReturn(Optional.of(orderHistoryMock));
        orderHistoryService.getById(1L);
        Mockito.verify(orderHistoryRepository).findById(1L);
    }

}
