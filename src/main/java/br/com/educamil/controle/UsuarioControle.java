package br.com.educamil.controle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.educamil.entity.Perfil;
import br.com.educamil.entity.Turma;
import br.com.educamil.dao.*;
import br.com.educamil.entity.Usuario;

@ManagedBean(name = "usuarioC")
@ViewScoped
public class UsuarioControle {

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private Session sessao;
	private List<Usuario> usuarios;
	private DataModel<Usuario> modelUsuarios;
	private List<SelectItem> comboPerfis;
	private Perfil perfil;
	private int aba;

	public UsuarioControle() {
		usuarioDao = new UsuarioDaoImpl();
	
	}

	public void salvar() throws NoSuchAlgorithmException {
		sessao = HibernateUtil.abrirSessao();
//		 String hash = new String(this.getHash(usuario)); 
		try {
//			usuario.setSenha(hash); 
			usuarioDao.salvarOuAlterar(usuario, sessao);
			usuario = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário Salvo com Sucesso" , null));
			modelUsuarios = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o Usuário" , null));
		} finally {
			sessao.close();
		}
	}

	public void excluir() {
		usuario = modelUsuarios.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			usuarioDao.excluir(usuario, sessao);
			usuario = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário excluido com Sucesso" , null));
			modelUsuarios = null;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir Usuário", null));
		} finally {
			sessao.close();
		}
	}
	
	public void alterar() {
		usuario = modelUsuarios.getRowData();
		aba = 0;
	}
	
	public void pesquisarUsuarioLogin() {
		sessao = HibernateUtil.abrirSessao();
		try {
			usuarios = usuarioDao.pesquisarUsuarioLogin(usuario.getLogin(), sessao);
			modelUsuarios = new ListDataModel<>(usuarios);
			usuario.setLogin(null);
		} catch (HibernateException e) {
			System.out.println("Erro ao pesquisar usuários por login" + e.getMessage());
		}
		sessao.close();
	
	}
	

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getAba() {
		return aba;
	}

	public DataModel<Usuario> getModelUsuarios() {
		return modelUsuarios;
	}
	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public void setModelUsuarios(DataModel<Usuario> modelUsuarios) {
		this.modelUsuarios = modelUsuarios;
	}
	
	
	public List<SelectItem> getComboPerfis() {
		return comboPerfis;
	}
	

	public Perfil getPerfil() {
		if (perfil == null) {
			perfil = new Perfil();
		}
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	private String getHash(Usuario usuario) throws NoSuchAlgorithmException {
		String senha = new String(usuario.getSenha());
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
