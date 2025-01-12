package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BaseOrderRequest;
import com.cmdotenter.VetCare.entity.Order;

import java.util.List;

public interface OrderService {
    void save(BaseOrderRequest request);
    Order findById(Long id);
    List<Order> findAll();
    void deleteById(Long id);
    void update(Long id, BaseOrderRequest request);
    List<Order> findAllByUserId(Long userId);
    Order findLatestOrder(Long userId);
}
