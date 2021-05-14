package com.mg.login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mg.login.models.IMC;

public interface IMCRepository extends MongoRepository<IMC, String> {
//IMC findByName(String name);
}
