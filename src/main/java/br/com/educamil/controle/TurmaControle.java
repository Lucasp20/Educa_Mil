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

import br.com.educamil.dao.HibernateUtil;
import br.com.educamil.dao.TurmaDao;
import br.com.educamil.dao.TurmaDaoImpl;
import br.com.educamil.entity.*;

@ManagedBean(name = "turmaC")
@ViewScoped
public class TurmaControle {

	private Turma turma;
	private TurmaDao turmaDao;
	private Session sessao;
	private List<Turma> turmas;
	private DataModel<Turma> modelturmas;
	private int aba;

	public TurmaControle() {
		turmaDao = new TurmaDaoImpl();
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			turmas = turmaDao.pesquisarPorNome(turma.getPelotao(), sessao);
			modelturmas = new ListDataModel<>(turmas);
			turma.setPelotao(null);
		} catch (HibernateException e) {
			System.out.println("Erro ao pesquisar turmas por pelotão" + e.getMessage());
		}
		sessao.close();
	}

	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			turmaDao.salvarOuAlterar(turma, sessao);
			turma = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Pelotão salvo com Sucesso", null));
			modelturmas = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o Pelotão", ""));
		} finally {
			sessao.close();
		}

	}

	public void excluir() {
		turma = modelturmas.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			turmaDao.excluir(turma, sessao);
			turma = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Pelotão excluido com Sucesso"));
			modelturmas = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Turmas", ""));
		} finally {
			sessao.close();
		}
	}

	public void alterar() {
		turma = modelturmas.getRowData();
		aba = 0;

	}

	/* mudar aba para novo */
	public void onTabChange(TabChangeEvent event) {
		if (event.getTab().getTitle().equals("Novo"));
	}

	public void onTabClose(TabCloseEvent event) {
	}

	/* fim mudar aba para novo */

	public Turma getTurma() {
		if (turma == null) {
			turma = new Turma();
		}
		return turma;
	}

	public DataModel<Turma> getModelturmas() {
		return modelturmas;
	}

	public void setModelturmas(DataModel<Turma> modelturmas) {
		this.modelturmas = modelturmas;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public int getAba() {
		return aba;
	}

}
