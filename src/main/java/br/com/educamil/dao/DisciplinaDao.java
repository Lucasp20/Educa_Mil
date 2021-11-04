package br.com.educamil.dao;

import java.util.List;
import org.hibernate.*;
import br.com.educamil.entity.Disciplina;

public interface DisciplinaDao extends BaseDao<Disciplina, Long>{

	List<Disciplina> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	
	List<Disciplina> pesquisarTodos(Session sessao) throws HibernateException;
}
