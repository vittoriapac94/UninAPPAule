package com.vipac.authentication_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vipac.authentication_service.domains.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    
    Role findByRole(String role);
    
}