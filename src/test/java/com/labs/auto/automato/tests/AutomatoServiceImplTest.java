package com.labs.auto.automato.tests;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.labs.auto.automato.service.impl.AutomatoServiceImpl;
import com.labs.auto.automato.type.ParametroConfiguracaoAplicacaoType;

public class AutomatoServiceImplTest extends BaseTest {

	@Autowired
	private AutomatoServiceImpl service;

	@Test
	public void obterAutomato() throws Exception {
		//assertThat(service).isNotNull();
		assertNotNull(service);
		service.obterAutomato("abc");
	}
	
	@Test
	public void init() throws Exception {
		assertNotNull(service);
		service.init();
	}
	
	@Test
	public void Parametros() {
		ParametroConfiguracaoAplicacaoType parametro = service.obterAutomato("abc");
		assertEquals("SUBMARINO",parametro.getCodigoEmpresaUsuarioUltimaAlteracao());
		assertEquals("1234",parametro.getCodigoMatriculaUsuarioUltimaAlteracao());
		assertEquals(Long.valueOf(1L),parametro.getCodigoModulo());
		assertEquals("superuser",parametro.getCodigoTipoUsuarioUltimaAlteracao());
		assertEquals("2020/05/01",parametro.getDataUltimaAtualizacao());
		assertEquals("TABLET SAMSUNG",parametro.getNomeParametro());
		assertEquals("TABLET",parametro.getNomeResumidoParametro());
		assertEquals("DISPOSITIVO DE TELA DE 10 POLEGADAS",parametro.getTextoConteudoParametro());
		assertEquals(Integer.valueOf(10),parametro.getCodigoParametro());
	}
	
	@Test
	public void codigoParametro() {
		ParametroConfiguracaoAplicacaoType parametro = service.obterAutomato("abc");
		assertEquals(Integer.valueOf(10),parametro.getCodigoParametro());
	}
}
