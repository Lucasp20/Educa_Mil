package br.com.educamil.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String tipoEnsino;

    @Column(nullable = false, length = 10)
    private String cargaHoraria;

    @OneToMany(mappedBy = "disciplina")
    private List<Nota> notas;

	/*
	 * @ManyToMany(mappedBy = "disciplina") private List<Turma> turmas;
	 * 
	 * @ManyToMany(mappedBy = "disciplina") private List<Professor> professores;
	 */
       
    public Disciplina() {
        super();

    }

    public Disciplina(String nome, String tipoEnsino, String cargaHoraria) {
        super();
        this.nome = nome;
        this.tipoEnsino = tipoEnsino;
        this.cargaHoraria = cargaHoraria;
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

    public String getTipoEnsino() {
        return tipoEnsino;
    }

    public void setTipoEnsino(String tipoEnsino) {
        this.tipoEnsino = tipoEnsino;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

	/*
	 * public List<Professor> getProfessores() { return professores; }
	 * 
	 * public void setProfessores(List<Professor> professores) { this.professores =
	 * professores; }
	 * 
	 * 
	 * 
	 * public List<Turma> getTurmas() { return turmas; }
	 * 
	 * public void setTurmas(List<Turma> turmas) { this.turmas = turmas; }
	 */

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
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
        Disciplina other = (Disciplina) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
