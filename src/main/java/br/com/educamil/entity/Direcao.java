package br.com.educamil.entity;

import java.util.List;

import javax.faces.model.DataModel;
import javax.persistence.*;


@Entity
@Table(name = "direcao")
@PrimaryKeyJoinColumn(name = "id_pessoa") 
public class Direcao extends Pessoa{
	
	private static final long serialVersionUID = 1L;
		
	@Column(nullable = false, length = 250)
    private String cargo;
	
	public Direcao(String cargo) {
		super();
		this.cargo = cargo;
	}
	
	public Direcao() {
		super();
		
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
