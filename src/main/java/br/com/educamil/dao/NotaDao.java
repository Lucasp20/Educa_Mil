package br.com.educamil.dao;

import br.com.educamil.entity.Disciplina;
import br.com.educamil.entity.Nota;
import br.com.educamil.entity.Turma;
import java.util.List;
import org.hibernate.*;

public interface NotaDao extends BaseDao<Nota, Long>{

	List<Disciplina> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
        
	List<Turma> pesquisarPorPelotaoEDisciplina(String turma, String disciplina, Session sessao) throws HibernateException;
}
