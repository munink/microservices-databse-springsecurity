package com.practice.spring.databsespringsecurity.service;

import com.practice.spring.databsespringsecurity.dto.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> productList = null;

    @PostConstruct
    public void loadProductsFromDB() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("Product-" + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000))
                        .build()
                ).collect(Collectors.toList());
    }

    public List<Product> getProducts() {

        return productList;
    }
    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Product " + id + " not found!"));
    }
}
