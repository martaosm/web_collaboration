package com.example.zad1.repository;

import com.example.zad1.model.Order;
import com.example.zad1.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>, PagingAndSortingRepository<OrderHistory, Long> {
}
