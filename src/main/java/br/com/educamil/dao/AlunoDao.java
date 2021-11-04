package br.com.educamil.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.educamil.entity.Aluno;

public interface AlunoDao extends BaseDao<Aluno, Long>{
	
List<Aluno> pesquisarTodos(Session sessao) throws HibernateException;

List<Aluno> pesquisarPorNome(String nome, Session sessao) throws HibernateException;

}
