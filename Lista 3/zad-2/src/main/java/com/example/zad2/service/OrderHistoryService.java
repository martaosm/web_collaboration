package com.example.zad2.service;

import com.example.zad2.model.OrderHistory;
import com.example.zad2.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

    public Long createOrderHistory(OrderHistory orderHistory){
        return orderHistoryRepository.save(orderHistory).getOrderId();
    }

    @Transactional
    public void updateStatus(Long id, String status){
        OrderHistory orderHistory = orderHistoryRepository.findById(id).get();
        orderHistory.setDeliveryStatus(status);
    }

    public void deleteById(Long id){
        orderHistoryRepository.deleteById(id);
    }

    public void deleteAll(){
        orderHistoryRepository.deleteAll();
    }

}
