package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.senac.entidade.Animal;
import br.com.senac.entidade.Cachorro;


public class CachorroDaoImpl extends BaseDaoImpl<Cachorro, Long> implements CachorroDao, Serializable{

	@Override
	public Cachorro pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Cachorro) sessao.get(Cachorro.class, id);
	}

	@Override
	public List<Cachorro> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Cachorro where nome = :nome");
		consulta.setParameter("nome", nome);
		return consulta.list();
	}

	@Override
	public List<Animal> pesquisarTodo(Session sessao) throws HibernateException {
		return sessao.createQuery("from Animal order by nome").list();
	}

}
