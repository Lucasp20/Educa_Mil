package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.senac.entidade.Animal;
import br.com.senac.entidade.Gato;

public class GatoDaoImpl extends BaseDaoImpl<Gato, Long> implements GatoDao, Serializable{

	@Override
	public Gato pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Gato) sessao.get(Gato.class, id);
	}

	@Override
	public List<Gato> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Gato where nome = :nome");
		consulta.setParameter("nome", nome);
		return consulta.list();
	}

	@Override
	public List<Animal> pesquisarTodo(Session sessao) throws HibernateException {
		return sessao.createQuery("from Animal order by nome").list();
	}
}
