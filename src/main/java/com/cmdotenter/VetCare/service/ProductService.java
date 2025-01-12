package com.cmdotenter.VetCare.service;

import java.util.List;

import com.cmdotenter.VetCare.dto.request.BaseProductRequest;
import com.cmdotenter.VetCare.entity.Product;

public interface ProductService {
    void save(BaseProductRequest request);
    Product findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    void update(Long id, BaseProductRequest request);
}
