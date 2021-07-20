package br.com.senac.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import br.com.senac.entidade.Dono;
import util.UtilGerador;

public class DonoDaoImplTest {

	private Dono dono;
	private DonoDao donoDao;
	private Session sessao;
	
	public DonoDaoImplTest() {
		donoDao = new DonoDaoImpl();
	}
	
//	@Test
	public void testSalvar() {
		System.out.println("Salvar");
		dono = new Dono (
				UtilGerador.gerarNome(),
				UtilGerador.gerarCpf(),
				UtilGerador.gerarTelefoneFixo(),
				UtilGerador.gerarEmail()	
				);
		sessao = HibernateUtil.abrirSessao();
		donoDao.salvarOuAlterar(dono, sessao);
		sessao.close();
		assertNotNull(dono.getId());
	}
	
//	@Test
	public void testAlterar() {
		System.out.println("Alterar");
	}
	
//	@Test
	public void testExcluir() {
		System.out.println("Excluir");
	}
	
//	@Test
	public void testPesquisarPorID() {
		System.out.println("Pesquisar Por ID");
	}
	
//	@Test
	public void testPesquisarPorNome() {
		System.out.println("Pesquisar Por Nome");
	}
	
//	@Test
	public void testPesquisarPorCpf() {
		System.out.println("Pesquisar Por CPF");
	}
	
	public Dono buscarDonoBD() {
		sessao = HibernateUtil.abrirSessao();
		Query consulta = sessao.createQuery("from Dono");
		List<Dono> donos = consulta.list();
		sessao.close();
		if(donos.isEmpty()) {
			testSalvar();
		}else {
			dono = donos.get(0);
		}
		return dono;
	}

}
