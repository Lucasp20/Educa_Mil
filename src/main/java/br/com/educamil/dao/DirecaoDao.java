package br.com.educamil.dao;

import java.util.List;
import org.hibernate.*;

import br.com.educamil.entity.Direcao;

public interface DirecaoDao extends BaseDao<Direcao, Long>{

	List<Direcao> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	
	List<Direcao> pesquisarTodos(Session sessao) throws HibernateException;
	
	Direcao pesquisarPorEmail(String email, Session sessao) throws HibernateException;
}
