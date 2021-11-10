package br.com.educamil.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.educamil.entity.Aluno;
import br.com.educamil.entity.Usuario;

public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Long> implements UsuarioDao, Serializable {

	@Override
	public Usuario pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Usuario) sessao.get(Usuario.class, id);
	}

	@Override
	public Usuario pesquisarPorLogin(String login, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Usuario where login like = :login");
		consulta.setParameter("login", login);
		return (Usuario) consulta.uniqueResult();
	}

	@Override
	public List<Usuario> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Usuario order by login");
		return consulta.list();
	}

}
