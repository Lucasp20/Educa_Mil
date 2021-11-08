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
import org.primefaces.util.StyleClassBuilder;

import br.com.educamil.dao.HibernateUtil;
import br.com.educamil.dao.ProfessorDao;
import br.com.educamil.dao.ProfessorDaoImpl;
import br.com.educamil.entity.*;
import br.com.educamil.webservice.WebServiceEndereco;

@ManagedBean(name = "professorC")
@ViewScoped
public class ProfessorControle {

	private Professor professor;
	private ProfessorDao professorDao;
	private Endereco endereco;
	private Session sessao;
	private List<Professor> professores;
	private DataModel<Professor> modelprofessores;
	private int aba;

	public ProfessorControle() {
		professorDao = new ProfessorDaoImpl();
	}



	public void buscarCep() {
		System.out.println("CEP AQUI" + endereco.getCep());
		WebServiceEndereco webservice = new WebServiceEndereco();
		endereco = webservice.pesquisarCep(endereco.getCep());
		if (endereco.getLogradouro() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não existe nenhum cep com esse valor" , null));
		}
	}

	/* mudar aba para novo */
	public void onTabChange(TabChangeEvent event) {
		if (event.getTab().getTitle().equals("Novo"))
			;
	}

	public void onTabClose(TabCloseEvent event) {
	}

	public void salvar() throws NoSuchAlgorithmException {
		sessao = HibernateUtil.abrirSessao();
		String hash = new String(this.getHash(professor));
		try {
			professor.setSenha(hash);
			endereco.setPessoa(professor);
			professor.setEndereco(endereco);
			professorDao.salvarOuAlterar(professor, sessao);
			professor = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor Salvo com Sucesso"  , null));
			modelprofessores = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o Professor" , null));
		} finally {
			sessao.close();
		}

	}
	
	public void excluir() {
		professor = modelprofessores.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			professorDao.excluir(professor, sessao);
			professor = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor excluido com Sucesso" , null));
			modelprofessores = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Professor", ""));
		} finally {
			sessao.close();
		}
	}

	public void alterar() {
		professor = modelprofessores.getRowData();
		endereco = professor.getEndereco();
		aba = 0;
	}
	
	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();
		try {
			professores = professorDao.pesquisarPorNome(professor.getNome(), sessao);
			modelprofessores = new ListDataModel<>(professores);
			professor.setNome(null);
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar Professor por nome" + e.getMessage());
		}
		sessao.close();
	}

	public Professor getProfessor() {
		if (professor == null) {
			professor = new Professor();
		}
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public DataModel<Professor> getModelprofessores() {
		return modelprofessores;
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

	private String getHash(Professor professor) throws NoSuchAlgorithmException {
		String senha = new String(professor.getSenha());
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
