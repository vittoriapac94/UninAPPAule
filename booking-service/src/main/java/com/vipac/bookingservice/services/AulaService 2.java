package com.vipac.bookingservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vipac.bookingservice.domains.Aula;
import com.vipac.bookingservice.repositories.AulaRepository;


@Service
public class AulaService {
	
	@Autowired
	private AulaRepository aulaRepository;
	@Autowired
	
	public List<Aula> getAll(){
		return aulaRepository.findAll();
	}
	
	public Aula getByNome(String nome) {
		return aulaRepository.findByNome(nome);
	}
	
}
