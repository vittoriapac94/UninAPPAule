package com.vipac.bookingservice.domains;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "aule")
public class Aula {
	
	@Id
	String id;
	@NotNull
	String nome;
	int righe;
	int colonne;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getRighe() {
		return righe;
	}
	public void setRighe(int righe) {
		this.righe = righe;
	}
	public int getColonne() {
		return colonne;
	}
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	
	

}
