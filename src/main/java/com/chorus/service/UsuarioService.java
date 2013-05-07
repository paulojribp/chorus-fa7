package com.chorus.service;

import com.chorus.dto.UsuarioDto;
import com.chorus.entity.Usuario;
import com.chorus.exceptions.ErroAoSeguirException;

public interface UsuarioService {

	String salvar(UsuarioDto usuario) throws Exception;
	
	Usuario findByUsuario(Usuario usuario);
	
	void seguir(Long userId, Long userASeguirId) throws ErroAoSeguirException;

}
