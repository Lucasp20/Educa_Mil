package br.com.educamil.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Aluno extends Pessoa {

    @Column(nullable = false, length = 250)
    private String nomeMae;

    @Column(nullable = false, length = 250)
    private String nomePai;
    
    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @OneToMany(mappedBy = "aluno")
    private List<Nota> notas;
      

    public Aluno(String nomeMae, String nomePai, Turma turma) {
        super();
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.turma = turma;
    }

    public Aluno() {
        super();
 
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nomeMae);
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
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.nomeMae, other.nomeMae)) {
            return false;
        }
        return true;
    }

}
