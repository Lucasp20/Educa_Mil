package br.com.educamil.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Professor extends Pessoa {

	private static final long serialVersionUID = 1L;

	/*
	 * @ManyToMany(mappedBy = "professor") private List<Disciplina> Disciplinas;
	 */

	public Professor() {
		super();
	}

	/*
	 * public List<Disciplina> getDisciplinas() { return Disciplinas; }
	 * 
	 * public void setDisciplinas(List<Disciplina> disciplinas) { Disciplinas =
	 * disciplinas; }
	 */



}
