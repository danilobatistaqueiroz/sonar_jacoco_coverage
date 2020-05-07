package com.labs.auto.automato.type;

public class ParametroConfiguracaoAplicacaoType {

	private Integer codigoParametro;
	private Long codigoModulo;
	private String nomeParametro;
	private String nomeResumidoParametro;
	private String textoConteudoParametro;
	private String dataUltimaAtualizacao;
	private String codigoEmpresaUsuarioUltimaAlteracao;
	private String codigoMatriculaUsuarioUltimaAlteracao;
	private String codigoTipoUsuarioUltimaAlteracao;
	
	public Integer getCodigoParametro() {
		return codigoParametro;
	}

	public void setCodigoParametro(final Integer codigoParametro) {
		this.codigoParametro = codigoParametro;
	}
	
	public Long getCodigoModulo() {
		return codigoModulo;
	}
	
	public void setCodigoModulo(final Long codigoModulo) {
		this.codigoModulo = codigoModulo;
	}
	
	public String getTextoConteudoParametro() {
		return textoConteudoParametro;
	}

	public void setTextoConteudoParametro(final String textoConteudoParametro) {
		this.textoConteudoParametro = textoConteudoParametro;
	}

	public String getNomeParametro() {
		return nomeParametro;
	}

	public void setNomeParametro(final String nomeParametro) {
		this.nomeParametro = nomeParametro;
	}

	public String getNomeResumidoParametro() {
		return nomeResumidoParametro;
	}

	public void setNomeResumidoParametro(final String nomeResumidoParametro) {
		this.nomeResumidoParametro = nomeResumidoParametro;
	}

	public String getCodigoEmpresaUsuarioUltimaAlteracao() {
		return codigoEmpresaUsuarioUltimaAlteracao;
	}

	public String getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(String dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	public void setCodigoEmpresaUsuarioUltimaAlteracao(
			final String codigoEmpresaUsuarioUltimaAlteracao) {
		this.codigoEmpresaUsuarioUltimaAlteracao = codigoEmpresaUsuarioUltimaAlteracao;
	}

	public String getCodigoMatriculaUsuarioUltimaAlteracao() {
		return codigoMatriculaUsuarioUltimaAlteracao;
	}

	public void setCodigoMatriculaUsuarioUltimaAlteracao(
			final String codigoMatriculaUsuarioUltimaAlteracao) {
		this.codigoMatriculaUsuarioUltimaAlteracao = codigoMatriculaUsuarioUltimaAlteracao;
	}

	public String getCodigoTipoUsuarioUltimaAlteracao() {
		return codigoTipoUsuarioUltimaAlteracao;
	}

	public void setCodigoTipoUsuarioUltimaAlteracao(
			final String codigoTipoUsuarioUltimaAlteracao) {
		this.codigoTipoUsuarioUltimaAlteracao = codigoTipoUsuarioUltimaAlteracao;
	}


}
