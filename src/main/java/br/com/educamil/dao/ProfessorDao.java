package br.com.educamil.dao;

import java.util.List;
import org.hibernate.*;

import br.com.educamil.entity.Professor;


public interface ProfessorDao extends BaseDao<Professor, Long>{

	List<Professor> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	
	List<Professor> pesquisarTodos(Session sessao) throws HibernateException;
}
