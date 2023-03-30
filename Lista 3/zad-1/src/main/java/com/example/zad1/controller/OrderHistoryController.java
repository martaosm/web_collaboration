package com.example.zad1.controller;

import com.example.zad1.model.OrderHistory;
import com.example.zad1.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/orderHistory")
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

    @GetMapping("/getAllPaged")
    public ResponseEntity<List<OrderHistory>> getAllPaged(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "3") Integer pageSize) {
        List<OrderHistory> orderHistories = orderHistoryService.getAllOrderHistoryPage(pageNo, pageSize);
        return new ResponseEntity<List<OrderHistory>>(orderHistories, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/createOrderHistory")
    public void createOrderHistory(@RequestBody OrderHistory orderHistory) {
        orderHistoryService.createOrderHistory(orderHistory);
    }

    @PutMapping("/updateStatus")
    public void updateStatus(@RequestParam("id") Long id,
                             @RequestParam("status") String status) {
        orderHistoryService.updateStatus(id, status);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam("id") Long id) {
        orderHistoryService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        orderHistoryService.deleteAll();
    }
}
