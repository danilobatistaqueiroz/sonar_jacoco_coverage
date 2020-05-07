package com.labs.auto.automato.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.labs.auto.automato.manager.AutoManager;
import com.labs.auto.automato.type.AutomatoType;
import com.labs.auto.automato.type.ParametroConfiguracaoAplicacaoType;

public class AutoManagerTest extends BaseTest {

	@Autowired
	private AutoManager manager;

	@Test
	public void loadKeys() throws Exception {
		manager.loadKeys();
		//assertThat(manager).isNotNull();
		assertNotNull(manager);
	}
	
	@Test
	public void init() throws Exception {
		manager.consultaAPI("key");
		//assertThat(manager).isNotNull();
		assertNotNull(manager);
	}
	
	@Test
	public void isEnabled() throws Exception {
		AutomatoType key = new AutomatoType() {
				public String name() {
					return "chave";
				}
		};
		ParametroConfiguracaoAplicacaoType param = new ParametroConfiguracaoAplicacaoType();
		param.setNomeParametro("chave");
		param.setNomeResumidoParametro("chave");
		manager.updateToggles(param);
		manager.isEnabled(key);
		//assertThat(manager).isNotNull();
		assertNotNull(manager);
	}
}
