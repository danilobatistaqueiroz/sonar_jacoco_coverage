package com.labs.auto.automato.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.labs.auto.automato.manager.AutoManager;
import com.labs.auto.automato.type.ParametroConfiguracaoAplicacaoType;

//@EnableBinding(Sink.class)
//@ConditionalOnProperty(value = "auto-feature-toggle.features.initialization-mode", havingValue = "default", matchIfMissing = false)
@Component
public class AutomatoEventListener {

	@Autowired
	private AutoManager autoManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(AutomatoEventListener.class);

	//@StreamListener(Sink.INPUT)
	public void readMessage(final ParametroConfiguracaoAplicacaoType message) {
		LOGGER.info("Novo evento lido : {} ", message);

		autoManager.updateToggles(message);
	}
}
