package br.com.educamil.dao;


import static org.junit.Assert.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import br.com.educamil.entity.Disciplina;
import br.com.educamil.entity.Nota;
import br.com.educamil.entity.Turma;

public class NotaDaoImplTest {

	@Test
	public void pesquisarPorPelotaoEDisciplina() {
		System.out.println("pesquisarPorPelotaoEDisciplina");
		Session sessao = HibernateUtil.abrirSessao();
		DisciplinaDao notaTeste = new DisciplinaDaoImpl();
		Disciplina disciplina = null;
		try {
			disciplina = notaTeste.pesquisarPorNomePelotao("primeiro", "física", "2021", sessao);
			for(Nota nota: disciplina.getNotas()) {
			System.out.println(nota.getNotaUm() + "  " + nota.getAluno().getNome() + "  "  );
			}
		} catch (HibernateException e) {
			System.out.println("deu ruim " + e.getMessage());
			e.printStackTrace();
		}
		sessao.close();
		System.out.println(disciplina);
	}

}
