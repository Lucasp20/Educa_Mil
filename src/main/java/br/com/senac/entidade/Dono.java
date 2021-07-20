package br.com.senac.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "dono")
public class Dono implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, length = 14)
	private String cpf;
	
	@Column(nullable = false, length = 15)
	private String telefone;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@OneToMany(mappedBy = "dono")
	private List<Animal> animais;
	
	@OneToOne(mappedBy = "dono", cascade = CascadeType.ALL)
	private Endereco endereco;
	
	public Dono(String nome, String cpf, String telefone, String email) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
	}
		
	public Dono() {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		Dono other = (Dono) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
