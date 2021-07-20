package br.com.senac.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String sexo;
	
	@Column(nullable = false)
	private String observacao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date nascimento;
	
	@Column(nullable = false)
	private double peso;
	
	@ManyToOne
	@JoinColumn(name = "id_dono")
	private Dono dono;
	
	@OneToMany(mappedBy = "animal")
	private List<Consulta> consultas;
	
	public Animal(String nome, String sexo, String observacao, Date nascimento, double peso) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.observacao = observacao;
		this.nascimento = nascimento;
		this.peso = peso;
	}
	
	public Animal() {
		super();
	
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Dono getDono() {
		return dono;
	}

	public void setDono(Dono dono) {
		this.dono = dono;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
