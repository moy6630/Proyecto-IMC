package com.mg.login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mg.login.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
