package br.com.educamil.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.educamil.dao.DisciplinaDao;
import br.com.educamil.dao.DisciplinaDaoImpl;
import br.com.educamil.dao.HibernateUtil;
import br.com.educamil.dao.NotaDao;

import br.com.educamil.dao.NotaDaoImpl;
import br.com.educamil.dao.TurmaDao;
import br.com.educamil.dao.TurmaDaoImpl;
import br.com.educamil.entity.*;
import javax.faces.model.SelectItem;

@ManagedBean(name = "notaC")
@ViewScoped
public class NotaControle {

    private Nota nota;
    private NotaDao notaDao;
    private Session sessao;
    private Disciplina disciplina;
    private Turma turma;
    private Aluno aluno;
    private List<Nota> notas;
    private List<Disciplina> disciplinas;
    private List<Aluno> alunos;
    private DataModel<Nota> modelNotas;
    private List<SelectItem> comboDisciplinas;
    private List<SelectItem> comboTurmas;
    private int aba;

    public NotaControle() {
        notaDao = new NotaDaoImpl();
        comboBoxDisciplinas();
        comboBoxPelotao();
    }

    public void salvar() {
        sessao = HibernateUtil.abrirSessao();
        try {
            notaDao.salvarOuAlterar(nota, sessao);
            nota = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Notas Salvas com Sucesso", null));
			modelNotas = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar as notas", null));
        } finally {
            sessao.close();
        }
    }
    
    public void excluir() {
    	
    }
    
    public void alterar() {

	}
    
    public void comboBoxDisciplinas() {
    	sessao = HibernateUtil.abrirSessao();
    	DisciplinaDao disciplinaDao = new DisciplinaDaoImpl();
    	try {
			List<Disciplina> disciplinas = disciplinaDao.pesquisarTodos(sessao);
			comboDisciplinas = new ArrayList<>();
				for(Disciplina dis : disciplinas) {
					comboDisciplinas.add(new SelectItem(dis.getNome(), dis.getNome()));
				}
		} catch (Exception e) {
			System.out.println("Erro ao carregar combobox Disciplina" + e.getMessage());
		}finally {
			sessao.close();
		}
    }
    
    public void comboBoxPelotao() {
		sessao = HibernateUtil.abrirSessao();
		TurmaDao turmaDao = new TurmaDaoImpl();
		try {
			List<Turma> turmas = turmaDao.pesqusiarTodos(sessao);
			comboTurmas = new ArrayList<>();
			for (Turma tur : turmas) {
				comboTurmas.add(new SelectItem(tur.getPelotao(), tur.getPelotao() + " - " + tur.getAno() ));
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar combobox pelotão" + e.getMessage());
		} finally {
			sessao.close();
		}

	}
    
    public void pesquisarAlunoPelotao() {
		sessao = HibernateUtil.abrirSessao();
		DisciplinaDao disciplinaDao = new DisciplinaDaoImpl();
		try {
			disciplina = disciplinaDao.pesquisarPorNomePelotao(turma.getPelotao(), disciplina.getNome(), "2021", sessao); 
			modelNotas = new ListDataModel<Nota>(disciplina.getNotas());
			for(Nota nota: disciplina.getNotas()) {
				System.out.println(nota.getNotaUm() + "  " + nota.getAluno().getNome() + "  "  );
				}
//			int i=0;
//			for(Nota modelNota: modelNotas ) {
//				Aluno aluno = disciplina.getNotas().get(i).getAluno();
//				modelNota.setAluno(aluno);
//				modelNota.setNome(aluno.getNome());
//				System.out.println("teste" + modelNota.getNotaUm() + "  " + modelNota.getAluno().getNome() + "  "  );
//				i++;
			//}
		} catch (HibernateException e) {
			System.out.println("Erro ao pesquisar alunos por nome: " + e.getMessage());
		} finally {
			sessao.close();
		}
	}
    
public double mediaNotas() {
    	
    	List<Turma> turmas = new ArrayList<Turma>();
    	
    	for(int i=0; i <= turmas.size(); i++) {
    		
    	} 	
    	return (Double) null;
    }
      
    
	public Nota getNota() {
		if (nota == null) {
			nota = new Nota();
		}
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}
    
	public Disciplina getDisciplina() {
		if (disciplina == null) {
			disciplina = new Disciplina();
		}
		return disciplina;
	}
	
	public Turma getTurma() {
		if (turma == null) {
			turma = new Turma();
		}
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public int getAba() {
		return aba;
	}

	public List<SelectItem> getComboDisciplinas() {
		return comboDisciplinas;
	}

	public List<SelectItem> getComboTurmas() {
		return comboTurmas;
	}

	public DataModel<Nota> getModelNotas() {
		return modelNotas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}   

	
	
}
