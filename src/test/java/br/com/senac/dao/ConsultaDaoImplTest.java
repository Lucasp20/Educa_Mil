package br.com.senac.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.*;
import org.junit.Test;

import br.com.senac.entidade.Cachorro;
import br.com.senac.entidade.Consulta;
import util.UtilGerador;

public class ConsultaDaoImplTest {

	private Consulta consulta;
	private ConsultaDao consultaDao;
	private Session sessao;
	
	public ConsultaDaoImplTest() {
		consultaDao = new ConsultaDaoImpl();
	}
		
//	@Test
	public void testSalvar() {
		System.out.println("Salvar");
		
		CachorroDaoImplTest cachorroTeste = new CachorroDaoImplTest();
		Cachorro cachorro = cachorroTeste.buscarCachorroBD();
		
		consulta = new Consulta(new Date(), new BigDecimal(UtilGerador.gerarNumero(4)));
		consulta.setAnimal(cachorro);
		
		sessao = HibernateUtil.abrirSessao();
		consultaDao.salvarOuAlterar(consulta, sessao);
		sessao.close();
		assertNotNull(consulta.getId());
	}

//	@Test
	public void testAlterar() {
		System.out.println("Alterar");
		buscarConsultaBD();		
		consulta.setValor(new BigDecimal(UtilGerador.gerarNumero(4)));
		sessao = HibernateUtil.abrirSessao();
				
		consultaDao.salvarOuAlterar(consulta, sessao);
		sessao.close();
		
		sessao = HibernateUtil.abrirSessao();
		Consulta consultaAlt = consultaDao.pesquisarPorId(consulta.getId(), sessao);
		sessao.close();
		assertEquals(consultaAlt.getValor().setScale(2), consulta.getValor().setScale(2));
	}
	
//	@Test
	public void testExcluir() {
		System.out.println("Excluir");
		buscarConsultaBD();	
		
		sessao = HibernateUtil.abrirSessao();
		consultaDao.excluir(consulta, sessao);
		
		Consulta consultaExc = consultaDao.pesquisarPorId(consulta.getId(), sessao);
		sessao.close();
		assertNull(consultaExc);
	}
	
//	@Test
	public void testPesquisarPorData() {
		System.out.println("Pesquisar Por Data");
	}
	
	public Consulta buscarConsultaBD() {
		sessao = HibernateUtil.abrirSessao();
		Query query = sessao.createQuery("from Consulta");
		List<Consulta> consultas = query.list();
		sessao.close();
		if(consultas.isEmpty()) {
			testSalvar();
		}else {
			consulta = consultas.get(0);
		}
		return consulta;
		
	}
}
