package br.com.educamil.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import br.com.educamil.entity.Usuario;

public interface UsuarioDao extends BaseDao<Usuario, Long> {

	List<Usuario> pesquisarTodos(Session sessao) throws HibernateException;

	List<Usuario> pesquisarPorLogin(String login, Session sessao) throws HibernateException;

}
