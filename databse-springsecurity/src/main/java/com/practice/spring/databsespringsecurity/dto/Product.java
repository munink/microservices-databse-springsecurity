package com.practice.spring.databsespringsecurity.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private int productId;

    private String name;

    private int qty;

    private int price;
}
