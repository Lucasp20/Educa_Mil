package br.com.educamil.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "turma")
public class Turma {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String pelotao;

    @Column(nullable = false, length = 4)
    private String ano;
    
    @OneToMany(mappedBy = "turma")
    private List<Aluno> aluno;
    
	@OneToMany() 
	private List<Disciplina> Disciplinas;
		
    public Turma(String pelotao, String ano) {
        super();
        this.pelotao = pelotao;
        this.ano = ano;
    }

    public Turma() {
        super();

    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPelotao() {
        return pelotao;
    }

    public void setPelotao(String pelotao) {
        this.pelotao = pelotao;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }


    public List<Aluno> getAluno() {
		return aluno;
	}

	public void setAluno(List<Aluno> aluno) {
		this.aluno = aluno;
	}
	
	/*
	 * public List<Disciplina> getDisciplinas() { return Disciplinas; }
	 * 
	 * public void setDisciplinas(List<Disciplina> disciplinas) { Disciplinas =
	 * disciplinas; }
	 */

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turma other = (Turma) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
