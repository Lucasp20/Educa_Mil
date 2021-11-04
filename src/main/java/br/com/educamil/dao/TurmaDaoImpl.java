package br.com.educamil.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.*;

import br.com.educamil.entity.Turma;


public class TurmaDaoImpl extends BaseDaoImpl<Turma, Long> implements TurmaDao, Serializable {

	@Override
	public Turma pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Turma) sessao.get(Turma.class, id);
	}

	@Override
	public List<Turma> pesquisarPorNome(String pelotao, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Turma where pelotao like :pelotao");
		consulta.setParameter("pelotao", "%" + pelotao + "%");
		return consulta.list();
	}

	@Override
	public List<Turma> pesqusiarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Turma order by pelotao");
		return consulta.list();
	}

	
}
