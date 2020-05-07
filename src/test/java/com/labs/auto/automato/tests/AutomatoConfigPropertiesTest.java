package com.labs.auto.automato.tests;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.labs.auto.automato.config.AutomatoConfigProperties;
import com.labs.auto.automato.config.AutomatoConfigProperties.Features;

public class AutomatoConfigPropertiesTest extends BaseTest {

	@Autowired
	private AutomatoConfigProperties autoProperties;
	
	@Mock
	Errors errors;

	@Test
	public void obterAutomato() throws Exception {
		AutomatoConfigProperties target = new AutomatoConfigProperties(); 
		when(errors.getNestedPath()).thenReturn("xx");		

		Features features = new Features();
		target.setFeatures(features);
		
		autoProperties.validate(target, errors);
		features.setLoad("CHINELO_HAVAIANAS=verdade");
		autoProperties.validate(target, errors);
		features.setLoad("CHINELO_HAVAIANAS");
		autoProperties.validate(target, errors);
		
		autoProperties.supports(errors.getClass());
		AutomatoConfigProperties.Api api = new AutomatoConfigProperties.Api();
		autoProperties.setApi(api);
		autoProperties.getApi();

		assertEquals("xx",errors.getNestedPath());
		//assertThat(autoProperties).isNotNull();
		assertNotNull(autoProperties);
	}
}
