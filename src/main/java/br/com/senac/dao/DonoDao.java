package br.com.senac.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.entidade.Dono;

public interface DonoDao extends BaseDao<Dono, Long>{

	Dono pesquisarPorCpfDono(String cpf, Session sessao) throws HibernateException;
	
	List<Dono> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
}
