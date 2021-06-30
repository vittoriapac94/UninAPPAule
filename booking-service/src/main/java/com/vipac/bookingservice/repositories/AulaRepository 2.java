package com.vipac.bookingservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vipac.bookingservice.domains.Aula;


@Repository
public interface AulaRepository extends MongoRepository<Aula, String> {
	
	public Aula findByNome(String nome);
	
}
