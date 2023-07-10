package com.practice.spring.databsespringsecurity.controller;

import com.practice.spring.databsespringsecurity.entity.UserInfo;
import com.practice.spring.databsespringsecurity.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/add")
    // @PreAuthorize(value = "ROLE_ADMIN")
    public String addUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @PostMapping("/postAdd")
    public String addUserPost() {
        return "POST addTest...";
    }

    @GetMapping("/getAdd")
    public String addUserGet() {
        return "GET getAdd...";
    }
}
