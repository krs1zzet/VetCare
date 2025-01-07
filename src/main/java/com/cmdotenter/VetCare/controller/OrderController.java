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
} 