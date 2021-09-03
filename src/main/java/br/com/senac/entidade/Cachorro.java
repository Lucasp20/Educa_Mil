package br.com.senac.entidade;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "cachorro")
@PrimaryKeyJoinColumn(name = "id_animal") 
public class Cachorro extends Animal{

	private boolean treinado;

	public Cachorro() {
		super();
		
	}

	public Cachorro(String nome, String sexo, String observacao, Date nascimento, double peso, boolean treinado) {
		super(nome, sexo, observacao, nascimento, peso);
		this.treinado = treinado;
	}
	
	public boolean isTreinado() {
		return treinado;
	}

	public void setTreinado(boolean treinado) {
		this.treinado = treinado;
	}
	
}
