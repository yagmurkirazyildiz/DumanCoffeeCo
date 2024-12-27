package com.yagmur.repository;

import com.yagmur.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByNameIgnoreCase(String categoryName);
}
