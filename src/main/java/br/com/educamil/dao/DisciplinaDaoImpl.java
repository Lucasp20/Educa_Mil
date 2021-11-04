package br.com.educamil.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.educamil.entity.Direcao;
import br.com.educamil.entity.Disciplina;


public class DisciplinaDaoImpl extends BaseDaoImpl<Disciplina, Long> implements DisciplinaDao, Serializable {

	@Override
	public Disciplina pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Disciplina) sessao.get(Disciplina.class, id);
	}

	@Override
	public List<Disciplina> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Disciplina where nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}

	@Override
	public List<Disciplina> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Disciplina order by nome");
		return consulta.list();
	}

	
}
