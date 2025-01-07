package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BaseOrderRequest;
import com.cmdotenter.VetCare.entity.Order;
import com.cmdotenter.VetCare.repository.OrderRepository;
import com.cmdotenter.VetCare.service.OrderService;
import com.cmdotenter.VetCare.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }


    @Transactional
    @Override
    public void save(BaseOrderRequest request) {
        Order order = new Order();
        order.setDate(request.getDate());
        order.setUser(userService.findById(request.getUserId()));
        order.setTotalPrice(request.getTotalPrice());
        orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new RuntimeException("Did not find order id - " + id));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        Order theOrder = order.orElseThrow(() -> new RuntimeException("Did not find order id - " + id));
        orderRepository.deleteById(theOrder.getId());
    }
}
