package br.com.senac.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class BaseDaoImpl <T, ID> implements BaseDao<T, ID>{

private Transaction transacao;
    
    @Override
    public void salvarOuAlterar(T entidade, Session sessao) throws HibernateException {
    	transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(entidade);
        transacao.commit();
    }

    @Override
    public void excluir(T entidade, Session sessao) throws HibernateException {
        transacao = sessao.beginTransaction();
        sessao.delete(entidade);
        transacao.commit();
    }

	

}
