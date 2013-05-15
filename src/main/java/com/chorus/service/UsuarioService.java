package com.chorus.service;

import java.util.List;

import com.chorus.dto.UsuarioDto;
import com.chorus.entity.Usuario;
import com.chorus.exceptions.ErroAoSeguirException;
import com.chorus.exceptions.UsuarioInexistenteException;

public interface UsuarioService {

	String salvar(UsuarioDto usuario) throws Exception;
	
	Usuario findByUsuario(Usuario usuario);
	
	void seguir(Long userId, Long userASeguirId) throws ErroAoSeguirException;

	void refresh(Usuario user);

	Usuario login(Usuario usuario) throws UsuarioInexistenteException;

	List<UsuarioDto> findSeguindo(Usuario user);

}
