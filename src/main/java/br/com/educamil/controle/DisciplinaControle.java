package br.com.educamil.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.util.StyleClassBuilder;

import br.com.educamil.dao.DisciplinaDao;
import br.com.educamil.dao.DisciplinaDaoImpl;
import br.com.educamil.dao.HibernateUtil;
import br.com.educamil.dao.TurmaDao;
import br.com.educamil.dao.TurmaDaoImpl;
import br.com.educamil.entity.*;
import br.com.educamil.webservice.WebServiceEndereco;

@ManagedBean(name = "disciplinaC")
@ViewScoped
public class DisciplinaControle {

	private Disciplina disciplina;
	private DisciplinaDao disciplinaDao;
	private Session sessao;
	private Turma turma;
	private List<Disciplina> disciplinas;
	private DataModel<Disciplina> modeldisciplinas;
	private List<SelectItem> comboTurmas;
	private int aba;

	public DisciplinaControle() {
		disciplinaDao = new DisciplinaDaoImpl();
		comboBoxPelotao();
	}

	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			disciplina.setTurma(turma);
			disciplinaDao.salvarOuAlterar(disciplina, sessao);
			disciplina = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Disciplina salva com Sucesso", null));
			modeldisciplinas= null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Disciplina", null));
		} finally {
			sessao.close();
		}

	}

	public void excluir() {
		disciplina = modeldisciplinas.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			disciplinaDao.excluir(disciplina, sessao);
			disciplina = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,"Disciplina excluida com Sucesso", null));
			modeldisciplinas = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Disciplina", null));
		} finally {
			sessao.close();
		}
	}

	public void alterar() {
		disciplina = modeldisciplinas.getRowData();
		turma = disciplina.getTurma();
		aba = 0;
	}
	
	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			disciplinas = disciplinaDao.pesquisarPorNome(disciplina.getNome(), sessao);
			modeldisciplinas = new ListDataModel<>(disciplinas);
			disciplina.setNome(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar Disciplina por nome" + e.getMessage());
		}
		sessao.close();
	}
	
	public void pesquisarNotaAluno (){
		sessao = HibernateUtil.abrirSessao();
		try {
			
		} catch (Exception e) {
			
		}
	}
	
	public void comboBoxPelotao() {
		sessao = HibernateUtil.abrirSessao();
		TurmaDao turmaDao = new TurmaDaoImpl();
		try {
			List<Turma> turmas = turmaDao.pesqusiarTodos(sessao);
			comboTurmas = new ArrayList<>();
			for (Turma tur : turmas) {
				comboTurmas.add(new SelectItem(tur.getId(), tur.getPelotao()));
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar combobox pelotão" + e.getMessage());
		} finally {
			sessao.close();
		}

	}
	
	public Disciplina getDisciplina() {
		if (disciplina == null) {
			disciplina = new Disciplina();
		}
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public DataModel<Disciplina> getModeldisciplinas() {
		return modeldisciplinas;
	}
	
	public void setModeldisciplinas(DataModel<Disciplina> modeldisciplinas) {
		this.modeldisciplinas = modeldisciplinas;
	}

	public int getAba() {
		return aba;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<SelectItem> getComboTurmas() {
		return comboTurmas;
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
	
}
