package com.vipac.authentication_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vipac.authentication_service.domains.User;

public interface UserRepository extends MongoRepository<User, String> {
	    
	User findByEmail(String email);
	User findByMatricola(String matricola);
	List<User> findByCorsoLaurea(String corsoLaurea);
	    
}
