package com.vipac.lectures_service.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vipac.lectures_service.domains.Lezione;
import com.vipac.lectures_service.repositories.LezioneRepository;

@Service
public class LezioneService {
	
	@Autowired
	private LezioneRepository lezioneRepository;
	
	public Lezione creaLezione(String nome, String professore) {
		return lezioneRepository.save(new Lezione(nome, professore));
	}
	
	public List<Lezione> getAll(){
		return lezioneRepository.findAll();
	}
	
	public Lezione getByNome(String nome) {
		return lezioneRepository.findByNome(nome);
	}
	
	public List<Lezione> getByProfessore(String professore) {
		return lezioneRepository.findByProfessore(professore);
	}
	
	public Lezione update(String nome, String professore, List<Date> date, List<String> iscritti) {
		Lezione l = lezioneRepository.findByNome(nome);
		l.setProfessore(professore);
		l.setOrari(date);
		l.setIscritti(iscritti);
		return lezioneRepository.save(l);
	}
	
	public void deleteAll() {
		lezioneRepository.deleteAll();
	}
	
	public void delete(String nome) {
		Lezione l = lezioneRepository.findByNome(nome);
		lezioneRepository.delete(l);
	}

	public List<Lezione> getAllForUser(String username) {
		ArrayList<Lezione> list = new ArrayList<Lezione>();
		for (Lezione l : getAll()) {
			if (l.getIscritti().contains(username)) {
				list.add(l);
			}
		}
		return list;
	}
}
