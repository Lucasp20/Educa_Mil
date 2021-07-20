package br.com.senac.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.*;
import org.junit.Test;

import br.com.senac.entidade.Dono;
import br.com.senac.entidade.Gato;
import util.UtilGerador;

public class GatoDaoImplTest {

	private Gato gato;
	private GatoDao gatoDao;
	private Session sessao;
	
	public GatoDaoImplTest() {
		gatoDao = new GatoDaoImpl();
	}
	
//	@Test
	public void testSalvar() {
		System.out.println("Salvar");
		
		DonoDaoImplTest donoTeste = new DonoDaoImplTest();
		Dono dono = donoTeste.buscarDonoBD();
		
		gato = new Gato (
				"Gato",
				"Macho",
				"teste teste",
				new Date(),
				4,
				true,
				true				
				);
		
		gato.setDono(dono);
		sessao = HibernateUtil.abrirSessao();
		gatoDao.salvarOuAlterar(gato, sessao);
		sessao.close();
		assertNotNull(gato.getId());
	}
	
//	@Test
	public void testAlterar() {
		System.out.println("Alterar");
		buscarGatoBD();
		gato.setNome("Gato");
		sessao = HibernateUtil.abrirSessao();
		
		gatoDao.salvarOuAlterar(gato, sessao);
		sessao.close();
		
		sessao = HibernateUtil.abrirSessao();
		Gato gatoAlt = gatoDao.pesquisarPorId(gato.getId(), sessao);
		sessao.close();
		assertEquals(gato.getNome(), gatoAlt.getNome());
	}

//	@Test
	public void testExcluir() {
		System.out.println("Excluir");
		buscarGatoBD();
		sessao = HibernateUtil.abrirSessao();
		gatoDao.excluir(gato, sessao);

		Gato gatoExc = gatoDao.pesquisarPorId(gato.getId(), sessao);
		sessao.close();
		assertNull(gatoExc);
	}
	
// @Test
	public void testPesquisarPorID() {
		System.out.println("Pesquisar Por ID");
		buscarGatoBD();
	}
	
//	@Test
	public void testPesquisarPorNome() {
		System.out.println("Pesquisar Por Nome");
		buscarGatoBD();
		sessao = HibernateUtil.abrirSessao();
		List<Gato> gatos = gatoDao.pesquisarPorNome(gato.getNome(), sessao);
		sessao.close();
		assertTrue(gatos.size() >= 1);
	}

	public Gato buscarGatoBD() {
		sessao = HibernateUtil.abrirSessao();
		Query consulta = sessao.createQuery("from Gato");
		List<Gato> gatos = consulta.list();
		sessao.close();
		if(gatos.isEmpty()) {
			testSalvar();
		}else {
			gato = gatos.get(0);
		}
		return gato;
	}
}
