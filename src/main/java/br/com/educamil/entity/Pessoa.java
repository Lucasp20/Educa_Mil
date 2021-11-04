package br.com.educamil.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 250)
	private String nome;

	@Column(nullable = false, length = 14, unique = true)
	private String cpf;

	@Column(nullable = false, length = 15, unique = true)
	private String rg;

	@Column(length = 12, unique = true)
	private String tituloEleitor;

	@Column(length = 12, unique = true)
	private String matricula;

	@Column(nullable = false, length = 60, unique = true)
	private String email;

	@Column(nullable = false, length = 12)
	private Date dataNascimento;

	@Column(nullable = false, length = 15, unique = true)
	private String telefone;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private boolean enable;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Endereco endereco;

	@JoinColumn(name = "id_Perfil")
	@ManyToOne
	private Perfil perfil;
	
	
	public Pessoa(String nome, String cpf, String rg, String tituloEleitor, String matricula, String email,
			Date dataNascimento, String telefone, String senha, boolean enable) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.tituloEleitor = tituloEleitor;
		this.matricula = matricula;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.senha = senha;
		this.enable = enable;
	}
	
	public Pessoa() {
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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

}
