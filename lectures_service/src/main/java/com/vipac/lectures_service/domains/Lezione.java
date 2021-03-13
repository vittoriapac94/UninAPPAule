package com.vipac.lectures_service.domains;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lectures")
public class Lezione {
	@Id
	String id;
	
	@NotNull(message="Il nome non può essere nullo")
	String nome;
	
	String professore;
	List<Date> orari;
	List<String> iscritti;
	
	public String getNome() {
		return nome;
	}
	public void setName(String nome) {
		this.nome = nome;
	}
	public String getProfessore() {
		return professore;
	}
	public void setProfessore(String professore) {
		this.professore = professore;
	}
	public List<Date> getOrari() {
		return orari;
	}
	public void setOrari(List<Date> orari) {
		this.orari = orari;
	}
	public List<String> getIscritti() {
		return iscritti;
	}
	public void setIscritti(List<String> iscritti) {
		this.iscritti = iscritti;
	}
	
	
	public Lezione(String nome, String professore) {
		super();
		this.nome = nome;
		this.professore = professore;
	}
	
	
	
}