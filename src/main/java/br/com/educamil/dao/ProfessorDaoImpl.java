package br.com.educamil.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.educamil.entity.Professor;


public class ProfessorDaoImpl extends BaseDaoImpl<Professor, Long> implements ProfessorDao, Serializable {

	@Override
	public Professor pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Professor) sessao.get(Professor.class, id);
	}

	@Override
	public List<Professor> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Professor where nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}

	@Override
	public List<Professor> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Professor order by nome");
		return consulta.list();
	}

	
}
