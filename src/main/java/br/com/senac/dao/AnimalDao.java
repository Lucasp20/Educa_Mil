package br.com.senac.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.entidade.Animal;
import br.com.senac.entidade.Cachorro;

public interface AnimalDao extends BaseDao<Animal, Long>{
		
	List<Animal> pesquisarTodo(Session sessao) throws HibernateException;

}
