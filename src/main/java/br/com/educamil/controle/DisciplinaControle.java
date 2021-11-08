package br.com.educamil.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.util.StyleClassBuilder;

import br.com.educamil.dao.DisciplinaDao;
import br.com.educamil.dao.DisciplinaDaoImpl;
import br.com.educamil.dao.HibernateUtil;
import br.com.educamil.entity.*;
import br.com.educamil.webservice.WebServiceEndereco;

@ManagedBean(name = "disciplinaC")
@ViewScoped
public class DisciplinaControle {

	private Disciplina disciplina;
	private DisciplinaDao disciplinaDao;
	private Session sessao;
	private List<Disciplina> disciplinas;
	private DataModel<Disciplina> modeldisciplinas;
	private int aba;

	public DisciplinaControle() {
		disciplinaDao = new DisciplinaDaoImpl();
	}

	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			disciplinaDao.salvarOuAlterar(disciplina, sessao);
			disciplina = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Disciplina salva com Sucesso", null));
			modeldisciplinas= null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar a Disciplina", null));
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
	
	/* mudar aba para novo */
	public void onTabChange(TabChangeEvent event) {
		if (event.getTab().getTitle().equals("Novo"))
			;
	}

	public void onTabClose(TabCloseEvent event) {
	}
	
	/* fim mudar aba para novo */

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

	
	
	
}
