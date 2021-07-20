package br.com.senac.controle;

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

import br.com.senac.dao.*;
import br.com.senac.entidade.*;

@ManagedBean(name = "gatoC")
@ViewScoped
public class GatoControle {

	private Gato gato;
	private Dono dono;
	private GatoDao gatoDao;
	private Session sessao;
	private List<Gato> gatos;
	private DataModel<Gato> modelgatos;
	private int aba;

	public GatoControle() {
		gatoDao = new GatoDaoImpl();
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			gatos = gatoDao.pesquisarPorNome(gato.getNome(), sessao);
			modelgatos = new ListDataModel<>(gatos);
			gato.setNome(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar gato por nome" + e.getMessage());
		} finally {
			sessao.close();
		}
	}
	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			gato.setDono(dono);
			gatoDao.salvarOuAlterar(gato, sessao);
			gato = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Gato Salvo com Sucesso", null));
			modelgatos = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o Gato", null));
		} finally {
			sessao.close();
		}

	}

	public void excluir() {
		gato = modelgatos.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			gatoDao.excluir(gato, sessao);
			gato = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "O Gato foi excluido com Sucesso", null));
			modelgatos = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir o Gato", ""));
		} finally {
			sessao.close();
		}
	}
	
	/* mudar aba para novo e carrega o comboBox */
	public void onTabChange(TabChangeEvent event) {
		if (event.getTab().getTitle().equals("Novo"))
			;
	}

	public void onTabClose(TabCloseEvent event) {
	}

	public void prepararAlterar() {
		gato = modelgatos.getRowData();
		aba = 1;
	}

	public Gato getGato() {
		if (gato == null) {
			gato = new Gato();
		}
		return gato;
	}

	public void setGato(Gato gato) {
		this.gato = gato;
	}

	public DataModel<Gato> getModelgatos() {
		return modelgatos;
	}

	public int getAba() {
		return aba;
	}
	
	public Dono getDono() {
		if(dono == null) {
			dono = new Dono();
		}
		return dono;
	}

	public void setDono(Dono dono) {
		this.dono = dono;
	}

	
}
