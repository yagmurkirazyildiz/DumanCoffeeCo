package com.yagmur.repository;

import com.yagmur.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Products, String> {
    List<Products> findByCategoryId(String id);
}
