package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.senac.entidade.Animal;
import br.com.senac.entidade.Cachorro;


public class AnimalDaoImpl extends BaseDaoImpl<Animal, Long> implements AnimalDao, Serializable{

	
	@Override
	public Animal pesquisarPorId(Long id, Session sessao) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Animal> pesquisarTodo(Session sessao) throws HibernateException {
		return sessao.createQuery("from Animal order by nome").list();
	}

}
