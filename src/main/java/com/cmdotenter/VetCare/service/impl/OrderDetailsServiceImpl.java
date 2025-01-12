package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BaseOrderDetailsRequest;
import com.cmdotenter.VetCare.entity.OrderDetails;
import com.cmdotenter.VetCare.repository.OrderDetailsRepository;
import com.cmdotenter.VetCare.service.OrderDetailsService;
import com.cmdotenter.VetCare.service.OrderService;
import com.cmdotenter.VetCare.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderService orderService;
    private final ProductService productService;


    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository, OrderService orderService, ProductService productService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderService = orderService;
        this.productService = productService;
    }
    @Transactional
    @Override
    public void save(BaseOrderDetailsRequest request) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrder(orderService.findById(request.getOrderId()));
        orderDetails.setProduct(productService.findById(request.getProductId()));
        orderDetails.setPrice(request.getPrice());
        orderDetails.setQuantity(request.getQuantity());
        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public OrderDetails findById(Long id) {
        Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(id);
        return orderDetails.orElseThrow(() -> new RuntimeException("Did not find orderDetails id - " + id));
    }

    @Override
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(id);
        OrderDetails theOrderDetails = orderDetails.orElseThrow(() -> new RuntimeException("Did not find orderDetails id - " + id));
        orderDetailsRepository.deleteById(theOrderDetails.getId());

    }

    @Transactional
    @Override
    public void update(Long id, BaseOrderDetailsRequest request) {
        Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(id);
        OrderDetails theOrderDetails = orderDetails.orElseThrow(() -> new RuntimeException("Did not find orderDetails id - " + id));
        theOrderDetails.setOrder(orderService.findById(request.getOrderId()));
        theOrderDetails.setProduct(productService.findById(request.getProductId()));
        theOrderDetails.setPrice(request.getPrice());
        theOrderDetails.setQuantity(request.getQuantity());
        orderDetailsRepository.save(theOrderDetails);
    }

    @Override
    public List<OrderDetails> findAllByOrderId(Long orderId) {
        return orderDetailsRepository.findAllByOrderId(orderId);
    }

    @Override
    public void deleteAll() {
        orderDetailsRepository.deleteAll();

    }




}
