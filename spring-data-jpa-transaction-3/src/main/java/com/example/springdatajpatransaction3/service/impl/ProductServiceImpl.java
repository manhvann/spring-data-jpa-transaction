package com.example.springdatajpatransaction3.service.impl;

import com.example.springdatajpatransaction3.entity.Product;
import com.example.springdatajpatransaction3.repo.ProductRepository;
import com.example.springdatajpatransaction3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getList() {
       return productRepository.findAll();
    }


    @Transactional(propagation = Propagation.REQUIRED) //connection start
    @Override
    public void saveProductInfor()  {
        List<Product> productList = new ArrayList<>();
        for(int i = 1;i<=1;i++){
            String name = "Test Product " + i;
            Product product = new Product(i,name);
            productList.add(product);
//            productRepository.save(product);
//            if(i == 1){
//
//            }
        }
        productRepository.saveAll(productList);
        productRepository.updateById(1,"Product Update");
    }
    //commit
    //close
}




