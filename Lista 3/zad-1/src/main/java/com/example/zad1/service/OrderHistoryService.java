package com.example.zad1.service;

import com.example.zad1.model.Order;
import com.example.zad1.model.OrderHistory;
import com.example.zad1.model.OrderItem;
import com.example.zad1.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public OrderHistoryService(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    public OrderHistory getById(Long id) {
        return orderHistoryRepository.findById(id).get();
    }

    public List<OrderHistory> getAll() {
        return orderHistoryRepository.findAll();
    }

    public void createOrderHistory(OrderHistory orderHistory){
        orderHistoryRepository.save(orderHistory);
    }

    public void createOrderHistoryWithOrder(Order order){
        OrderHistory orderHistory = new OrderHistory(order.getCustomer_name(), order.getDelivery().getCourierName(),
                order.getDelivery().getDeliveryStatus().toString(), convertListToString(order.getItems()),
                calculateTotalPrice(order.getItems()));
        orderHistoryRepository.save(orderHistory);
    }

    public String convertListToString(List<OrderItem> orderItems){
        StringBuilder products = new StringBuilder();
        for(OrderItem o : orderItems){
            products.append(o.getProduct().getName() + ", ");
        }
        return String.valueOf(products);
    }

    public BigDecimal calculateTotalPrice(List<OrderItem> orderItems){
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for(OrderItem o : orderItems){
            totalPrice = totalPrice.add(o.getProduct().getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
        }
        return totalPrice;
    }

    @Transactional
    public void updateStatus(Long id, String status){
        OrderHistory orderHistory = orderHistoryRepository.findById(id).get();
        orderHistory.setDeliveryStatus(status);
    }

    public List<OrderHistory> getAllOrderHistoryPage(Integer pageNo, Integer pageSize){
        Pageable paging = PageRequest.of(pageNo,pageSize);
        Page<OrderHistory> pagedOrderHistory = orderHistoryRepository.findAll(paging);
        if(pagedOrderHistory.hasContent()) {
            return pagedOrderHistory.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public void deleteById(Long id){
        orderHistoryRepository.deleteById(id);
    }

    public void deleteAll(){
        orderHistoryRepository.deleteAll();
    }
}
