package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.*;

import br.com.senac.entidade.Dono;

public class DonoDaoImpl extends BaseDaoImpl<Dono, Long> implements DonoDao, Serializable{

	@Override
	public Dono pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Dono) sessao.get(Dono.class, id);
	}

	@Override
	public Dono pesquisarPorCpfDono(String cpf, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Dono where cpf = :cpf");
		consulta.setParameter("cpf", cpf);
		return (Dono) consulta.uniqueResult();
	}

	@Override
	public List<Dono> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Dono where nome like :nome");
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.list();
	}

}
