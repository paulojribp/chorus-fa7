package com.chorus.dto;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.chorus.entity.Usuario;

@SessionScoped
@Component
public class UsuarioLogado {

	private Usuario usuario;
	
	private Boolean	isLogado;
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getIsLogado() {
		return isLogado;
	}

	public void setIsLogado(Boolean isLogado) {
		this.isLogado = isLogado;
	}

	public Boolean isLogado(){
		return isLogado;
	}
	
	public void logar(Usuario usuario){
		this.usuario=usuario;
		this.isLogado = true;
	}
}
