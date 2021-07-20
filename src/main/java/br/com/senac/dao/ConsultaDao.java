package br.com.senac.dao;

import java.util.*;
import org.hibernate.*;

import br.com.senac.entidade.Consulta;

public interface ConsultaDao extends BaseDao<Consulta, Long> {

	List<Consulta> pesquisarPorData(Date dia, Session sessao) throws HibernateException;
	

}
