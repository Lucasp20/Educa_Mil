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

@ManagedBean(name = "cachorroC")
@ViewScoped
public class CachorroControle {

	private Cachorro cachorro;
	private Dono dono;
	private CachorroDao cachorroDao;
	private Session sessao;
	private List<Cachorro> cachorros;
	private DataModel<Cachorro> modelcaes;
	private int aba;

	public CachorroControle() {
		cachorroDao = new CachorroDaoImpl();
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			cachorros = cachorroDao.pesquisarPorNome(cachorro.getNome(), sessao);
			modelcaes = new ListDataModel<>(cachorros);
			cachorro.setNome(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar cachorro por nome" + e.getMessage());
		} finally {
			sessao.close();
		}

	}
	
	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			cachorro.setDono(dono);
			cachorroDao.salvarOuAlterar(cachorro, sessao);
			cachorro = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cachorro Salvo com Sucesso", null));
			modelcaes = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o Cachorro", null));
		} finally {
			sessao.close();
		}
	
	}

	public void excluir() {
		cachorro = modelcaes.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			cachorroDao.excluir(cachorro, sessao);
			cachorro = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "O Cachorro foi excluido com Sucesso", null));
			modelcaes = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir o Cachorro", ""));
		} finally {
			sessao.close();
		}
	}

	public void prepararAlterar() {
		cachorro = modelcaes.getRowData();
		aba = 1;
	}
	
	/* mudar aba para novo */
	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getTitle().equals("Novo"));
	}

	public void onTabClose(TabCloseEvent event) {
	}

	public Cachorro getCachorro() {
		if (cachorro == null) {
			cachorro = new Cachorro();
		}
		return cachorro;
	}

	public void setCachorro(Cachorro cachorro) {
		this.cachorro = cachorro;
	}

	public DataModel<Cachorro> getModelcaes() {
		return modelcaes;
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
