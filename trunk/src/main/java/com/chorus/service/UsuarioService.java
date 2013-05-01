package com.chorus.service;

import com.chorus.dto.UsuarioDto;
import com.chorus.entity.Usuario;

public interface UsuarioService {

	String salvar(UsuarioDto usuario) throws Exception;
	
	Usuario findByUsuario(Usuario usuario);
	
	

}
