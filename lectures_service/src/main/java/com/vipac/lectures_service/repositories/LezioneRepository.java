package com.vipac.lectures_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.vipac.lectures_service.domains.Lezione;

@Repository
public interface LezioneRepository extends MongoRepository<Lezione, String> {
	
	public Lezione findByNome(String nome);
	public List<Lezione> findByProfessore(String professore);

}
