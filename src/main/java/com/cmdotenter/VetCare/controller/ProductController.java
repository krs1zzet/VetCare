package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BaseProductRequest;
import com.cmdotenter.VetCare.entity.Product;
import com.cmdotenter.VetCare.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productService.findAll();
        log.info("Find all products");
        return ResponseEntity.ok(products);
    }

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@RequestBody BaseProductRequest request){
        productService.save(request);
        log.info("Product saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        log.info("Product deleted");
        return ResponseEntity.ok().build();
    }
} 