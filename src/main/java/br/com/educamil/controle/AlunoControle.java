package br.com.educamil.controle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import br.com.educamil.entity.Aluno;
import br.com.educamil.entity.Endereco;
import br.com.educamil.webservice.WebServiceEndereco;
import br.com.educamil.dao.AlunoDao;
import br.com.educamil.dao.AlunoDaoImpl;
import br.com.educamil.dao.HibernateUtil;
import br.com.educamil.dao.TurmaDao;
import br.com.educamil.dao.TurmaDaoImpl;
import br.com.educamil.entity.Turma;

@ManagedBean(name = "alunoC")
@ViewScoped
public class AlunoControle {

	private Aluno aluno;
	private AlunoDao alunoDao;
	private Session sessao;
	private List<Aluno> alunos;
	private Turma turma;
	private List<SelectItem> comboTurmas;
	private DataModel<Aluno> modelAlunos;
	private int aba;
	private Endereco endereco;

	public AlunoControle() {
		alunoDao = new AlunoDaoImpl();
	}

	public void salvar() throws NoSuchAlgorithmException {
		sessao = HibernateUtil.abrirSessao();
		String hash = new String(this.getHash(aluno));
		try {
			aluno.setSenha(hash);
			endereco.setPessoa(aluno);
			aluno.setEndereco(endereco);
			aluno.setTurma(turma);
			alunoDao.salvarOuAlterar(aluno, sessao);
			aluno = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Aluno Salvo com Sucesso"));
			modelAlunos = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro ao salvar o Aluno"));
		} finally {
			sessao.close();
		}
	}

	public void excluir() {
		aluno = modelAlunos.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			alunoDao.excluir(aluno, sessao);
			aluno = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Aluno excluido com Sucesso"));
			modelAlunos = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Aluno", ""));
		} finally {
			sessao.close();
		}
	}

	public void alterar() {
		aluno = modelAlunos.getRowData();
		endereco = aluno.getEndereco();
		turma = aluno.getTurma();
		aba = 0;
	}

	public void pesquisarPorNome() {
		sessao = HibernateUtil.abrirSessao();

		try {
			alunos = alunoDao.pesquisarPorNome(aluno.getNome(), sessao);
			modelAlunos = new ListDataModel<>(alunos);
			aluno.setNome(null);
		} catch (HibernateException e) {
			System.out.println("erro ao pesquisar alunos por nome: " + e.getMessage());
		} finally {
			sessao.close();
		}
	}

	public void buscarCep() {
		System.out.println("CEP AQUI" + endereco.getCep());
		WebServiceEndereco webservice = new WebServiceEndereco();
		endereco = webservice.pesquisarCep(endereco.getCep());
		if (endereco.getLogradouro() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Não existe nenhum cep com esse valor"));
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

	/* Inicio mudar aba para novo */
	public void onTabChange(TabChangeEvent event) {
		if (event.getTab().getTitle().equals("Novo"));
		
		comboBoxPelotao();
	}

	public void onTabClose(TabCloseEvent event) {
	}

	/* Fim mudar aba para novo */

	public Aluno getAluno() {
		if (aluno == null) {
			aluno = new Aluno();
		}
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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

	public DataModel<Aluno> getModelAlunos() {
		return modelAlunos;
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

	private String getHash(Aluno aluno) throws NoSuchAlgorithmException {
		String senha = new String(aluno.getSenha());
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
		// Convert digest to a string
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
