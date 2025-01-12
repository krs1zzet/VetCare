package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BaseOrderDetailsRequest;
import com.cmdotenter.VetCare.entity.OrderDetails;
import com.cmdotenter.VetCare.service.OrderDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/order-details")
    public ResponseEntity<List<OrderDetails>> findAll(){
        List<OrderDetails> orderDetails = orderDetailsService.findAll();
        log.info("Find all order details");
        return ResponseEntity.ok(orderDetails);
    }

    @PostMapping("/order-details")
    public ResponseEntity<Void> createOrderDetails(@RequestBody BaseOrderDetailsRequest request){
        orderDetailsService.save(request);
        log.info("Order details saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/order-details/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long id){
        orderDetailsService.deleteById(id);
        log.info("Order details deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/order-details/{id}")
    public ResponseEntity<Void> updateOrderDetails(@PathVariable Long id, @RequestBody BaseOrderDetailsRequest request){
        orderDetailsService.update(id, request);
        log.info("Order details updated");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/order-details/order/{id}")
    public ResponseEntity<List<OrderDetails>> findByOrderId(@PathVariable Long id){
        List<OrderDetails> orderDetails = orderDetailsService.findAllByOrderId(id);
        log.info("Find order details by id");
        return ResponseEntity.ok(orderDetails);
    }
    @DeleteMapping("/order-details")
    public ResponseEntity<Void> deleteAll(){
        orderDetailsService.deleteAll();
        log.info("All order details deleted");
        return ResponseEntity.ok().build();
    }
} 