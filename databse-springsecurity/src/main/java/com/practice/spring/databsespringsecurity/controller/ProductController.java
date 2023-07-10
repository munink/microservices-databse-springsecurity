package com.practice.spring.databsespringsecurity.controller;

import com.practice.spring.databsespringsecurity.dto.Product;
import com.practice.spring.databsespringsecurity.entity.UserInfo;
import com.practice.spring.databsespringsecurity.service.ProductService;
import com.practice.spring.databsespringsecurity.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/welcome")
    public String welcome() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setName("munin");
//        userInfo.setEmail("muninxx@gmail.com");
//        userInfo.setPassword("password");
//        userInfo.setRoles("ROLE_ADMIN,ROLE_USER");
//        userInfoService.addUser(userInfo);

        return "Welcome, this endpoint is not secure!";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }


    @PostMapping("/add")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }
}
