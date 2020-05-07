package com.labs.auto.automato.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.labs.auto.automato.config.AutomatoConfigProperties;
import com.labs.auto.automato.type.ParametroConfiguracaoAplicacaoType;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Component
public class AutomatoServiceImpl {

    private static final String PATH = "parametrosconfiguracao/nome";
    
    private static final String LOG_INFO_THREAD = "Client: {} Thread: {}";

	/**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AutomatoServiceImpl.class);

	@Autowired
    private AutomatoConfigProperties props;
	
	/**
	 * WebClient Builder
	 */
	@Autowired
	private WebClient.Builder builderInjected;

	/**
	 * WebClient
	 */
	private WebClient webClient;
        
	//@PostConstruct
	public void init() {
		final HttpClient httpClient = HttpClient.create().tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, props.getApi().getTimeout() * 1000))
				.tcpConfiguration(client -> client.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(props.getApi().getTimeout())).addHandlerLast(new WriteTimeoutHandler(props.getApi().getTimeout()))));

		final ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
		
		//webClient = builderInjected.baseUrl(props.getApi().getEndpoint()).clientConnector(connector)
		//		.defaultHeader(HttpHeaders.USER_AGENT, props.getApi().getUseragent()).build();
	}

	/*
	public ParametroConfiguracaoAplicacaoType obterFeatures(final String featureName) {
		try {
			return webClient.get().uri("/" + PATH + "/" + featureName).retrieve().bodyToMono(ParametroConfiguracaoAplicacaoType.class).doOnNext(c -> LOGGER.info(LOG_INFO_THREAD,c,Thread.currentThread())).block();
		} catch (WebClientException wce) {
			// server errors paersed by webclient
			LOGGER.error(wce.getMessage());
		} catch (io.netty.channel.ChannelException ce) {
			// communication errors - netty - network
			// example: io.netty.handler.timeout.ReadTimeoutException
			// example: io.netty.handler.timeout.WriteTimeoutException
			LOGGER.error(ce.getMessage());
		} catch (RuntimeException re) {
			// communication errors - reactor - configuration, dns, etc..
			// example: reactor.core.Exceptions$ReactiveException: java.net.UnknownHostException: HOSTUNDEFINED
			// example: reactor.core.Exceptions$ReactiveException: io.netty.channel.AbstractChannel$AnnotatedConnectException: Connection refused: no further information: localhost/127.0.0.1:8098
			LOGGER.error(re.getMessage());
		}
		return null;
	}
	*/
	
	public ParametroConfiguracaoAplicacaoType obterAutomato(final String featureName) {
		ParametroConfiguracaoAplicacaoType parametro = new ParametroConfiguracaoAplicacaoType();
		parametro.setCodigoEmpresaUsuarioUltimaAlteracao("SUBMARINO");
		parametro.setCodigoMatriculaUsuarioUltimaAlteracao("1234");
		parametro.setCodigoTipoUsuarioUltimaAlteracao("superuser");
		parametro.setDataUltimaAtualizacao("2020/05/01");
		parametro.setNomeParametro("TABLET SAMSUNG");
		parametro.setNomeResumidoParametro("TABLET");
		parametro.setTextoConteudoParametro("DISPOSITIVO DE TELA DE 10 POLEGADAS");
		parametro.setCodigoModulo(1L);
		parametro.setCodigoParametro(10);
		return parametro;
	}
}
