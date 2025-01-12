package com.cmdotenter.VetCare.repository;

import com.cmdotenter.VetCare.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
    @Query(value = " SELECT * from orders order by id desc limit 1",nativeQuery = true)
    Order findTopByUserIdOrderByIdDesc(Long userId);
}
