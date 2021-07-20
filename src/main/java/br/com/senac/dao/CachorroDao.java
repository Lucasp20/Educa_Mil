package br.com.senac.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.entidade.Animal;
import br.com.senac.entidade.Cachorro;

public interface CachorroDao extends BaseDao<Cachorro, Long>{
	
	List<Cachorro> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	
	List<Animal> pesquisarTodo(Session sessao) throws HibernateException;

}
