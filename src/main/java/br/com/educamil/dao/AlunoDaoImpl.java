package br.com.educamil.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.educamil.entity.Aluno;
import br.com.educamil.entity.Usuario;

public class AlunoDaoImpl extends BaseDaoImpl<Aluno, Long> implements AlunoDao, Serializable {

	@Override
	public Aluno pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Aluno) sessao.get(Aluno.class, id);
	}

	@Override
	public List<Aluno> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Aluno where nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}

	@Override
	public List<Aluno> pesquisarTodos(Session sessao) throws HibernateException {
		return sessao.createQuery("from Aluno order by nome").list();

	}


}
