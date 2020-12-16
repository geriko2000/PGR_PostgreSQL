package com.PostgreSQLRGR.repos;

import com.PostgreSQLRGR.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findById(Integer id);
    List<Order> findByClient(String client);
    List<Order> findByProduct(String product);
    List<Order> findByDate(LocalDate date);
}
