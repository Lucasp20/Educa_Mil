package br.com.senac.dao;

import java.util.List;

import org.hibernate.*;

import br.com.senac.entidade.Animal;
import br.com.senac.entidade.Gato;

public interface GatoDao extends BaseDao<Gato, Long>{
	
	List<Gato> pesquisarPorNome(String nome, Session sessao) throws HibernateException;

	List<Animal> pesquisarTodo(Session sessao) throws HibernateException;
}
