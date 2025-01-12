package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BaseOrderRequest;
import com.cmdotenter.VetCare.entity.Order;
import com.cmdotenter.VetCare.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> findAll(){
        List<Order> orders = orderService.findAll();
        log.info("Find all orders");
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/orders")
    public ResponseEntity<Void> createOrder(@RequestBody BaseOrderRequest request){
        orderService.save(request);
        log.info("Order saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteById(id);
        log.info("Order deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long id, @RequestBody BaseOrderRequest request){
        orderService.update(id, request);
        log.info("Order updated");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<List<Order>> findAllByUserId(@PathVariable Long userId){
        List<Order> orders = orderService.findAllByUserId(userId);
        log.info("Find all orders by user id");
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/latest/{userId}")
    public ResponseEntity<Order> findLatestOrder(@PathVariable Long userId){
        Order order = orderService.findLatestOrder(userId);
        log.info("Find latest order by user id");
        return ResponseEntity.ok(order);
    }
} 