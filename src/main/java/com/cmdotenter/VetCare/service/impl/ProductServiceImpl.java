package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BaseProductRequest;
import com.cmdotenter.VetCare.entity.Product;
import com.cmdotenter.VetCare.repository.ProductRepository;
import com.cmdotenter.VetCare.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Transactional
    @Override
    public void save(BaseProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Did not find product id - " + id));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        Product theProduct = product.orElseThrow(() -> new RuntimeException("Did not find product id - " + id));
        productRepository.deleteById(theProduct.getId());
    }
}
