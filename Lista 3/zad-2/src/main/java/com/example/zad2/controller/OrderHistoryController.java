package com.example.zad2.controller;

import com.example.zad2.model.OrderHistory;
import com.example.zad2.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @GetMapping("/getById")
    public OrderHistory getById(Long id) {
        return orderHistoryService.getById(id);
    }

    @GetMapping("/getAll")
    public List<OrderHistory> getAll() {
        return orderHistoryService.getAll();
    }

    @PostMapping("/createOrderHistory")
    public Long createOrderHistory(@RequestBody OrderHistory orderHistory) {
        return orderHistoryService.createOrderHistory(orderHistory);
    }

    @PutMapping("/updateStatus")
    public void updateStatus(@RequestParam("id") Long id,
                             @RequestParam("status") String status) {
        orderHistoryService.updateStatus(id, status);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam("id") Long id){
        orderHistoryService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        orderHistoryService.deleteAll();
    }
}
