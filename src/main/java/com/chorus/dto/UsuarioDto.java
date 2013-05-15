package com.chorus.dto;

import com.chorus.entity.Usuario;

public class UsuarioDto {

	private String nome;
	
	private String username;
	
	private String email;
	
	private String senha;
	
	private String confirmaSenha;
	
	private String gravatarUrl;
	
	private boolean seguido;
	
	private boolean seguindo;

	public UsuarioDto() {
	}
	
	public UsuarioDto(String username, String nome) {
		this.username = username;
		this.nome = nome;
	}

	public UsuarioDto(Usuario u) {
		username = u.getUsername();
		email = u.getEmail();
		nome = u.getNome();
	}

	public Usuario getUsuario() {
		Usuario u = new Usuario();
		u.setUsername(username);
		u.setEmail(email);
		u.setSenha(senha);
		u.setNome(nome);
		
		return u;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
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

	public boolean isSeguido() {
		return seguido;
	}

	public void setSeguido(boolean seguido) {
		this.seguido = seguido;
	}

	public boolean isSeguindo() {
		return seguindo;
	}

	public void setSeguindo(boolean seguindo) {
		this.seguindo = seguindo;
	}
	
}
