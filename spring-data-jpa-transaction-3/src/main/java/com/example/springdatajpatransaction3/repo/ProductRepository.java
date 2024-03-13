package com.example.springdatajpatransaction3.repo;

import com.example.springdatajpatransaction3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Transactional(propagation = Propagation.REQUIRED)
//    <S extends Product> S save(S entity);
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    <S extends Product> List<S> saveAll(Iterable<S> entities);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "update Product p set p.id = :newName where p.id = :productId")
    void updateById(@Param("productId") long productId, @Param("newName") String newName);
}
