package com.example.springdatajpatransaction3.service;

import com.example.springdatajpatransaction3.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<Product> getList();
    void saveProductInfor();
}
