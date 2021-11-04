package br.com.educamil.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.educamil.entity.Direcao;


public class DirecaoDaoImpl extends BaseDaoImpl<Direcao, Long> implements DirecaoDao, Serializable {

	@Override
	public Direcao pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Direcao) sessao.get(Direcao.class, id);
	}

	@Override
	public List<Direcao> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Direcao where nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}

	@Override
	public List<Direcao> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Direcao order by nome");
		return consulta.list();
	}
	
	@Override
	public Direcao pesquisarPorEmail(String email, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("FROM Direcao WHERE email = :email");
        consulta.setParameter("email", email);
        return (Direcao) consulta.uniqueResult();
	}

	
}
