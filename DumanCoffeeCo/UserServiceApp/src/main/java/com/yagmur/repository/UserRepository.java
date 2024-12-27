package com.yagmur.repository;

import com.yagmur.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, String> {

}
