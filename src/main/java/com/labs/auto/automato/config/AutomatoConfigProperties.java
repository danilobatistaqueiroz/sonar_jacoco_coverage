package com.labs.auto.automato.config;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import com.labs.auto.automato.enums.AutomatoInitializationMode;

@Configuration
//@ConfigurationProperties(prefix = "automato")
@Validated
public class AutomatoConfigProperties implements Validator {

	private Api api;
	private Features features;

	public AutomatoConfigProperties() {
		// Metodo Construtor Default
	}

	public Api getApi() {
		return api;
	}

	public void setApi(Api api) {
		this.api = api;
	}

	public Features getFeatures() {
		return features;
	}

	public void setFeatures(Features features) {
		this.features = features;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AutomatoConfigProperties.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AutomatoConfigProperties props = (AutomatoConfigProperties) target;
		if (!validateFeaturesLoad(props.getFeatures().getLoad())) {
			errors.rejectValue("features.load", "",
					"Value '" + props.getFeatures().getLoad() + "' está formatado incorretamente.");
		}
	}

	private boolean validateFeaturesLoad(final String load) {
		boolean valid = true;

		String[] lista = load.trim().split("\\s*,\\s*");

		for (String str : lista) {
			if (str.contains("=")) {
				String[] lista2 = str.split("\\s*=\\s*", 2);
				if (!(lista2[1].equalsIgnoreCase("TRUE") || lista2[1].equalsIgnoreCase("FALSE"))) {
					valid = false;
				}
			} else {
				valid = false;
			}
		}
		return valid;
	}

	public static class Api {

		@NotBlank
		private String endpoint;
		
		@NotBlank
		private String useragent;
		
		@Min(1)
		@Max(1000)
		private Integer timeout;
		
		@Min(60000)
		@Max(86400000)
		private Integer refreshFrequency;

		public Api() {
			// Metodo Construtor Default
		}

		public String getEndpoint() {
			return endpoint;
		}

		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}

		public String getUseragent() {
			return useragent;
		}

		public void setUseragent(String useragent) {
			this.useragent = useragent;
		}

		public Integer getTimeout() {
			return timeout;
		}

		public void setTimeout(Integer timeout) {
			this.timeout = timeout;
		}

		public Integer getRefreshFrequency() {
			return refreshFrequency;
		}

		public void setRefreshFrequency(Integer refreshFrequency) {
			this.refreshFrequency = refreshFrequency;
		}
	}

	public static class Features {

		@NotBlank
		private String load;
		
		private AutomatoInitializationMode initializationMode;

		public Features() {
			load = "CHINELO_HAVAIANAS=FALSE";
			// Metodo Construtor Default
		}

		public String getLoad() {
			return load;
		}

		public void setLoad(String load) {
			this.load = load;
		}

		public AutomatoInitializationMode getInitializationMode() {
			return initializationMode;
		}

		public void setInitializationMode(AutomatoInitializationMode initializationMode) {
			this.initializationMode = initializationMode;
		}
	}
}
