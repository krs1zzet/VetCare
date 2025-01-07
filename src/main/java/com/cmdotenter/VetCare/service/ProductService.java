package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BaseProductRequest;
import com.cmdotenter.VetCare.entity.Product;

import java.util.List;

public interface ProductService {
    void save(BaseProductRequest request);
    Product findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
