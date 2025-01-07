package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BaseOrderDetailsRequest;
import com.cmdotenter.VetCare.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    void save(BaseOrderDetailsRequest request);
    OrderDetails findById(Long id);
    List<OrderDetails> findAll();
    void deleteById(Long id);


}
