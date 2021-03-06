package br.com.educamil.dao;

import br.com.educamil.entity.Disciplina;
import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.*;
import org.hibernate.Session;

import br.com.educamil.entity.Nota;
import br.com.educamil.entity.Turma;

public class NotaDaoImpl extends BaseDaoImpl<Nota, Long> implements NotaDao, Serializable {

	@Override
	public Nota pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Nota) sessao.get(Nota.class, id);
	}

	@Override
	public List<Disciplina> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Aluno where nome = :nome");
		consulta.setParameter("nome", nome);
		return consulta.list();

		// apenas iniciei, revisar, não está correto
	}

	
//	@Override
//	public Turma pesquisarPorPelotaoEDisciplina(String turma, String disciplina, Session sessao)
//			throws HibernateException {
//		Query consulta = sessao.createQuery(
//				"from Turma t join fetch t.disciplinas d where t.pelotao = :pelotao " + "and d.nome = :nome");
//		consulta.setParameter("pelotao", turma);
//		consulta.setParameter("nome", disciplina);
//		Turma turmaTeste = (Turma) consulta.uniqueResult();
//		consulta = sessao.createQuery("from Turma t join fetch t.alunos");
//		return (Turma) consulta.uniqueResult();
//		
		
		

	}


