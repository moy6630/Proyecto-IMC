package com.mg.login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mg.login.models.User;

public interface UserRepository extends MongoRepository<User, String>{
    User findByEmail(String email);

}
