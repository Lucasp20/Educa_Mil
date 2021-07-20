package br.com.senac.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.*;
import org.junit.Test;
import br.com.senac.entidade.Cachorro;
import br.com.senac.entidade.Dono;
import br.com.senac.entidade.Gato;
import util.UtilGerador;


public class CachorroDaoImplTest {
	
	private Cachorro cachorro;
	private CachorroDao cachorroDao;
	private Session sessao;
	
	public CachorroDaoImplTest() {
		cachorroDao = new CachorroDaoImpl();
	}
	
//	@Test
	public void testSalvar() {
		System.out.println("Salvar");
		DonoDaoImplTest donoTeste = new DonoDaoImplTest();
		Dono dono = donoTeste.buscarDonoBD();
		
		cachorro = new Cachorro(
				"Cachorro",
				"Macho",
				"Cachorro Grande",
				new Date(),
				45,
				false
				);
		cachorro.setDono(dono);
		sessao = HibernateUtil.abrirSessao();
		cachorroDao.salvarOuAlterar(cachorro, sessao);
		sessao.close();
		assertNotNull(cachorro.getId());
	}
	
//	@Test
	public void testAlterar() {
		System.out.println("Alterar");
		buscarCachorroBD();
		cachorro.setNome("Cachorro");
		sessao = HibernateUtil.abrirSessao();
		cachorroDao.salvarOuAlterar(cachorro, sessao);
		sessao.close();
		
		sessao = HibernateUtil.abrirSessao();
		Cachorro cachorroAlt = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
		sessao.close();
		assertEquals(cachorro.getNome(), cachorroAlt.getNome());
	}
// @Test
	public void testExcluir() {
		System.out.println("Excluir");
		buscarCachorroBD();
		sessao = HibernateUtil.abrirSessao();
		cachorroDao.excluir(cachorro, sessao);

		Cachorro cachorroExc = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
		sessao.close();
		assertNull(cachorroExc);
	}
//	@Test
	public void testPesquisarPorNome() {
		System.out.println("Pesquisar Por Nome");
		buscarCachorroBD();
		sessao = HibernateUtil.abrirSessao();
		List<Cachorro> cachorros = cachorroDao.pesquisarPorNome(cachorro.getNome(), sessao);
		sessao.close();
		assertTrue(cachorros.size() >= 1);
	}
	
	public Cachorro buscarCachorroBD() {
		sessao = HibernateUtil.abrirSessao();
		Query consulta = sessao.createQuery("from Cachorro");
		List<Cachorro> cachorros = consulta.list();
		sessao.close();
		if(cachorros.isEmpty()) {
			testSalvar();
		}else {
			cachorro = cachorros.get(0);
		}
		return cachorro;
	}
}

