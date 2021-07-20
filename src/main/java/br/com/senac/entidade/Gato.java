package br.com.senac.entidade;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "gato")
@PrimaryKeyJoinColumn(name = "id_animal")
public class Gato extends Animal{

	private boolean fiv;
	
	private boolean felv;
	

	public Gato(String nome, String sexo, String observacao, Date nascimento, double peso, boolean fiv, boolean felv) {
		super(nome, sexo, observacao, nascimento, peso);
		this.fiv = fiv;
		this.felv = felv;
	}
	
	public Gato() {
		super();
		
	}

	public boolean isFiv() {
		return fiv;
	}

	public void setFiv(boolean fiv) {
		this.fiv = fiv;
	}

	public boolean isFelv() {
		return felv;
	}

	public void setFelv(boolean felv) {
		this.felv = felv;
	}
	
	
}
