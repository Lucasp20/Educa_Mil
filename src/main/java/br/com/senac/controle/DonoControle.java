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


@ManagedBean(name = "donoC")
@ViewScoped
public class DonoControle {

	private Dono dono;
	private Endereco endereco;
	private DonoDao donoDao;
	private Session sessao;
	private List<Dono> donos;
	private List<Dono> cpfs;
	private DataModel<Dono> modeldonos;
	private int aba;

	public DonoControle() {
		donoDao = new DonoDaoImpl();
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			donos = donoDao.pesquisarPorNome(dono.getNome(), sessao);
			modeldonos = new ListDataModel<>(donos);
			dono.setNome(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar Dono por nome" + e.getMessage());
		} finally {
			sessao.close();
		}

	}
	
	public void pesquisarPorCpfDono() {
		sessao = HibernateUtil.abrirSessao();
		try {
			dono = donoDao.pesquisarPorCpfDono(dono.getCpf(), sessao);
			dono.setCpf(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar dono por cpf" + e.getMessage());
		} finally {
			sessao.close();
		}
	}

	
	public void buscarCep() {
		WebServiceEndereco webservice = new WebServiceEndereco();
		endereco = webservice.pesquisarCep(endereco.getCep());
		if(endereco.getLogradouro() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não existe nenhum cep com esse valor", null));
		}
	}

	/* mudar aba para novo e carrega o comboBox */
	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getTitle().equals("Novo"));
	}

	public void onTabClose(TabCloseEvent event) {
	}
	
	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			endereco.setDono(dono);
			dono.setEndereco(endereco);
			donoDao.salvarOuAlterar(dono, sessao);
			dono = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Dono Salvo com Sucesso", null));
			modeldonos = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o Dono", null));
		} finally {
			sessao.close();
		}
	
	}

	public void excluir() {
		dono = modeldonos.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			donoDao.excluir(dono, sessao);
			dono = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "O Dono foi excluido com Sucesso", null));
			modeldonos = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir o Dono", ""));
		} finally {
			sessao.close();
		}
	}

	public void prepararAlterar() {
		dono = modeldonos.getRowData();
		endereco = dono.getEndereco();
		aba = 1;
	}

	public Dono getDono() {
		if (dono == null) {
			dono = new Dono();
		}
		return dono;
	}

	public void setDono(Dono dono) {
		this.dono = dono;
	}

	public DataModel<Dono> getModeldonos() {
		return modeldonos;
	}

	public int getAba() {
		return aba;
	}
	
	public Endereco getEndereco() {
		if(endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}	
	
}
