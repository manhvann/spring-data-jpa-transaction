package com.example.springdatajpatransaction3.controller;

import com.example.springdatajpatransaction3.entity.Product;
import com.example.springdatajpatransaction3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public void saveAll()  {
        productService.saveProductInfor();
    }

    @GetMapping("/product")
    public List<Product> getList()  {
        return productService.getList();
    }

}
