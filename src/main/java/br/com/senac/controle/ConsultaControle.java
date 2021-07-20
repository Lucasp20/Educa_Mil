package br.com.senac.controle;

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



import br.com.senac.dao.*;
import br.com.senac.entidade.*;

@ManagedBean(name = "consultaC")
@ViewScoped
public class ConsultaControle {

	private Consulta consulta;
	private ConsultaDao consultaDao;
	private Animal animal;
	private Session sessao;
	private List<SelectItem> comboAnimal;
	private List<Consulta> consultas;
	private DataModel<Consulta> modelconsultas;
	private int aba;

	public ConsultaControle() {
		consultaDao = new ConsultaDaoImpl();
	}

	public void pesquisarPorData() {
		sessao = HibernateUtil.abrirSessao();
		try {
			consultas = consultaDao.pesquisarPorData(consulta.getDia(), sessao);
			modelconsultas = new ListDataModel<>(consultas);
			consulta.setDia(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar consulta por data" + e.getMessage());
		} finally {
			sessao.close();
		}

	}
	
	public void carregarComboAnimal() {
		sessao = HibernateUtil.abrirSessao();
		AnimalDao animalDao = new AnimalDaoImpl();
		try {
			List<Animal> animais = animalDao.pesquisarTodo(sessao);
			comboAnimal = new ArrayList<>();
			for (Animal animal : animais) {
				comboAnimal.add(new SelectItem(animal.getId(), animal.getNome()));
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar combobox cachorros" + e.getMessage());
		}finally {
			sessao.close();
		}
		
	}
	
	/* mudar aba para novo e carrega o comboBox */
	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getTitle().equals("Novo"));
		carregarComboAnimal();
	}

	public void onTabClose(TabCloseEvent event) {
	}
	
	public void salvar() {
		sessao = HibernateUtil.abrirSessao();
		try {
			consultaDao.salvarOuAlterar(consulta, sessao);
			consulta = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Salva com Sucesso", null));
			modelconsultas = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o Consulta", null));
		} finally {
			sessao.close();
		}
	
	}

	public void excluir() {
		consulta = modelconsultas.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			consultaDao.excluir(consulta, sessao);
			consulta = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "O Consulta foi excluido com Sucesso", null));
			modelconsultas = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir o Consulta", ""));
		} finally {
			sessao.close();
		}
	}

	public void prepararAlterar() {
		consulta = modelconsultas.getRowData();
		animal = consulta.getAnimal();
		aba = 1;
	}

	public Consulta getConsulta() {
		if (consulta == null) {
			consulta = new Consulta();
		}
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public DataModel<Consulta> getModelconsultas() {
		return modelconsultas;
	}
	
	public List<SelectItem> getComboAnimal() {
		return comboAnimal;
	}

	public int getAba() {
		return aba;
	}
	
	public Animal getAnimal() {
		if(animal == null) {
			animal = new Animal();
		}
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
