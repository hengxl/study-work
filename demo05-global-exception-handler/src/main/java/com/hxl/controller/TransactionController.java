package com.hxl.controller;

import com.hxl.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;
    /**
     * 添加商品
     */
    @PostMapping("/add")
    public void addProduct() {
        Integer userId = 2;
        Integer number = 100;
        Integer productId = 1;

        service.add(userId, productId, number);
    }
}
