package com.chorus.dto;

public class ChoruDto {

	private String mensagem;
	
	private String datahora;
	
	private String username;
	
	private String nome;
	
	private String gravatarUrl;

	public ChoruDto() {
	}
	
	public ChoruDto(String mensagem, String username, String nome,
			String gravatarUrl, String datahora) {
		super();
		this.mensagem = mensagem;
		this.username = username;
		this.nome = nome;
		this.gravatarUrl = gravatarUrl;
		this.datahora = datahora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGravatarUrl() {
		return gravatarUrl;
	}

	public void setGravatarUrl(String gravatarUrl) {
		this.gravatarUrl = gravatarUrl;
	}

	public String getDatahora() {
		return datahora;
	}

	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}
	
}