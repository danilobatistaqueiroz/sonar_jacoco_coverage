package com.labs.auto.automato.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//import com.labs.auto.exception.EnvironmentException;

import com.labs.auto.automato.config.AutomatoConfigProperties;
import com.labs.auto.automato.config.AutomatoConfigProperties.Features;
import com.labs.auto.automato.enums.AutomatoInitializationMode;
import com.labs.auto.automato.service.impl.AutomatoServiceImpl;
import com.labs.auto.automato.type.AutomatoType;
import com.labs.auto.automato.type.ParametroConfiguracaoAplicacaoType;

//@EnableScheduling
@Component
public class AutoManager {

	@Autowired
    private AutomatoConfigProperties properties;
	private Features features;
	
	@Autowired
	private AutomatoServiceImpl service;

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoManager.class);

	private Supplier<Stream<String[]>> keys;

	private Map<String, Boolean> toggles = new HashMap<>();

	@PostConstruct
	private void init() {
		properties = new AutomatoConfigProperties();
		features = new Features();
		properties.setFeatures(features);
		properties.getFeatures().setInitializationMode(AutomatoInitializationMode.DEFAULT);
		
		
		LOGGER.info("[AUTOMATO TOGGLE MODE : {}]", properties.getFeatures().getInitializationMode());
		keys = () -> Arrays.stream(properties.getFeatures().getLoad().toUpperCase().split("\\s*,\\s*")).distinct().map(s -> s.split("\\s*=\\s*"));

		// Carrega o HASHMAP local
		try {
			toggles = keys.get().collect(Collectors.toMap(s -> s[0].trim(), s -> new Boolean(s[1])));
		} catch (Exception e) {
			throw new RuntimeException("Automato cadastrado incorretamente. Verifique se esta no formato : CHAVE_1=TRUE, CHAVE_2=FALSE");
		}
		
		// Efetua as chamadas ONLINE somente no modo DEFAULT
		if (properties.getFeatures().getInitializationMode() == AutomatoInitializationMode.DEFAULT) {
			loadKeys();
		}
	}

	//@Scheduled(fixedRateString = "${auto-feature-toggle.api.refresh-frequency}", initialDelayString = "${auto-feature-toggle.api.refresh-frequency}")
	public void loadKeys() {
		if (properties.getFeatures().getInitializationMode() == AutomatoInitializationMode.DEFAULT) {
			LOGGER.info("[LOADING ALL AUTOMATO TOGGLES]");
			keys.get().forEach(s -> consultaAPI(s[0]));
		}
	}

	public void consultaAPI(final String key) {
		LOGGER.info("[LOADING AUTOMATO TOGGLE '{}']", key);
		// call api online with filter
		ParametroConfiguracaoAplicacaoType automatoResponse = service.obterAutomato(key);

		// put result on local cache
		updateToggles(automatoResponse);
	}

	public void updateToggles(final ParametroConfiguracaoAplicacaoType param) {
		// put result on local cache
		if (param != null) {
			LOGGER.info("LOADING AUTOMATO TOGGLE '{}'='{}'", param.getNomeResumidoParametro(),
					Boolean.parseBoolean(param.getTextoConteudoParametro()));
			toggles.put(param.getNomeResumidoParametro(), Boolean.parseBoolean(param.getTextoConteudoParametro()));
		}
	}

	public boolean isEnabled(final AutomatoType key) {
		if (toggles.get(key.name()) == null) {
			throw new RuntimeException(
					"Automato não encontrado. Verifique se o automato está cadastrado na propriedade automato.auto.load e no ENUM da sua aplicação.");
		}

		return toggles.get(key.name());
	}
}
