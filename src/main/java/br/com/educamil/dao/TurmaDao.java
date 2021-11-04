package br.com.educamil.dao;

import java.util.List;
import org.hibernate.*;

import br.com.educamil.entity.Professor;
import br.com.educamil.entity.Turma;


public interface TurmaDao extends BaseDao<Turma, Long>{

	List<Turma> pesquisarPorNome(String pelotao, Session sessao) throws HibernateException;
	
	List<Turma> pesqusiarTodos(Session sessao) throws HibernateException; 
}
