package br.com.educamil.controle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import br.com.educamil.dao.DirecaoDao;
import br.com.educamil.dao.DirecaoDaoImpl;
import br.com.educamil.dao.HibernateUtil;
import br.com.educamil.entity.*;
import br.com.educamil.webservice.WebServiceEndereco;

@ManagedBean(name = "direcaoC")
@ViewScoped
public class DirecaoControle {

	private Direcao direcao;
	private DirecaoDao direcaoDao;
	private Endereco endereco;
	private Session sessao;
	private List<Direcao> diretores;
	private DataModel<Direcao> modeldiretores;
	private int aba;
	
	public DirecaoControle() {
		direcaoDao = new DirecaoDaoImpl();
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			diretores = direcaoDao.pesquisarPorNome(direcao.getNome(), sessao);
			modeldiretores = new ListDataModel<>(diretores);
			direcao.setNome(null);
		} catch (HibernateException e) {
			System.out.println("Erro ao pesquisar Diretores por nome" + e.getMessage());
		}
		sessao.close();
	}
	
	public void buscarCep() {
		System.out.println("CEP AQUI" + endereco.getCep());
		WebServiceEndereco webservice = new WebServiceEndereco();
		endereco = webservice.pesquisarCep(endereco.getCep());
		if (endereco.getLogradouro() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não existe nenhum cep com esse valor", null));
		}
	}

	public void salvar() throws NoSuchAlgorithmException {
		sessao = HibernateUtil.abrirSessao();
		/* String hash = new String(this.getHash(direcao)); */
		try {
			/* direcao.setSenha(hash); */
			endereco.setPessoa(direcao);
			direcao.setEndereco(endereco);
			direcaoDao.salvarOuAlterar(direcao, sessao);
			direcao = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Responsável Salvo com Sucesso", null));
			modeldiretores = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao salvar o Responsável" , null));
		} finally {
			sessao.close();
		}

	}

	public void excluir() {
		direcao = modeldiretores.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			direcaoDao.excluir(direcao, sessao);
			direcao = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Responsável excluido com Sucesso" , null));
			modeldiretores = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Responsável", null));
		} finally {
			sessao.close();
		}
	}

	public void alterar() {
		direcao = modeldiretores.getRowData();
		endereco = direcao.getEndereco();
		aba = 0;
	}
	
	/* mudar aba para novo */
	public void onTabChange(TabChangeEvent event) {
		if (event.getTab().getTitle().equals("Novo"))
			;
	}

	public void onTabClose(TabCloseEvent event) {
	}
	
	/* fim mudar aba para novo */

	public Direcao getDirecao() {
		if (direcao == null) {
			direcao = new Direcao();
		}
		return direcao;
	}

	public void setDirecao(Direcao direcao) {
		this.direcao = direcao;
	}
	

	public List<Direcao> getDiretores() {
		return diretores;
	}
	
	public void setDiretores(List<Direcao> diretores) {
		this.diretores = diretores;
	}


	public DataModel<Direcao> getModeldiretores() {
		return modeldiretores;
	}
	
	public void setModeldiretores(DataModel<Direcao> modeldiretores) {
		this.modeldiretores = modeldiretores;
	}
	
	public int getAba() {
		return aba;
	}

	public Endereco getEndereco() {
		if (endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	private String getHash(Direcao direcao) throws NoSuchAlgorithmException {
		String senha = new String(direcao.getSenha());
		byte[] digest = sha512(senha);
		String hash = hexaToString(digest);
		return hash;
	}
	private byte[] sha512(String message) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(message.getBytes());
		byte[] digest = md.digest();
		return digest;
	}
	
	private String hexaToString(byte[] digest) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			if ((0xff & digest[i]) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & digest[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & digest[i]));
			}
		}
		return hexString.toString();
	}
	

}
