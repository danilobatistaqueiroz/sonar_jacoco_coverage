package com.labs.auto.automato.tests;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.labs.auto.automato.events.AutomatoEventListener;
import com.labs.auto.automato.type.ParametroConfiguracaoAplicacaoType;

public class AutomatoEventListenerTest extends BaseTest {
	
	@Autowired
	private AutomatoEventListener listener;

	@Test
	public void loadKeys() throws Exception {
		ParametroConfiguracaoAplicacaoType message = new ParametroConfiguracaoAplicacaoType();
		message.setNomeResumidoParametro("AUTO_COTACAO");
		message.setTextoConteudoParametro("true");
		message.setCodigoMatriculaUsuarioUltimaAlteracao("123456");
		message.getCodigoMatriculaUsuarioUltimaAlteracao();
		message.setCodigoTipoUsuarioUltimaAlteracao("superuser");
		message.getCodigoTipoUsuarioUltimaAlteracao();
		listener.readMessage(message);
		//assertThat(listener).isNotNull();
		assertNotNull(listener);
	}
}
